package com.once.image.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.once.image.R;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private List<GalleryMode>galleryModeList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.gallery_recycle_view);
        final GalleryAdapter galleryAdapter = new GalleryAdapter(galleryModeList,getActivity());
        recyclerView.setAdapter(galleryAdapter);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        galleryViewModel.getGalleryModeList().observe(getViewLifecycleOwner(), new Observer<List<GalleryMode>>() {
            @Override
            public void onChanged(List<GalleryMode> galleryModes) {
                galleryModeList = galleryModes;
                galleryAdapter.setGalleryModeList(galleryModeList);
            }
        });
        return root;
    }

    private static class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.VH> {
        private List<GalleryMode> galleryModeList;
        private Context context;

        GalleryAdapter(List<GalleryMode> galleryModes, Context context) {
            galleryModeList = galleryModes;
            this.context = context;
        }

        void setGalleryModeList(List<GalleryMode> galleryModes) {
            galleryModeList.clear();
            galleryModeList.addAll(galleryModes);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item, parent, false);
            return new VH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull GalleryAdapter.VH holder, final int position) {
            holder.imageView.setImageResource(galleryModeList.get(position).getDrawable());
            holder.textView.setText(galleryModeList.get(position).getName());
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(context, galleryModeList.get(position).getClassName());
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return galleryModeList.size();
        }

        private static class VH extends RecyclerView.ViewHolder {
            private ImageView imageView;
            private TextView textView;

            VH(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.gallery_item_iv);
                textView = itemView.findViewById(R.id.gallery_item_tv);
            }
        }
    }
}
