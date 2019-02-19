package de.reclinarka.util;

import java.awt.event.KeyEvent;

public class KeyBoardUtil {
    public static String getRelevantKey(KeyEvent e) {
        char ch = e.getKeyChar();

        if(((e.getKeyCode() == KeyEvent.VK_C) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0))){
            return "";
        } else if(((e.getKeyCode() == KeyEvent.VK_V) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0))){
            return "";
        }

        if (Character.isLetterOrDigit(ch) || Character.isDefined(ch)) {
            return "" + ch;
        } else {
            return "";
        }


    }
}
