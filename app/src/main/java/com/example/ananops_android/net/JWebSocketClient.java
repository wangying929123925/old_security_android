package com.example.ananops_android.net;

import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Map;

public class JWebSocketClient extends WebSocketClient {

   // private Map<String, String> header = new HashMap<>();
    public JWebSocketClient(URI serverUri,Map<String,String> httpHeaders) {
        super(serverUri,  new Draft_6455(),httpHeaders);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        Log.e("onOpen:", "------连接成功!!!");
    }

    @Override
    public void onMessage(String s) {
        Log.e("webSocket收到信息:", s);

    }

    @Override
    public void onClose(int i, String s, boolean b) {
        Log.e("onClose:", "------连接关闭!!!" +s);
    }

    @Override
    public void onError(Exception e) {
        {
            Log.d("onError:", e.toString());
        }
    }
}
