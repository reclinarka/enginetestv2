package de.reclinarka.objects.controlElements;

import de.reclinarka.objects.interaction.EventType;
import de.reclinarka.util.ClipboardGetter;
import de.reclinarka.util.ColorStorage;
import de.reclinarka.util.KeyBoardUtil;
import de.reclinarka.util.StringTrimmer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Textfield extends ControlElement {
    public Textfield(int x, int y, int width, int height, String ID) {
        super(ID);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    private Color mainColor = ColorStorage.defaultSlider;
    private Color borderColor = ColorStorage.defaultSliderAlt;
    private Color clickedBorder = ColorStorage.defaultTextfieldBorder;

    private boolean clicked = false;
    private boolean highlighted = false;

    private int size = 16;
    private String defaultText = "Enter Command to execute here";
    private String content = "";
    private String acceptedCommand = "txt_default";
    private String thrownCommand = "txt_default";

    private int x;
    private int y;
    private int width;
    private int height;

    @Override
    public void exec(Graphics g) {
        if(!highlighted)
            g.setColor(mainColor);
        else
            g.setColor(borderColor);
        g.fillRect(x, y, width, height);
        if (!clicked)
            g.setColor(borderColor);
        else
            g.setColor(clickedBorder);
        g.drawRect(x, y, width, height);

        Font tmp = g.getFont();
        this.drawText(g);
        g.setFont(tmp);

        super.exec(g);

        if(isDebugMode())
            g.drawString(getID(),x,y);
    }

    @Override
    public void mouseEvent(MouseEvent e, EventType type, String ID) {
        super.mouseEvent(e, type, ID);
        if (isInbound(new int[]{x, y}, new int[]{width, height}, new int[]{e.getX(), e.getY()})) {
            highlighted = true;
        } else {
            highlighted = false;
        }

        if (e.getButton() == 1)
            if(type == EventType.Mouse_Clicked)
                if(highlighted)
                    clicked = true;
                else
                    clicked = false;


    }

    private void drawText(Graphics g) {
        String content = "{ " + this.content + " }";
        Graphics2D g2 = (Graphics2D) g;
        g.setFont(new Font(null, g.getFont().getStyle(), size));
        if (this.content.isEmpty() && !defaultText.isEmpty()) {
            g.setColor(ColorStorage.defaultTextfieldBorder);
            if (((int) g.getFont().getStringBounds(defaultText, g2.getFontRenderContext()).getWidth()) + 5 <= width) {

                g.drawString(defaultText, x + 2, y + (int) (((double) height) * (22.0 / 30.0)));
                return;
            } else {
                for (int i = 0; i < defaultText.length(); i++) {
                    if (((int) g.getFont().getStringBounds("..." + defaultText.substring(i, defaultText.length()), g2.getFontRenderContext()).getWidth()) + 5 <= width) {
                        g.drawString("..." + defaultText.substring(i, defaultText.length()), x + 2, y + (int) (((double) height) * (22.0 / 30.0)));
                        return;
                    }
                }
            }
        }

        g.setColor(ColorStorage.white);


        if (((int) g.getFont().getStringBounds(content, g2.getFontRenderContext()).getWidth()) + 5 <= width) {

            g.drawString(content, x + 2, y + (int) (((double) height) * (22.0 / 30.0)));
        } else {
            for (int i = 0; i < content.length(); i++) {
                if (((int) g.getFont().getStringBounds("..." + content.substring(i, content.length()), g2.getFontRenderContext()).getWidth()) + 5 <= width) {
                    g.drawString("..." + content.substring(i, content.length()), x + 2, y + (int) (((double) height) * (22.0 / 30.0)));
                    return;
                }
            }
        }
        g.setColor(ColorStorage.defaultColor);
    }

    @Override
    public void keyEvent(KeyEvent e, EventType eventType, String ID) {
        super.keyEvent(e, eventType, ID);
        if (clicked) {
            switch (eventType) {
                case Key_Pressed:
                    if (((e.getKeyCode() == KeyEvent.VK_C) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0))) {
                        ClipboardGetter.setClipboardContents(content);
                        return;
                    } else if (((e.getKeyCode() == KeyEvent.VK_V) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0))) {
                        content = content + ClipboardGetter.getClipboardContents();
                        return;
                    } else if (e.getKeyCode() == KeyEvent.VK_ENTER){
                        commandParser();
                        //commandRegister.eventListener(thrownCommand  + getContent(),CommandType.TEXTFIELD_ENTER);
                        return;
                    }
                    System.out.println(e.getKeyCode());
                    switch (e.getKeyCode()) {

                        case KeyEvent.VK_BACK_SPACE:
                            content = StringTrimmer.trim(content);
                            return;

                    }

                    content = content + KeyBoardUtil.getRelevantKey(e);
                    break;
                case Key_Typed:


                    break;
            }
        }
    }

    private void commandParser(){
        String content = this.content + '\n';
        int layerCounter = 1;
        String builder = "";
        for(int i = 0; i< content.length(); i++){
            if(content.charAt(i) == ';')
                layerCounter++;
        }
        String[] command = new String[layerCounter];
        layerCounter = 0;
        for(int i = 0; i<content.length();i++){
            if(content.charAt(i) == ';') {
                command[layerCounter] = builder;
                layerCounter++;
                builder = "";
            } else if(content.charAt(i) == '\n'){
                command[layerCounter] = builder;
                builder = "";
            } else {
                builder = builder + content.charAt(i);
            }

        }

        if(command.length < 3)
            return;

        getReciever().commandThrown(command,getID());
    }

    @Override
    public void commandThrown(String[] command, String ID) {
        super.commandThrown(command, ID);
    }

    //Getter


    public String getContent() {
        return content;
    }
}
