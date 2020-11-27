package com.once.test.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.once.test.R;
import com.once.test.mode.ChatMessage;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private List<ChatMessage> messageList;


    static class ChatViewHolder extends RecyclerView.ViewHolder {
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView tvLeft;
        TextView tvRight;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            leftLayout = itemView.findViewById(R.id.left_layout);
            rightLayout = itemView.findViewById(R.id.right_layout);
            tvLeft = itemView.findViewById(R.id.tv_left);
            tvRight = itemView.findViewById(R.id.tv_right);
        }
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_chat_layout, viewGroup, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder chatViewHolder, int position) {
        ChatMessage chatMessage = messageList.get(position);
        if (chatMessage.getType() == ChatMessage.TYPE_RECEIVED) {
            chatViewHolder.leftLayout.setVisibility(View.VISIBLE);
            chatViewHolder.rightLayout.setVisibility(View.GONE);
            chatViewHolder.tvLeft.setText(chatMessage.getContent());
        } else if (chatMessage.getType() == ChatMessage.TYPE_SEND) {
            chatViewHolder.leftLayout.setVisibility(View.GONE);
            chatViewHolder.rightLayout.setVisibility(View.VISIBLE);
            chatViewHolder.tvRight.setText(chatMessage.getContent());
        }
    }

    public ChatAdapter(List<ChatMessage> list) {
        messageList = list;
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}
