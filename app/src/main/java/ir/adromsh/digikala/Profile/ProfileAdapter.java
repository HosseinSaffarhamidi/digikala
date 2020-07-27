package ir.adromsh.digikala.Profile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import ir.adromsh.digikala.Model.Profile;
import ir.adromsh.digikala.R;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {
    List<Profile> profiles;
    OnProfileItemClick onProfileItemClick;
    Context context;

    public ProfileAdapter(Context context,List<Profile> profiles,OnProfileItemClick onProfileItemClick){
        this.profiles=profiles;
        this.onProfileItemClick=onProfileItemClick;
        this.context=context;
    }
    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.profile_item,viewGroup,false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder profileViewHolder, int i) {

        final Profile profile=profiles.get(i);
        profileViewHolder.txtTitle.setText(profile.getTitle());
        profileViewHolder.icon.setImageDrawable(ContextCompat.getDrawable(context,profile.getIcon()));
        profileViewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onProfileItemClick.onClick(profile.getTitle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout parent;
        TextView txtTitle;
        ImageView icon;
        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            parent=itemView.findViewById(R.id.rel_profileItem_parent);
            txtTitle=itemView.findViewById(R.id.txt_profileItem_title);
            icon=itemView.findViewById(R.id.img_profileItem_icon);
        }
    }

    public interface OnProfileItemClick{
        void onClick(String title);
    }
}
