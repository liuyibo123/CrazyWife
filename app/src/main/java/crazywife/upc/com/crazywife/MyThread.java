package crazywife.upc.com.crazywife;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import crazywife.upc.com.crazywife.event.ChangedEvent;

/**
 * Created by Administrator on 2017/4/17.
 */

public class MyThread extends Thread{
    byte SendBuf[] = { 0x3A, 0x00, 0x01, 0x0A, 0x00, 0x00, 0x23, 0x00 };
    private Socket socket;
    private OutputStream outputStream = null;
    private InputStream inputStream = null;
    private SocketAddress socketAddress;
    final int SERVER_PORT = 33333;
    int ucRecvLen = 7;

    void connect() {
        socketAddress = new InetSocketAddress("192.168.1.103", SERVER_PORT);
        socket = new Socket();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        socket.connect(socketAddress);
                        inputStream = socket.getInputStream();
                        outputStream = socket.getOutputStream();
                      //  58 0 -1 1 25 32 0 1 0 0 0 1 0 0 0 1 0 0 0 1 -3 35
                      //  58 0 -1 1 25 32 0 0 0 0 0 1 0 0 0 1 0 0 0 1 -4 35 0 0 0 0 0 0 0
                        int b;
                        while(true){
                            outputStream.write(DeviceCode.Search);
                            byte strRxBuf[] = new byte[30];
                            inputStream.read(strRxBuf);
                            EventBus.getDefault().post(new ChangedEvent(strRxBuf[4],strRxBuf[5],strRxBuf[6],strRxBuf[7]));
                            sleep(1000);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();




    }

    public void run() {
        connect();
    }
}
