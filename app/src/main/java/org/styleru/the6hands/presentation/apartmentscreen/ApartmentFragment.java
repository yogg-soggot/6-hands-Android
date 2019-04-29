package org.styleru.the6hands.presentation.apartmentscreen;


import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.parceler.Parcels;
import org.styleru.the6hands.R;
import org.styleru.the6hands.SixHandsApplication;
import org.styleru.the6hands.domain.entities.Apartment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ApartmentFragment extends MvpAppCompatFragment implements ApartmentView {

    private static final String APARTMENT_KEY = "apartment";

    @Inject
    @InjectPresenter
    ApartmentPresenter presenter;

    @BindView(R.id.toolbar_apartment)
    Toolbar toolbar;

    @BindView(R.id.toolbar_layout_apartment)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.apartment_address)
    TextView appartmentAddress;

    @BindView(R.id.apartment_image)
    ImageView apartmentImage;

    @BindView(R.id.subway_line_color)
    View subwayColor;

    @BindView(R.id.apartment_description)
    TextView apartmentDescription;

    @BindView(R.id.full_please)
    TextView fullPlease;

    @BindView(R.id.facilities_list)
    RecyclerView recyclerView;

    @BindView(R.id.facility_group)
    Group group;

    @BindView(R.id.owner_avatar)
    ImageView ownerAvatar;


    boolean needToExpand = false;

    @ProvidePresenter
    ApartmentPresenter provideApartmentPresenter(){
        return presenter;
    }

    @Override
    public void onAttach(Context context) {
        SixHandsApplication.getAppComponent().inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apartment, container, false);
        ButterKnife.bind(this, view);

        // Toolbar
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        setHasOptionsMenu(true);
        collapsingToolbarLayout.setTitleEnabled(false);

        // TODO init all fields
        Apartment apartment = Parcels.unwrap(getArguments().getParcelable(APARTMENT_KEY));
        appartmentAddress.setText(apartment.getAddress());
        subwayColor.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#EB1B35")));
        setApartmentDescription("Description");
        Glide.with(this)
                .load(R.drawable.anon)
                .apply(RequestOptions.circleCropTransform())
                .into(ownerAvatar);

        if (apartment.getFacilities().isEmpty()) group.setVisibility(View.GONE);
        else recyclerView.setAdapter(new FacilitiesAdapter(apartment.getFacilities()));

        return view;
    }

    // This method is used when user clicks on toolbar menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add_to_favourites:
                // TODO: 21.04.2019 Place your action on click favourites in toolbar
                return true;

            case android.R.id.home:
                presenter.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Toolbar menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.apartment_toolbar_menu, menu);
    }

    @OnClick({R.id.apartment_description, R.id.full_please})
    void onDescriptionClicked(){
        if (needToExpand) {
            apartmentDescription.setMaxLines(Integer.MAX_VALUE);
            fullPlease.setVisibility(View.GONE);
            needToExpand = false;
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    private void setApartmentDescription(String description) {
        apartmentDescription.setText(description);
        int length = (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) ? 320 : 800;
        if (description.length() > length) {
            needToExpand = true;
            apartmentDescription.setMaxLines(4);
            fullPlease.setVisibility(View.VISIBLE);
        }
    }

    public static ApartmentFragment getInstance(Apartment apartment){
        ApartmentFragment apartmentFragment = new ApartmentFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(APARTMENT_KEY, Parcels.wrap(apartment));
        apartmentFragment.setArguments(bundle);
        return apartmentFragment;
    }
}