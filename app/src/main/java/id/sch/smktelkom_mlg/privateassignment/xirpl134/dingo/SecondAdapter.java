package id.sch.smktelkom_mlg.privateassignment.xirpl134.dingo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Yessy Permatasari on 5/12/2017.
 */
public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.ViewHolder> {

    private List<SecondListItem> secondListItems;
    private Context context;

    //model dari HomeList
    public SecondAdapter(List<SecondListItem> secondListItems, Context context) {
        this.secondListItems = secondListItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.second_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final SecondListItem secondListItem = secondListItems.get(position);

        holder.textViewJudul.setText(secondListItem.getJudul());
        //holder.imageViewBack.setImageURI(homeListItem.getImageUrl());
        //glide ini nnt

//
//
//        }
//        );
        Glide
                .with(context)
                .load(secondListItem.getBackdrop())
                .into(holder.imageViewBack);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "Klik " + homeListItem.getJudul(), Toast.LENGTH_LONG).show();
//                Intent singleBlogIntent = new Intent(context, HomeActivity.class);
//                singleBlogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                singleBlogIntent.putExtra("blog_id", position);
//                context.startActivity(singleBlogIntent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return secondListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewJudul;
        public ImageView imageViewBack;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewJudul = (TextView) itemView.findViewById(R.id.textViewJudul2);
            imageViewBack = (ImageView) itemView.findViewById(R.id.imageViewBack2);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.LinearLayout2);

        }
    }
}