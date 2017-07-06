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


        delay(10 *1000);

        keyPress(KeyEvent.VK_F);
        keyPress(KeyEvent.VK_I);
        keyPress(KeyEvent.VK_S);
        keyPress(KeyEvent.VK_M);
        keyPress(KeyEvent.VK_SHIFT);
        keyPress(KeyEvent.VK_MINUS);
        keyRelease(KeyEvent.VK_SHIFT);
        keyPress(KeyEvent.VK_V);
        keyPress(KeyEvent.VK_I);
        keyPress(KeyEvent.VK_E);
        keyPress(KeyEvent.VK_W);
        keyPress(KeyEvent.VK_E);
        keyPress(KeyEvent.VK_R);

        keyPress(KeyEvent.VK_TAB);

        keyPress(KeyEvent.VK_SHIFT);
        keyPress(KeyEvent.VK_S);
        keyRelease(KeyEvent.VK_SHIFT);
        keyPress(KeyEvent.VK_U);
        keyPress(KeyEvent.VK_P);
        keyPress(KeyEvent.VK_E);
        keyPress(KeyEvent.VK_R);
        keyPress(KeyEvent.VK_SHIFT);
        keyPress(KeyEvent.VK_I);
        keyRelease(KeyEvent.VK_SHIFT);
        keyPress(KeyEvent.VK_N);
        keyPress(KeyEvent.VK_F);
        keyPress(KeyEvent.VK_SHIFT);
        keyPress(KeyEvent.VK_S);
        keyRelease(KeyEvent.VK_SHIFT);
        keyPress(KeyEvent.VK_O);
        keyPress(KeyEvent.VK_L);
        keyPress(KeyEvent.VK_2);
        keyPress(KeyEvent.VK_0);
        keyPress(KeyEvent.VK_1);
        keyPress(KeyEvent.VK_7);

        keyPress(KeyEvent.VK_ENTER);
    }

}
