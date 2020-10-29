package com.once.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.once.test.R;
import com.once.test.mode.CustomMode;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    
    private Context context;
    private List<CustomMode> customModeList;

    public CustomListAdapter(Context context,List<CustomMode>list) {
        this.context = context;
        this.customModeList = list;
    }

    @Override
    public int getCount() {
        return customModeList.size();
    }

    @Override
    public Object getItem(int position) {
        return customModeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if (convertView == null){
            view = LayoutInflater.from(context).inflate(R.layout.custom_item_land,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = view.findViewById(R.id.item_image_view);
            viewHolder.textView = view.findViewById(R.id.item_text_view);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.imageView.setImageResource(customModeList.get(position).getId());
        viewHolder.textView.setText(customModeList.get(position).getName());
        return view;
    }

    static class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
