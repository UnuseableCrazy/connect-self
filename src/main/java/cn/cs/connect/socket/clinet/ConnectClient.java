package cn.cs.connect.socket.clinet;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8989);
        if(socket.isConnected()){
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ClientWindow clientWindow = new ClientWindow(oos);
            byte[] imageBytes;
            while (true){
                imageBytes  = new byte[dis.readInt()];
                dis.readFully(imageBytes);
                clientWindow.repaintImage(imageBytes);
            }

        }
    }
}
