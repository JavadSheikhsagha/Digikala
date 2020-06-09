package jvd.ir.digiknew.Main;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.logging.Handler;

import jvd.ir.digiknew.Model.Product;
import jvd.ir.digiknew.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    public List<Product> products;

    public Product product;

    ProductViewHolder productViewHolder;

    OnProductClick onProductClick;

    MainActivity mainActivity;

    public ProductAdapter(List<Product> products,OnProductClick onProductClick){
        this.products=products;
        this.onProductClick=onProductClick;

    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_list,parent,false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, int position) {
        product=products.get(position);
        holder.id=product.getId();
        //////////////////////////////////////////////////holder :| :| :| :|  Fuck!
        //Log.e("LOG6", "onBindViewHolder: "+product.getTitle()+" "+product.getPrice()+product.getPPrice()+product.getImage() );
        Picasso.get().load(product.getImage()).into(holder.imgView);
        holder.txtTitle.setText(product.getTitle());
        holder.txtPrice.setText(product.getPrice());
        SpannableString spannableString=new SpannableString(product.getPPrice());
        spannableString.setSpan(new StrikethroughSpan(),0,product.getPPrice().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.txtpPrice.setText(spannableString);
        holder.cardParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onProductClick.onClick(holder.id);
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        ImageView imgView;
        String id;
        TextView txtTitle,txtpPrice,txtPrice;
        CardView cardParent;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            cardParent=itemView.findViewById(R.id.card_product_item_parent);
            imgView=itemView.findViewById(R.id.img_product_image);
            txtTitle=itemView.findViewById(R.id.txt_product_name);
            txtpPrice=itemView.findViewById(R.id.txt_product_pPrice);
            txtPrice=itemView.findViewById(R.id.txt_product_price);


        }
    }

    public interface OnProductClick{
        void onClick(String id);
    }


}
