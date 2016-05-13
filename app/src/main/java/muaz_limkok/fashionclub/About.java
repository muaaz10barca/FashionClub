package muaz_limkok.fashionclub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class About extends AppCompatActivity {

    List<Person> persons;
    RecyclerView rv;
    LinearLayoutManager linearLayoutManager;
    AboutAdapter aboutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);

        persons = new ArrayList<>();
        setUpData();
        rv = (RecyclerView)findViewById(R.id.rv_person);
        rv.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);


        aboutAdapter = new AboutAdapter(persons);
        rv.setAdapter(aboutAdapter);
    }

    private void setUpData(){

        persons.add(new Person("Muaadh Mohammed Salem","11004219","muaaz10barca@gmail.com"));
        persons.add(new Person("Ala Mahmood Abullah Hassan","110042593","alamahmoodhassan@gmail.com"));
        persons.add(new Person("Islam bouhaha","110042201","ibouhaha67@gmail.com"));
        persons.add(new Person("Alaa Aldeen Alobahi","110042803","alaa12alobahi@gmail.com"));

    }
}
