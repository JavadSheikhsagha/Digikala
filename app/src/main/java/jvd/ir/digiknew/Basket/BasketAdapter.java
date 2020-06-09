package jvd.ir.digiknew.Basket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import jvd.ir.digiknew.Model.BasketProduct;
import jvd.ir.digiknew.R;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.BasketViewHolder> {

    List<BasketProduct> baskets;

    onCardClick onCardClick;

    onDeleteClick onDeleteClick;

    onTotalPrice onTotalPrice;

    public BasketAdapter(List<BasketProduct> baskets,onCardClick onCardClick,onDeleteClick onDeleteClick,onTotalPrice onTotalPrice) {
        this.baskets = baskets;
        this.onCardClick=onCardClick;
        this.onDeleteClick=onDeleteClick;
        this.onTotalPrice=onTotalPrice;
    }

    @NonNull
    @Override
    public BasketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.basket_layout, parent, false);
        return new BasketViewHolder(view);
    }

    public void deleteRow(BasketProduct basketProduct){
        int index=baskets.indexOf(basketProduct);
        baskets.remove(index);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull BasketViewHolder holder, final int position) {
        final BasketProduct basketProduct = baskets.get(position);
        holder.txtTitle.setText(basketProduct.getTitle());
        holder.txtGuarantee.setText("product");
        Picasso.get().load(basketProduct.getImage()).into(holder.image);
        holder.txtPrePrice.setText(basketProduct.getPrice()+" تومان ");
        holder.txtPrice.setText(basketProduct.getPrice()+" تومان ");
        holder.txtSeller.setText("Digikala");

        onTotalPrice.onPrice(basketProduct.getPrice());

        holder.txtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteClick.onClick(basketProduct);

            }
        });

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCardClick.onClick(basketProduct.getProductid());
            }
        });


    }

    @Override
    public int getItemCount() {
        return baskets.size();
    }

    public class BasketViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView txtTitle, txtGuarantee, txtSeller, txtPrePrice, txtPrice, txtDelete;
        CardView parent;
        Spinner spinner;
        String[] amount;

        public BasketViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img_bl_image);
            txtTitle = itemView.findViewById(R.id.txt_bl_title);
            txtSeller = itemView.findViewById(R.id.txt_bl_seller);
            txtPrice = itemView.findViewById(R.id.txt_bl_price_after);
            txtDelete = itemView.findViewById(R.id.txt_bl_delete);
            txtPrePrice = itemView.findViewById(R.id.txt_bl_price_before);
            txtGuarantee = itemView.findViewById(R.id.txt_bl_guarantee);
            parent = itemView.findViewById(R.id.card_bl_parent);
            spinner = itemView.findViewById(R.id.spinner_bl_spinner);

        }
    }

    public interface onCardClick{
        void onClick(String productId);
    }

    public interface onDeleteClick{
        void onClick(BasketProduct basket);
    }

    public interface onTotalPrice{
        void onPrice(String price);
    }
}
