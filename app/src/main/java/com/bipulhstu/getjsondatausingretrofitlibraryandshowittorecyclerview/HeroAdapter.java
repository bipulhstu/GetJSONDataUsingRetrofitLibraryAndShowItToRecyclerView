package com.bipulhstu.getjsondatausingretrofitlibraryandshowittorecyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.ViewHolder> {
   private List<DemoData> list;
   Context context;
   MyClick myClick;

    public HeroAdapter() {
    }

    public HeroAdapter(List<DemoData> list, Context context, MyClick myClick) {
        this.list = list;
        this.context = context;
        this.myClick = myClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myClick.onClickMe(view, viewHolder.getPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final DemoData current = list.get(position);
        Picasso.get().load(current.getImg()).into(holder.image);
        holder.name.setText(current.getName());

        //Android RecyclerView onClick item without interface ****
       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailsActivity.class);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);

                //Send data to DetailsActivity using putExtra
                intent.putExtra("img", current.getImg());
                intent.putExtra("name", current.getName());
                intent.putExtra("description", current.getDescription());

                view.getContext().startActivity(intent);
            }
        });*/

        //Android RecyclerView onClick item using interface ***this is an efficient way

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textView);
            image = itemView.findViewById(R.id.imageView);
        }
    }
}
