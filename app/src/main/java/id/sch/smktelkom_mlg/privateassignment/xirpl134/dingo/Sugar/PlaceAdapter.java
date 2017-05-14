package id.sch.smktelkom_mlg.privateassignment.xirpl134.dingo.Sugar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl134.dingo.R;

/**
 * Created by Yessy Permatasari on 5/14/2017.
 */

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {

    private final Context context;
    ArrayList<Place> pItem;
    IPlaceAdapter iPlaceAdapter;

    public PlaceAdapter(ArrayList<Place> place, Context context) {
        this.pItem = place;
        this.context = context;

    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rate1_item, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;

    }

//    @Override
//    public PlaceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return null;
//    }

    @Override
    public void onBindViewHolder(PlaceAdapter.ViewHolder holder, int position) {
        final Place place = pItem.get(position);

        holder.textViewJudulRate1.setText(place.judul);

        holder.textViewTerbitRate1.setText(place.terbit);

        holder.textViewOverviewRate1.setText(place.overview);

        holder.textViewRate1.setText(place.rate);

        Glide

                .with(context)

                .load("https://image.tmdb.org/t/p/w500" + place.backdrop)

                .into(holder.imageViewBackdropRate1);

//        holder.buttonDelete.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//
//            public void onClick(View view) {
//
//                final FavouriteItem favouriteItem1 = fItem.get(position);
//
//                fItem.remove(position);
//
//                favouriteItem1.delete();
//
//                FavouriteAdapter.this.notifyDataSetChanged();
//
//
//
//            }
//
//        });
    }

    @Override
    public int getItemCount() {
        return pItem.size();
    }

    public interface IPlaceAdapter {
        //      void doDelete(int pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewJudulRate1;
        public TextView textViewTerbitRate1;
        public TextView textViewOverviewRate1;
        public TextView textViewRate1;
        public ImageView imageViewBackdropRate1;


        public ViewHolder(View itemView) {
            super(itemView);

            textViewJudulRate1 = (TextView) itemView.findViewById(R.id.textViewJudulRate1);
            textViewTerbitRate1 = (TextView) itemView.findViewById(R.id.textViewTerbitRate1);
            textViewOverviewRate1 = (TextView) itemView.findViewById(R.id.textViewOverviewRate1);
            textViewRate1 = (TextView) itemView.findViewById(R.id.textViewRate1);
            imageViewBackdropRate1 = (ImageView) itemView.findViewById(R.id.imageViewBackDropRate1);
        }
    }
}
