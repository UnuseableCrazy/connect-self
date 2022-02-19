package cn.cs.connect.socket.clinet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ClientWindow extends JFrame {
    private ObjectOutputStream oos;
    private JLabel jLabel;

    public void repaintImage(byte[] imageBytes) {
        jLabel.setIcon(new ImageIcon(imageBytes));
        this.repaint();
    }

    public ClientWindow(ObjectOutputStream oos){
        this.oos = oos;
        this.setTitle("远程控制程序");
        jLabel = new JLabel();
        JPanel jPanel = new JPanel();
        jPanel.add(jLabel);
        JScrollPane jScrollPane = new JScrollPane(jPanel);
        this.add(jScrollPane);
        this.setSize(1960,1080);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                sendEvent(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                sendEvent(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                sendEvent(e);
            }
        });
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sendEvent(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                sendEvent(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                sendEvent(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                sendEvent(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                sendEvent(e);
            }
        });
    }

    private void sendEvent(InputEvent event){
        try {
            oos.writeObject(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
