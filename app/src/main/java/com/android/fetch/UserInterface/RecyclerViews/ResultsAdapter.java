package com.android.fetch.UserInterface.RecyclerViews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.fetch.Models.Results;
import com.android.fetch.R;

import java.util.List;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ViewHolder> {

    private List<Results> results;
    public ResultsAdapter(List<Results> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_result_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText("Name: " + results.get(position).getName());
        holder.id.setText("Id: " + results.get(position).getId());
        holder.listId.setText("ListId: " + results.get(position).getListId());

    }

    @Override
    public int getItemCount() {
        return results.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView name, listId, id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            listId = itemView.findViewById(R.id.listId);
            id = itemView.findViewById(R.id.id);
        }
    }
}
