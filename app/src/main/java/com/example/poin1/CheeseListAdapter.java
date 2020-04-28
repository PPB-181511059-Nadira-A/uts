package com.example.poin1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CheeseListAdapter extends RecyclerView.Adapter<CheeseListAdapter.CheeseViewHolder> {

    class CheeseViewHolder extends RecyclerView.ViewHolder {
        private final TextView cheeseItemView;

        private CheeseViewHolder(View itemView) {
            super(itemView);
            cheeseItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Cheese> mCheese;

    CheeseListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public CheeseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recycler_item, parent, false);
        return new CheeseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CheeseViewHolder holder, int position) {
        if (mCheese != null) {
            Cheese current = mCheese.get(position);
            holder.cheeseItemView.setText(current.getCheese());
        } else {
            // Covers the case of data not being ready yet.
            holder.cheeseItemView.setText("No Word");
        }
    }

    void setCheese(List<Cheese> cheeses){
        mCheese = cheeses;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mCheese!= null)
            return mCheese.size();
        else return 0;
    }
}