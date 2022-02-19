package cn.cs.connect.socket.server;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;

public class EventThread implements Runnable{
    private ObjectInputStream ois;
    private Robot robot;

    public EventThread(ObjectInputStream ois) {
        this.ois = ois;
    }

    @Override
    public void run() {
        try {
            robot = new Robot();
            while (true) {
                InputEvent event = (InputEvent) ois.readObject();
                actionEvent(event);
            }
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void actionEvent(InputEvent event){
        if(event instanceof KeyEvent){
            KeyEvent keyEvent = (KeyEvent) event;
            int id = keyEvent.getID();
            if(id == Event.KEY_PRESS){
                robot.keyPress(keyEvent.getKeyCode());
            } else if(id == Event.KEY_RELEASE){
                robot.keyRelease(keyEvent.getKeyCode());
            }
        } else if(event instanceof MouseEvent){
            MouseEvent mouseEvent = (MouseEvent) event;
            int id = mouseEvent.getID();
            if(id == Event.MOUSE_MOVE){
                robot.mouseMove(mouseEvent.getX(),mouseEvent.getY());
            } else if(id == Event.MOUSE_DOWN){
                robot.mousePress(getMouseKey(id));
            } else if(id == Event.MOUSE_UP){
                robot.mouseRelease(getMouseKey(id));
            } else if(id == Event.MOUSE_DRAG){
                robot.mouseMove(mouseEvent.getX(),mouseEvent.getY());
            }
        }
    }

    private int getMouseKey(int button){
        if(button == MouseEvent.BUTTON1){
            return InputEvent.BUTTON1_MASK;
        } else if(button == MouseEvent.BUTTON2){
            return InputEvent.BUTTON2_MASK;
        } else if(button == MouseEvent.BUTTON3){
            return InputEvent.BUTTON3_MASK;
        } else {
            return 0;
        }
    }
}
