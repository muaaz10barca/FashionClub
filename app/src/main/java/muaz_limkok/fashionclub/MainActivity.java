package muaz_limkok.fashionclub;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {


    RVAdapter rvAdapter;
    // lets start
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    RecyclerView rv;
    LinearLayoutManager linearLayoutManager;

    private List<Item> items;

    private GoogleApiClient mGoogleApiClient;

    public static Place userPlace = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeData();

        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);


        rvAdapter = new RVAdapter(items);
        rv.setAdapter(rvAdapter);

        FindUserBackground findUserBackground = new FindUserBackground();
        findUserBackground.execute();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.shitstuff);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                // when user clicks close the drawer
                drawerLayout.closeDrawers();

                if (item.getItemId() == R.id.map) {
                    Toast.makeText(MainActivity.this, "map", Toast.LENGTH_SHORT).show();
                    Intent mapIntent = new Intent(MainActivity.this, MapClass.class);
                    startActivity(mapIntent);
                }

                if (item.getItemId() == R.id.info) {
                    Toast.makeText(MainActivity.this, "information", Toast.LENGTH_SHORT).show();
                    Intent infoIntent = new Intent(MainActivity.this, Information.class);
                    startActivity(infoIntent);
                }

                if (item.getItemId() == R.id.about) {
                    Toast.makeText(MainActivity.this, "ab", Toast.LENGTH_SHORT).show();
                    Intent aboutIntent = new Intent(MainActivity.this, About.class);
                    startActivity(aboutIntent);
                }

                // rest of the menu

                return false;
            }
        });

        // setup drawer toggle
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name,
                R.string.app_name);

        // listening to the button
        drawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();


        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();



    }

    private class FindUserBackground extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {

            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return null;
            }
            PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi
                    .getCurrentPlace(mGoogleApiClient, null);
            result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
                @Override
                public void onResult(PlaceLikelihoodBuffer likelyPlaces) {
                    for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                        /*
                        Log.i("muaaz10", String.format("Place '%s' has likelihood: %g",
                                placeLikelihood.getPlace().getName(),
                                placeLikelihood.getLikelihood()));
                                */
                        userPlace = placeLikelihood.getPlace();
                        break;

                    }
                    likelyPlaces.release();
                    Log.i("muaaz10", (String) userPlace.getName());
                }
            });

            return null;
        }
    }


    private void initializeData(){
        items = new ArrayList<>();
        items.add(new Item("Basic V", "RM 139", R.drawable.basic_v_profile_139));
        items.add(new Item("Crocodile tee", "RM 139", R.drawable.crocodile_tee_profile_139));
        items.add(new Item("Disco fever top", "RM 79", R.drawable.disco_fever_top_profile_79));
        items.add(new Item("Double Zipper", "RM 229", R.drawable.double_zipper_profile_229));
        items.add(new Item("Essential Mesh Sleeves", "RM 199", R.drawable.ess_mesh_sleeves_sweater_fprofile_199));
        items.add(new Item("Essential Mesh Sleeves", "RM 199", R.drawable.ess_mesh_sleeves_sweater_profile_199));
        items.add(new Item("Fashion Club 10", "RM 99", R.drawable.fashionclub10_thumb_99));
        items.add(new Item("Leather Dungaree Dress", "RM 235", R.drawable.leather_dungaree_dress_profile_235));
        items.add(new Item("Mermaid Mesh Skirt", "RM 299", R.drawable.mermaid_mesh_skirt_prof_299));
        items.add(new Item("No Excuses", "RM 99", R.drawable.no_excuses_tee_profile_99));
        items.add(new Item("Snake Skin Sweater", "RM 198", R.drawable.snakeskinsweater_thumb_198));
        items.add(new Item("Windbreaker pocket sweater", "RM 229", R.drawable.windbreaker_pocket_sweater_profile_229));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}



