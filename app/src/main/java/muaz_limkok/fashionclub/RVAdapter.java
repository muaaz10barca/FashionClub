package muaz_limkok.fashionclub;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ItemViewHolder>{

    List<Item> items;
    Context context;
    RVAdapter(List<Item> items, Context context){

        this.items = items;
        this.context = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_template, parent, false);
        ItemViewHolder ivh = new ItemViewHolder(v);
        return ivh;

    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        holder.name.setText(items.get(position).name);
        holder.price.setText(items.get(position).price);
        holder.image.setImageResource(items.get(position).photoId);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialog pop up
                new MaterialDialog.Builder(context)
                        .title("Add to cart")
                        .content(items.get(position).name)
                        .positiveText("Add")
                        .negativeText("Cancel")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                //
                                MainActivity.purchases.add(items.get(position));
                                Toast.makeText(context,"Added To card successfully",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView name;
        TextView price;
        ImageView image;

        ItemViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            name = (TextView)itemView.findViewById(R.id.name);
            price = (TextView)itemView.findViewById(R.id.price);
            image = (ImageView)itemView.findViewById(R.id.image);
        }
    }

}