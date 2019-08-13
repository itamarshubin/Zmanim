package com.example.tabs;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ZmanimListAdapter extends RecyclerView.Adapter<ZmanimViewHolder> {

    private List<cell> names;
    private zmanClickListener listener;

    public ZmanimListAdapter(List<cell> names, zmanClickListener listener) {
        this.names = names;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ZmanimViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ZmanimViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.zman_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ZmanimViewHolder holder, final int position) {
        holder.title.setText(names.get(position).getTitle());
        holder.time.setText(names.get(position).getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.zmanClicked(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return names.size();
    }
}
