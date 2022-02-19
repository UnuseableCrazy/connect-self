package cn.cs.connect.socket.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8989);
        Socket socket = serverSocket.accept();
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        new Thread(new ImageThread(dos)).start();
        new Thread(new EventThread(new ObjectInputStream(socket.getInputStream()))).start();
    }
}
