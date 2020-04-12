package com.test.viewsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



import java.util.List;

public class MyAdapter extends BaseAdapter {

    private List<String>nameList;
    private Context mContext;

    MyAdapter(Context context, List<String> list) {
        super();
        mContext = context;
        nameList = list;
    }

    @Override
    public int getCount() {
        return nameList.size();
    }

    @Override
    public Object getItem(int position) {
        return nameList.get(position);
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
            view = LayoutInflater.from(mContext).inflate(R.layout.main_list_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.textView = view.findViewById(R.id.tv_test_name);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textView.setText(nameList.get(position));
        return view;
    }

    static class ViewHolder{
        TextView textView;
    }

}
