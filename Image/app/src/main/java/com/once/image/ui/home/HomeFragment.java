package com.once.image.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.once.image.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private List<HomeMode> homeModeList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.home_recycle_view);
        final HomeAdapter homeAdapter = new HomeAdapter(getActivity(), homeModeList);
        recyclerView.setAdapter(homeAdapter);
        homeViewModel.getHomeModeList().observe(getViewLifecycleOwner(), new Observer<List<HomeMode>>() {
            @Override
            public void onChanged(List<HomeMode> homeModes) {
                homeModeList = homeModes;
                homeAdapter.setHomeModeList(homeModeList);
            }
        });
        return root;
    }

    private static class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.VH> {

        private List<HomeMode> homeModeList;
        private Context context;

        public HomeAdapter(Context context, List<HomeMode> list) {
            this.context = context;
            homeModeList = list;
        }

        public void setHomeModeList(List<HomeMode> list) {
            homeModeList.clear();
            homeModeList.addAll(list);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, parent, false);
            return new VH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, final int position) {
            holder.imageView.setImageResource(homeModeList.get(position).getDrawable());
            holder.textView.setText(homeModeList.get(position).getName());
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, homeModeList.get(position).getClassName());
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return homeModeList.size();
        }

        private static class VH extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView textView;

            public VH(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.home_item_iv);
                textView = itemView.findViewById(R.id.home_item_tv);
            }
        }
    }


}
