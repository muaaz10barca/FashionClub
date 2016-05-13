package muaz_limkok.fashionclub;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    List<Item> purchases;
    CartAdapter(List<Item> purchases){
        this.purchases = purchases;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        CartViewHolder ivh = new CartViewHolder(v);
        return ivh;
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        holder.name.setText(purchases.get(position).name);
        holder.price.setText(purchases.get(position).price);
        holder.image.setImageResource(purchases.get(position).photoId);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return purchases.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder{

        CardView cv;
        TextView name;
        TextView price;
        ImageView image;

        CartViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv_cart);
            name = (TextView)itemView.findViewById(R.id.name_cart);
            price = (TextView)itemView.findViewById(R.id.price_cart);
            image = (ImageView)itemView.findViewById(R.id.image_cart);
        }
    }
}
