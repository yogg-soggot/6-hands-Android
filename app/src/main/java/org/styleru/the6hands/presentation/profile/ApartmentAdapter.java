package org.styleru.the6hands.presentation.profile;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.styleru.the6hands.R;
import org.styleru.the6hands.domain.entities.Apartment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.subjects.PublishSubject;

public class ApartmentAdapter extends RecyclerView.Adapter<ApartmentAdapter.ApartmentViewHolder> {
    private PublishSubject<Apartment> onClickApartment;
    private List<Apartment> apartments;

    ApartmentAdapter() {
        apartments = new ArrayList<>();
        onClickApartment = PublishSubject.create();
    }

    void setAppartments(List<Apartment> apartments){
        this.apartments = apartments;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ApartmentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View vApartment = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_my_apartment,viewGroup,false);

        return new ApartmentViewHolder(vApartment);

    }

    @Override
    public void onBindViewHolder(@NonNull ApartmentViewHolder viewHolder, int i) {
        viewHolder.bind(apartments.get(i));
    }

    PublishSubject<Apartment> getOnClickApartment() {
        return onClickApartment;
    }

    @Override
    public int getItemCount() {
        return apartments.size();
    }

    class ApartmentViewHolder extends RecyclerView.ViewHolder {
        private Apartment apartment;

        ApartmentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.edit_my_apartment)
        void onClickEdit(){

        }

        @OnClick(R.id.root)
        void onClickMyApartment(){
            onClickApartment.onNext(apartment);
        }

        void bind(Apartment apartment){
            this.apartment = apartment;
        }
    }


}
