package id.sch.smktelkom_mlg.privateassignment.xirpl134.dingo;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import id.sch.smktelkom_mlg.privateassignment.xirpl134.dingo.Sugar.Place;

public class SecondActivity extends AppCompatActivity {
    private static final String URL_DATA = "https://api.themoviedb.org/3/discover/movie?with_genres=18&sort_by=vote_average.desc&vote_count.gte=10&api_key=0652326c89cd19f30e225c34439a580c";
    public TextView textViewJudul;
    public TextView textViewTerbit;
    public TextView textViewOverView;
    public ImageView imageViewDetail;
    public String Backdrop;
    public Button btnRate;
    public Spinner spinnerRating;
    public byte[] gambar = new byte[2048];
    Place place;
    boolean isPressed = true;
    //    FloatingActionButton fab;
    boolean isNew;
    ArrayList<Place> pItem;
    JSONObject o = null;
    private Integer mPostkey = null;

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPostkey = getIntent().getExtras().getInt("blog_id");

        loadRecyclerViewData();
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Uri uri = Uri.parse(url); // missing 'http://' will cause crashed
////
////                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
////
////                startActivity(intent);
//
//            }
//
//        });


        textViewJudul = (TextView) findViewById(R.id.textViewJudul);
        textViewTerbit = (TextView) findViewById(R.id.textViewTerbit);
        textViewOverView = (TextView) findViewById(R.id.textViewOverView);
        imageViewDetail = (ImageView) findViewById(R.id.imageViewBack);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spinnerRating = (Spinner) findViewById(R.id.spinnerRating);
        btnRate = (Button) findViewById(R.id.btnRate);

        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPressed) {
                    doSave();
                    Snackbar.make(view, "Rating Berhasil Ditambahkan", Snackbar.LENGTH_LONG)

                            .setAction("Action", null).show();
                } else {
                    Snackbar.make(view, "Rating Gagal", Snackbar.LENGTH_LONG)

                            .setAction("Action", null).show();
                }
                isPressed = !isPressed;
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void doSave() {
        String overview = textViewOverView.getText().toString();
        String terbit = textViewTerbit.getText().toString();
        String judul = textViewJudul.getText().toString();
        byte[] backdrop = gambar;
        String rate = spinnerRating.getSelectedItem().toString();

        place = new Place(overview, terbit, judul, backdrop, rate);
        place.save();
    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,

                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("results");
                            o = array.getJSONObject(mPostkey);


                            setTitle(" ");


                            textViewJudul.setText(o.getString("title"));
                            textViewTerbit.setText(o.getString("release_date"));
                            textViewOverView.setText(o.getString("overview"));

//                            url = o.getJSONObject("link").getString("url");

                            Glide

                                    .with(SecondActivity.this)
                                    .load("https://image.tmdb.org/t/p/w500" + o.getString("backdrop_path"))
                                    .into(imageViewDetail);

                            new AsyncTask<Void, Void, Void>() {

                                @Override
                                protected Void doInBackground(Void... params) {
                                    try {
                                        Bitmap bitmap = Glide.
                                                with(getApplicationContext()).
                                                load("https://image.tmdb.org/t/p/w500" + o.getString("backdrop_path")).
                                                asBitmap().
                                                into(500, 500).get();
                                        gambar = getBytes(bitmap);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    } catch (ExecutionException e) {
                                        e.printStackTrace();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    return null;
                                }
                            }.execute();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
