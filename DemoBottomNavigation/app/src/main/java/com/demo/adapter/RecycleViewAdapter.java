package com.demo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.R;
import com.demo.model.Item;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder>{

    private List<Item>list;
    private ItemListenner itemListenner;
    public RecycleViewAdapter() {
        list=new ArrayList<>();
    }

    public void setItemListenner(ItemListenner itemListenner) {
        this.itemListenner = itemListenner;
    }

    public void setList(List<Item> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public Item getItem(int position){
        return list.get(position);
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Item item = list.get(position);
        holder.title.setText(item.getTitle());
        holder.category.setText(item.getCategory());
        holder.price.setText(item.getPrice());
        holder.date.setText(item.getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView title,category,price,date;
        public HomeViewHolder(@NonNull View view) {
            super(view);
            title=view.findViewById(R.id.tvTitle);
            category=view.findViewById(R.id.tvCategory);
            price=view.findViewById(R.id.tvPrice);
            date=view.findViewById(R.id.tvDate);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemListenner!=null){
                itemListenner.onItemClick(view,getAdapterPosition());
            }
        }
    }
    public interface ItemListenner{
        void onItemClick(View view, int position);
    }
}
