package com.example.tabs;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ZmanimViewHolder extends RecyclerView.ViewHolder {

    TextView title;
    TextView time;

    public ZmanimViewHolder(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.title);
        time = itemView.findViewById(R.id.time);
    }
}
