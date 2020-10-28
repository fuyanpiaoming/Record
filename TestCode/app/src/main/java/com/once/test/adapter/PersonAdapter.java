package com.once.test.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.once.test.R;
import com.once.test.activity.TestCollapsingToolbarActivity;
import com.once.test.mode.Person;

import java.util.List;


public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    private Context context;
    private List<Person> personList;

    public PersonAdapter(List<Person>personList) {
        this.personList = personList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (context == null){
            context = viewGroup.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.person_item,viewGroup,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Person person = personList.get(position);
                Intent intent = new Intent(context, TestCollapsingToolbarActivity.class);
                intent.putExtra("name",person.getName());
                intent.putExtra("image_id",person.getImageId());
                context.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Person person = personList.get(i);
        viewHolder.textView.setText(person.getName());
        Glide.with(context).load(person.getImageId()).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView)itemView;
            imageView = cardView.findViewById(R.id.iv_image);
            textView = cardView.findViewById(R.id.tv_name);
        }
    }


}
