package com.once.test.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.once.test.R;
import com.once.test.adapter.ChatAdapter;
import com.once.test.mode.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private List<ChatMessage> messageList = new ArrayList<>();
    private ChatAdapter chatAdapter;
    private EditText editText;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initMessage();

        editText = findViewById(R.id.edit_msg);

        recyclerView = findViewById(R.id.chat_recycle_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        chatAdapter = new ChatAdapter(messageList);
        recyclerView.setAdapter(chatAdapter);

        findViewById(R.id.btn_send_msg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editText.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    ChatMessage chatMessage = new ChatMessage(ChatMessage.TYPE_SEND, content);
                    messageList.add(chatMessage);
                    String receive = "回复:" + content;
                    ChatMessage chatMessage1 = new ChatMessage(ChatMessage.TYPE_RECEIVED, receive);
                    messageList.add(chatMessage1);
                    chatAdapter.notifyDataSetChanged();
                    recyclerView.scrollToPosition(messageList.size() - 1);
                    editText.setText("");
                }
                Toast.makeText(ChatActivity.this, "Click send", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initMessage() {
        ChatMessage chatMessage = new ChatMessage(ChatMessage.TYPE_RECEIVED, "你好");
        messageList.add(chatMessage);

        ChatMessage chatMessage2 = new ChatMessage(ChatMessage.TYPE_SEND, "你好啊");
        messageList.add(chatMessage2);

        ChatMessage chatMessage1 = new ChatMessage(ChatMessage.TYPE_RECEIVED, "在干嘛呢");
        messageList.add(chatMessage1);

        ChatMessage chatMessage3 = new ChatMessage(ChatMessage.TYPE_SEND, "看书");
        messageList.add(chatMessage3);

    }
}