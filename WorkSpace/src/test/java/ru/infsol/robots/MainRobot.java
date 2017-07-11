package ru.infsol.robots;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Alexander Tumakov on 30.06.2017.
 */
public class MainRobot extends Robot{
    private static MainRobot r ;

    private MainRobot() throws AWTException {
        super();
    }

    public static MainRobot getInstanceOfRobot()throws AWTException{
        if(r==null){
            r = new MainRobot();
            return  r;
        }
        return r;
    }

    public void robotAuthorize() {


        keyPress(KeyEvent.VK_ENTER);
    }

}
