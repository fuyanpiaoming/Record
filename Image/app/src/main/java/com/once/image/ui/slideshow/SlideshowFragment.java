package com.once.image.ui.slideshow;

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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.once.image.R;
import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {

    private List<SlideMode>slideModeList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel = ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        RecyclerView recyclerView = root.findViewById(R.id.slide_recycle_view);
        final SlideAdapter slideAdapter = new SlideAdapter(slideModeList,getContext());
        recyclerView.setAdapter(slideAdapter);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        //recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),RecyclerView.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        slideshowViewModel.getSlideList().observe(getViewLifecycleOwner(), new Observer<List<SlideMode>>() {
            @Override
            public void onChanged(List<SlideMode> slideModes) {
                slideModeList = slideModes;
                slideAdapter.setSlideModeList(slideModeList);
                slideAdapter.notifyDataSetChanged();
            }
        });
        return root;
    }

    private static class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.VH>{
        private List<SlideMode>slideModeList;
        private Context context;

        SlideAdapter(List<SlideMode> slideModes, Context context){
            slideModeList = slideModes;
            this.context = context;
        }

        void setSlideModeList(List<SlideMode> slideModes){
            slideModeList.clear();
            slideModeList.addAll(slideModes);
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_item,parent,false);
            return new VH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, final int position) {
            holder.imageView.setImageResource(slideModeList.get(position).getDrawable());
            holder.textView.setText(slideModeList.get(position).getName());
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(context,slideModeList.get(position).getClassName());
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return slideModeList.size();
        }

        private static class VH extends RecyclerView.ViewHolder{
            private ImageView imageView;
            private TextView textView;

            VH(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.slide_item_iv);
                textView = itemView.findViewById(R.id.slide_item_tv);
            }
        }
    }

}
