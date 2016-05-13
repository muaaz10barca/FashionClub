package muaz_limkok.fashionclub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

public class Cart extends AppCompatActivity {

    CartAdapter ca;
    RecyclerView rv;
    LinearLayoutManager linearLayoutManager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rv = (RecyclerView) findViewById(R.id.rv_cart);
        rv.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);


        ca = new CartAdapter(MainActivity.purchases);
        rv.setAdapter(ca);

    }
}
