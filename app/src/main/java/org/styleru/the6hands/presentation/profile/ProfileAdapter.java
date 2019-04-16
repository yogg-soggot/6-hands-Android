package org.styleru.the6hands.presentation.profile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.joooonho.SelectableRoundedImageView;

import org.styleru.the6hands.R;
import org.styleru.the6hands.domain.entities.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_PROFILE = 0, VIEW_TYPE_FLAT = 1;
    private User user;
    private Context context;

    ProfileAdapter(Context context) {
        this.context = context;
    }

    public void setUser(User user) {
        this.user = user;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return position > 0 ? VIEW_TYPE_FLAT : VIEW_TYPE_PROFILE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_PROFILE:
                View vProfile = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_profile,viewGroup,false);
                return new ProfileViewHolder(vProfile);
            default:
                View vFlat = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_my_flat,viewGroup,false);
                return new FlatViewHolder(vFlat);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        switch (viewHolder.getItemViewType()){
            case VIEW_TYPE_PROFILE:
                if (user != null) {
                    ProfileViewHolder profileViewHolder = (ProfileViewHolder) viewHolder;
                    profileViewHolder.profileName.setText(user.getFirstName());
                    Glide.with(context)
                            .load(user.getPhoto200Url())
                            .into(profileViewHolder.profilePic);
                }
                break;

            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class ProfileViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.profile_name)
        TextView profileName;

        @BindView(R.id.profile_pic)
        SelectableRoundedImageView profilePic;

        ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.change_profile_data)
        void onClickChangeProfileData(){

        }

        @OnClick(R.id.vk_button)
        void onClickVkButton(){

        }
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
