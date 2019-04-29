package org.styleru.the6hands.presentation.profile;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
import com.vk.api.sdk.VK;

import org.styleru.the6hands.R;
import org.styleru.the6hands.SixHandsApplication;
import org.styleru.the6hands.domain.entities.Apartment;
import org.styleru.the6hands.domain.entities.User;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class ProfileFragment extends MvpAppCompatFragment implements ProfileView {

    @BindView(R.id.profile_recycler)
    RecyclerView recyclerView;

    @BindView(R.id.profile_name)
    TextView profileName;

    @BindView(R.id.change_profile_data)
    TextView changeProfileData;

    @BindView(R.id.profile_pic)
    ImageView profilePic;

    @BindView(R.id.vk_button)
    View vkButton;

    @BindView(R.id.add_apartment)
    FloatingActionButton addApartmentBtn;

    Disposable disposable;

    ApartmentAdapter apartmentAdapter;

    @Inject
    @InjectPresenter
    ProfilePresenter profilePresenter;

    @ProvidePresenter
    ProfilePresenter provideProfilePresenter(){
        return profilePresenter;
    }

    @Override
    public void onAttach(Context context) {
        SixHandsApplication.getAppComponent().inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        if (!VK.isLoggedIn()) {
            addApartmentBtn.hide();
            changeProfileData.setVisibility(View.INVISIBLE);
            vkButton.setVisibility(View.INVISIBLE);
            Glide.with(this)
                    .load(R.drawable.anon)
                    .apply(RequestOptions.circleCropTransform())
                    .into(profilePic);
            return view;

        }

        apartmentAdapter = new ApartmentAdapter();
        disposable = apartmentAdapter.getOnClickApartment()
                .subscribe(profilePresenter::onMyApartmentClicked);

        recyclerView.setAdapter(apartmentAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && addApartmentBtn.getVisibility() == View.VISIBLE)
                    addApartmentBtn.hide();
                else if (dy < 0 && addApartmentBtn.getVisibility() != View.VISIBLE)
                    addApartmentBtn.show();
            }
        });
        return view;
    }

    @Override
    public void setUser(User user) {
        profileName.setText(user.getFirstName());
        Glide.with(this)
                .load(user.getPhoto200Url())
                .apply(RequestOptions.circleCropTransform())
                .into(profilePic);
    }

    @Override
    public void openLink(String link){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
    }

    @Override
    public void setApartments(List<Apartment> apartments) {
        apartmentAdapter.setAppartments(apartments);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.vk_button)
    void onVkButtonClicked(){
        profilePresenter.onVkButtonClicked();
    }

    @OnClick(R.id.add_apartment)
    void onClickAddApartment(){
        Toast.makeText(getContext(), "Click", Toast.LENGTH_SHORT).show();

    }

    @OnClick(R.id.change_profile_data)
    void onClickChangeProfileData(){

    }

    @OnClick(R.id.add_facebook_button)
    void onClickAddFacebook(){}


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null) disposable.dispose();
    }
}
