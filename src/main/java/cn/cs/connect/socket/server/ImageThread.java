package cn.cs.connect.socket.server;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ImageThread implements Runnable{
    private DataOutputStream dos;
    private Robot robot;

    public ImageThread(DataOutputStream dos) {
        this.dos = dos;
    }

    @Override
    public void run() {
        try {
            robot = new Robot();
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle rectangle = new Rectangle(dimension);
            BufferedImage image;
            byte[] imageBytes;
            while (true) {
                image = robot.createScreenCapture(rectangle);
                imageBytes = getImage(image);
                dos.writeInt(imageBytes.length);
                dos.write(imageBytes);
                dos.flush();
                Thread.sleep(50);
            }
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(dos != null){
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private byte[] getImage(BufferedImage image) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outputStream);
        encoder.encode(image);
        return outputStream.toByteArray();
    }
}

