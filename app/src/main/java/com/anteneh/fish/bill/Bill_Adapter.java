package com.anteneh.fish.bill;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Bill_Adapter extends RecyclerView.Adapter<Bill_Adapter.MyViewHolder> implements Filterable {

    Context context;
    Activity activity;
    List<Bill_model> list, newList;
    int lay;

    public Bill_Adapter(Context context, Activity activity, List<Bill_model> list) {
        this.context = context;
        this.activity = activity;
        this.list = list;
        newList = new ArrayList<>(list);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        dark();
        View view = LayoutInflater.from(parent.getContext()).inflate(lay, parent, false);
        return new MyViewHolder(view);
    }
    private void dark(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean is_dark = preferences.getBoolean("enable_night", false);
        if (is_dark){
            lay = R.layout.recycle_holder_dark;
        }else{
            lay = R.layout.recycler_holder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.title.setText(list.get(holder.getAdapterPosition()).getTitle());
        holder.item1.setText(list.get(holder.getAdapterPosition()).getItem());
        holder.amount1.setText(list.get(holder.getAdapterPosition()).getAmount());
        holder.price1.setText(list.get(holder.getAdapterPosition()).getPrice());
        holder.product1.setText(list.get(holder.getAdapterPosition()).getProduct());
        holder.date.setText(list.get(holder.getAdapterPosition()).getDate());
        holder.time.setText(list.get(holder.getAdapterPosition()).getTime());

        holder.layout.setOnClickListener(view -> {
            Intent intent = new Intent(context, Update.class);
            intent.putExtra("title", list.get(position).getTitle());
            intent.putExtra("item1", list.get(position).getItem());
            intent.putExtra("amount1", list.get(position).getAmount());
            intent.putExtra("price1", list.get(position).getPrice());
            intent.putExtra("product1", list.get(position).getProduct());
            intent.putExtra("id", list.get(position).getId());
            activity.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Filter getFilter(){
        return filter;
    }
    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Bill_model> isFiltered = new ArrayList<>();
            if (charSequence.toString().isEmpty() || charSequence.toString().length() == 0) {
                isFiltered.addAll(newList);
            } else {
                String filterpattern = charSequence.toString().toLowerCase().trim();
                for (Bill_model model : list) {
                    if (model.getTitle().toLowerCase().contains(filterpattern)) {
                        isFiltered.add(model);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = isFiltered;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            list.clear();
            list.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, item1, amount1, price1, product1,date,time;
        RelativeLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.bill_title);
            item1 = itemView.findViewById(R.id.item1);
            amount1 = itemView.findViewById(R.id.amount1);
            price1 = itemView.findViewById(R.id.price1);
            product1 = itemView.findViewById(R.id.product1);
            layout = itemView.findViewById(R.id.adapter_layout);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);

        }
    }

    public List<Bill_model> getList() {
        return list;
    }

    public void removeBill(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }
    public void restore(Bill_model model,int position){
        list.add(position,model);
        notifyItemInserted(position);
    }
}








