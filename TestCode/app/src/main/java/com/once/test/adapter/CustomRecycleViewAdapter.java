package com.once.test.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.once.test.R;
import com.once.test.mode.CustomMode;

import java.util.List;

public class CustomRecycleViewAdapter extends RecyclerView.Adapter<CustomRecycleViewAdapter.CustomViewHolder> {

    private List<CustomMode> customModeList;

    public CustomRecycleViewAdapter(List<CustomMode>list) {
        customModeList = list;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_item_port,viewGroup,false);
        final CustomViewHolder customViewHolder = new CustomViewHolder(view);
        final CustomMode customMode = customModeList.get(i);
        customViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(viewGroup.getContext(),"Click item " + customMode.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        customViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(viewGroup.getContext(),"Click itemview " + customModeList.get(customViewHolder.getAdapterPosition()).getName(),Toast.LENGTH_SHORT).show();
            }
        });
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {
        customViewHolder.imageView.setImageResource(customModeList.get(i).getId());
        customViewHolder.textView.setText(customModeList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return customModeList.size();
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image_view);
            textView = itemView.findViewById(R.id.item_text_view);
        }
    }
}
