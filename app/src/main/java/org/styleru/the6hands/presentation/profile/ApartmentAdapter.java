package org.styleru.the6hands.presentation.profile;

import android.content.Context;
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

public class ApartmentAdapter extends RecyclerView.Adapter<ApartmentAdapter.apartmentViewHolder> {
    private Context context;
    private PublishSubject<Apartment> onClickApartment;
    private List<Apartment> apartments;

    ApartmentAdapter(Context context) {
        this.context = context;
        apartments = new ArrayList<>();
        onClickApartment = PublishSubject.create();
    }

    void setAppartments(List<Apartment> apartments){
        this.apartments = apartments;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ApartmentAdapter.apartmentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View vApartment = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_my_apartment,viewGroup,false);

        return new apartmentViewHolder(vApartment);

    }

    @Override
    public void onBindViewHolder(@NonNull ApartmentAdapter.apartmentViewHolder viewHolder, int i) {
        viewHolder.bind(apartments.get(i));
    }

    PublishSubject<Apartment> getOnClickApartment() {
        return onClickApartment;
    }

    @Override
    public int getItemCount() {
        return apartments.size();
    }

    class apartmentViewHolder extends RecyclerView.ViewHolder {
        private Apartment apartment;

        apartmentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.edit_my_apartment)
        void onClickEdit(){

        }

        @OnClick(R.id.my_apartment_card)
        void onClickMyApartment(){
            onClickApartment.onNext(apartment);
        }

        void bind(Apartment apartment){
            this.apartment = apartment;
        }
    }


}
