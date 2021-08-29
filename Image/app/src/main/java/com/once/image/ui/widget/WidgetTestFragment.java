package com.once.image.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.once.image.R;

import java.util.ArrayList;
import java.util.List;


public class WidgetTestFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        WidgetViewModel widgetViewModel = ViewModelProviders.of(this).get(WidgetViewModel.class);
        View root = inflater.inflate(R.layout.fragment_widget, container, false);

        final RecyclerView recyclerView = root.findViewById(R.id.widget_recycle);
        final MyAdapter myAdapter = new MyAdapter(getContext());
        recyclerView.setAdapter(myAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        widgetViewModel.setTestBean();
        widgetViewModel.getTestBean().observe(getViewLifecycleOwner(), new Observer<List<WidgetMode>>() {
            @Override
            public void onChanged(List<WidgetMode> widgetModes) {
                //update ui
                myAdapter.setWidgetModes(widgetModes);
                myAdapter.notifyDataSetChanged();
            }
        });

        return root;
    }


    private static class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {

        private List<WidgetMode> mWidgetModes;
        private Context mContext;

        static class VH extends RecyclerView.ViewHolder {
            TextView textView;

            public VH(@NonNull View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.tv_test_item);
            }
        }

        MyAdapter(Context context) {
            mContext = context;
            mWidgetModes = new ArrayList<>();
        }

        void setWidgetModes(List<WidgetMode> widgetModes) {
            mWidgetModes.clear();
            mWidgetModes.addAll(widgetModes);
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_item, parent, false);
            return new VH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, final int position) {
            holder.textView.setText(mWidgetModes.get(position).getName());
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, mWidgetModes.get(position).getClassName());
                    mContext.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mWidgetModes.size();
        }
    }

}
