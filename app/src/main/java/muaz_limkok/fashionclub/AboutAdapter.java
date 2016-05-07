package muaz_limkok.fashionclub;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.PersonViewHolder>{

    List<Person> persons;

    AboutAdapter(List<Person> persons){
        this.persons = persons;
    }


    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_layout, parent, false);
        PersonViewHolder ivh = new PersonViewHolder(v);
        return ivh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        holder.p_name.setText(persons.get(position).name);
        holder.p_id.setText(persons.get(position).id);
        holder.p_email.setText(persons.get(position).email);
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView p_name;
        TextView p_id;
        TextView p_email;


        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.p_cv);
            p_name = (TextView)itemView.findViewById(R.id.p_name);
            p_id = (TextView)itemView.findViewById(R.id.p_id);
            p_email = (TextView)itemView.findViewById(R.id.p_email);
        }
    }


}
