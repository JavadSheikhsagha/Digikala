package jvd.ir.digiknew.EditProfile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import jvd.ir.digiknew.Model.Profile;
import jvd.ir.digiknew.R;

public class EditProfileAdapter extends RecyclerView.Adapter<EditProfileAdapter.EditProfileViewHolder> {

    List<Profile> profiles;
    Context context;
    EditProfileOnClick editProfileOnClick;

    public EditProfileAdapter(Context context, List<Profile> profiles,EditProfileOnClick editProfileOnClick){
        this.profiles=profiles;
        this.context=context;
        this.editProfileOnClick=editProfileOnClick;
    }

    @NonNull
    @Override
    public EditProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.edt_profile,parent,false);
        return new EditProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EditProfileViewHolder holder, final int position) {
        Profile profile=profiles.get(position);
        holder.txtMenu.setText(profile.getTitle());
        holder.imgMenu.setImageResource(profile.getIcon());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfileOnClick.onClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    public class EditProfileViewHolder extends RecyclerView.ViewHolder{

        ImageView imgMenu;
        TextView txtMenu;
        RelativeLayout parent;

        public EditProfileViewHolder(@NonNull View itemView) {
            super(itemView);

            imgMenu = itemView.findViewById(R.id.img_edtProfile_menu);
            txtMenu = itemView.findViewById(R.id.txt_edtProfile_menu);
            parent = itemView.findViewById(R.id.rel_edtProfile_parent);
        }
    }

    public interface EditProfileOnClick{
        void onClick(int position);
    }
}
