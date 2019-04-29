package org.styleru.the6hands.presentation.apartmentscreen;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.styleru.the6hands.R;
import org.styleru.the6hands.domain.entities.Facility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FacilitiesAdapter extends RecyclerView.Adapter<FacilitiesAdapter.FacilityHolder> {

    private List<Facility> facilities;

    FacilitiesAdapter(List<Facility> facilities) {
        this.facilities = facilities;
    }

    @NonNull
    @Override
    public FacilityHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vApartment = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_facility, viewGroup, false);
        return new FacilityHolder(vApartment);
    }

    @Override
    public void onBindViewHolder(@NonNull FacilityHolder facilityHolder, int i) {
        facilityHolder.bind(facilities.get(i));
    }

    @Override
    public int getItemCount() {
        return facilities.size();
    }

    class FacilityHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.facility_icon)
        ImageView icon;

        @BindView(R.id.facility_name)
        TextView name;

        FacilityHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Facility facility){
            Glide.with(itemView)
                    .load(facility.getIcon())
                    .into(icon);
            name.setText(facility.getName());
        }
    }
}
