package id.sch.smktelkom_mlg.privateassignment.xirpl134.dingo;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */

public class HomeFragment extends Fragment {
    private static final String URL_DATA = "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=0652326c89cd19f30e225c34439a580c";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<HomeListItem> listItems;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        listItems = new ArrayList<>();

        loadRecyclerViewData();


//        for(int i = 0; i<=10;i++) {
//            HomeListItem listItem = new HomeListItem(
//                    "heading" +(i+1),
//                    "ini desc"
//            );
//            listItems.add(listItem);
//        }
//        adapter = new HomeAdapter(listItems, getActivity());
//
//        recyclerView.setAdapter(adapter);
        return view;
    }

    private void loadRecyclerViewData() {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());

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


                            //JSONArray array = jsonObject.getJSONObject("data").getJSONArray("results");

                            JSONArray array = jsonObject.getJSONArray("results");

                            //JSONArray array2 = jsonObject.getJSONArray("multimedia");


                            for (int i = 0; i < array.length(); i++) {

                                JSONObject o = array.getJSONObject(i);

                                HomeListItem item = new HomeListItem(

                                        "https://image.tmdb.org/t/p/w500" + o.getString("poster_path"),

                                        o.getString("title")

                                );

                                listItems.add(item);

                            }

                            adapter = new HomeAdapter(listItems, getActivity().getApplicationContext());

                            recyclerView.setAdapter(adapter);


                        } catch (JSONException e) {

                            e.printStackTrace();

                        }


                    }

                },

                new Response.ErrorListener() {

                    @Override

                    public void onErrorResponse(VolleyError volleyError) {

                        progressDialog.dismiss();

                        Toast.makeText(getActivity().getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_LONG).show();


                    }

                });


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        requestQueue.add(stringRequest);

    }


}

