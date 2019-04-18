package org.styleru.the6hands.presentation.profile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.styleru.the6hands.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;

    ProfileAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View vFlat = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_my_flat,viewGroup,false);

        return new FlatViewHolder(vFlat);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class FlatViewHolder extends RecyclerView.ViewHolder {

        FlatViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.edit_my_flat)
        void onClickEdit(){

        }
    }


}
