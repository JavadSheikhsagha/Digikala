package jvd.ir.digiknew.CompareProduct;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import jvd.ir.digiknew.R;

public class SearchCompareProductAdapter extends RecyclerView.Adapter<SearchCompareProductAdapter.SearchCompareProductViewHolder> {

    Context context;
    SearchCompareOnClickListener onClickListener;

    public SearchCompareProductAdapter(Context context,SearchCompareOnClickListener searchCompareOnClickListener){
        this.context=context;
        this.onClickListener=searchCompareOnClickListener;

    }

    @NonNull
    @Override
    public SearchCompareProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.compare_list_search,parent,false);
        return new SearchCompareProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchCompareProductViewHolder holder, int position) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onClickListener.onClickListener(product.getProduct);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SearchCompareProductViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView txtTitle;
        CardView cardView;

        public SearchCompareProductViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img_compareSearch_image);
            cardView=itemView.findViewById(R.id.Card_compareSearch_Items);
            txtTitle= itemView.findViewById(R.id.txt_compareSearch_title);
        }
    }
    public interface SearchCompareOnClickListener{
        void onClickListener(String search);
    }
}
