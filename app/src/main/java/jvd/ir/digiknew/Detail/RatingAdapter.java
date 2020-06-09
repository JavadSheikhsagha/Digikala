package jvd.ir.digiknew.Detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

import java.util.List;

import jvd.ir.digiknew.Model.Product;
import jvd.ir.digiknew.R;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.RatingViewHolder> {


    public RatingAdapter (List<Product> products){

    }


    @NonNull
    @Override
    public RatingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rating_item,parent,false);
        return new RatingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RatingViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class RatingViewHolder extends RecyclerView.ViewHolder{

        RoundCornerProgressBar progressBar;
        TextView progressTitle;

        public RatingViewHolder(@NonNull View itemView) {
            super(itemView);

            progressBar=itemView.findViewById(R.id.progress_rating_item);
            progressTitle=itemView.findViewById(R.id.rating_item_title);
        }
    }
}
