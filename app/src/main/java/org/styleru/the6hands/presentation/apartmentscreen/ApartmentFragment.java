package org.styleru.the6hands.presentation.apartmentscreen;


import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import org.parceler.Parcels;
import org.styleru.the6hands.R;
import org.styleru.the6hands.SixHandsApplication;
import org.styleru.the6hands.domain.entities.Apartment;

import java.util.Objects;

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

    @BindView(R.id.facility_refrigerator)
    LinearLayout refrigerator;

    @BindView(R.id.facility_pets)
    LinearLayout petsx;

    @BindView(R.id.facility_wifi)
    LinearLayout wifix;

    @BindView(R.id.facility_parking)
    LinearLayout parkingx;

    @BindView(R.id.facility_air_conditioning)
    LinearLayout conditioningx;


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
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayShowTitleEnabled(false);
        setHasOptionsMenu(true);
        collapsingToolbarLayout.setTitleEnabled(false);

        // init all fields
        Apartment apartment = Parcels.unwrap(getArguments().getParcelable(APARTMENT_KEY));
        appartmentAddress.setText(apartment.getAddress());

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
    public void onDescriptionClicked(){
        if (needToExpand) {
            apartmentDescription.setMaxLines(Integer.MAX_VALUE);
            fullPlease.setVisibility(View.GONE);
            needToExpand = false;
        }
    }

    @Override
    public void setApartmentDescription(String description) {
        apartmentDescription.setText(description);
        int length = (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) ? 320 : 800;
        if (description.length() > length) {
            needToExpand = true;
            apartmentDescription.setMaxLines(4);
            fullPlease.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setFacilities(boolean fridge, boolean pets, boolean wifi, boolean parking, boolean conditioning) {
        if(fridge) refrigerator.setVisibility(View.VISIBLE);
        if(pets) petsx.setVisibility(View.VISIBLE);
        if(wifi) wifix.setVisibility(View.VISIBLE);
        if(parking) parkingx.setVisibility(View.VISIBLE);
        if(conditioning) conditioningx.setVisibility(View.VISIBLE);
    }


    //Use this when u need to change color of subway line before station name
    //All colors are in resources, u don't need to choose them manually
    //Use 0 if there's no metro
    @Override
    public void setSubwayLine(int subwayLine) {
        switch (subwayLine) {
            case 0: subwayColor.setVisibility(View.GONE); // TODO: 21.04.2019 make all cities with metro support
                break;
            case 1: subwayColor.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.metro1_sokolnicheskaya)));
                break;
            case 2: subwayColor.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.metro2_zamoskvoretskaya)));
                break;
            case 3: subwayColor.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.metro3_arbatsko_pokrovskaya)));
                break;
            case 4: subwayColor.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.metro4_filyovskaya)));
                break;
            case 5: subwayColor.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.metro5_koltsevaya)));
                break;
            case 6: subwayColor.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.metro6_kaluzhsko_rizhskaya)));
                break;
            case 7: subwayColor.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.metro7_tagansko_krasnopresnenskaya)));
                break;
            case 8: subwayColor.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.metro8_kalininskaya)));
                break;
            case 9: subwayColor.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.metro9_serpuhobsko_temiryazebskaya)));
                break;
            case 10: subwayColor.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.metro10_lyublinskaya)));
                break;
            case 11: subwayColor.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.metro11_kahovskaya)));
                break;
            case 12: subwayColor.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.metro12_butovskaya)));
                break;
            default: throw new IllegalArgumentException("Pick one of 12 moscow metro lines");
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