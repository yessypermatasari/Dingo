package id.sch.smktelkom_mlg.privateassignment.xirpl134.dingo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Yessy Permatasari on 5/12/2017.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<HomeListItem> homeListItems;
    private Context context;

    //model dari HomeList
    public HomeAdapter(List<HomeListItem> homeListItems, Context context) {
        this.homeListItems = homeListItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final HomeListItem homeListItem = homeListItems.get(position);

        holder.textViewJudul.setText(homeListItem.getJudul());
        //holder.imageViewBack.setImageURI(homeListItem.getImageUrl());
        //glide ini nnt

//
//
//        }
//        );
        Glide
                .with(context)
                .load(homeListItem.getBackdrop())
                .into(holder.imageViewBack);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Klik " + homeListItem.getJudul(), Toast.LENGTH_LONG).show();
                Intent singleBlogIntent = new Intent(context, DetailActivity.class);
                singleBlogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                singleBlogIntent.putExtra("blog_id", position);
                context.startActivity(singleBlogIntent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return homeListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewJudul;
        public ImageView imageViewBack;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewJudul = (TextView) itemView.findViewById(R.id.textViewJudul);
            imageViewBack = (ImageView) itemView.findViewById(R.id.imageViewBack);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.LinearLayout);

        }
    }
}