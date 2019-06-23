package de.reclinarka.editor.animation.tools;

import de.reclinarka.editor.animation.AnimatorInstance;
import de.reclinarka.editor.animation.tools.animationTools.CreatingTool;
import de.reclinarka.editor.animation.tools.controllElements.AnimatorControlElement;
import de.reclinarka.editor.animation.tools.controllElements.HideButton;
import de.reclinarka.editor.animation.tools.controllElements.ToolSlider;
import de.reclinarka.objects.framework.properties.coordinates.Coordinate;
import de.reclinarka.objects.interaction.EventType;
import de.reclinarka.objects.interaction.Interactable;
import de.reclinarka.objects.interaction.InteractionRegistry;
import de.reclinarka.util.ColorStorage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Toolbar implements Interactable{

    public Toolbar(String ID, int totalHeight, AnimatorInstance parent){
        this.ID = ID;
        this.totalHeight = totalHeight;
        this.parent = parent;
        elements.add(new HideButton(this, "hideButton"));
        elements.add(new ToolSlider(this, "toolSlider"));
        //for(int i = 0; i<10; i++){
        //    addTool(new Tool("test_" + i, this));
        //}
        addTool(new CreatingTool("CreationToolTest",this));
        defaultExtendedHeight = initDevision(5);
        defaultFoldedHeight = initDevision(20);
    }

    private AnimatorInstance parent;
    private final int totalHeight;
    private int toolHeight = 160;
    private InteractionRegistry reciever;
    private int defaultToolWidth = 400;
    private int defaultToolDistance = 30;
    private int defaultExtendedHeight;
    private int defaultFoldedHeight;
    private int ToolYOffset = 50;
    private boolean expanded = false;
    private boolean hoverMenu = false;
    private int height;
    private int width;
    private Coordinate lastMousePos = new Coordinate(0,0);
    private String ID;
    private ArrayList<Tool> content = new ArrayList<>();
    private ArrayList<AnimatorControlElement> elements = new ArrayList<>();
    public BufferedImage lastExtendedRender;

    private int initDevision(int x){
        return totalHeight / x;
    }

    public void removeTool(String ID){
        for (Tool f:content) {
            if(f.getID().contentEquals(ID))
                content.remove(f);
        }
    }

    public void addTool(Tool tool){
        content.add(tool);
    }

    public void draw(Graphics graphics, BufferedImage currentFrame) {
        BufferedImage currentRender;
        width = currentFrame.getWidth();
        if(expanded) {
            height = defaultExtendedHeight;
            currentRender = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = currentRender.getGraphics();
            g.setColor(ColorStorage.defaultButton);
            g.fillRect(0, 0, width, height);
            g.setColor(ColorStorage.defaultTextfieldBorder);
            g.drawRect(0, 0, width - 1, height - 1);
            float toolLength = (defaultToolDistance + (content.size() * defaultToolWidth) + (content.size() * defaultToolDistance)) -width;
            g.translate( (int) -(toolLength * getSliderValue()),ToolYOffset);
            for (int i = 0; i < content.size() ; i++){
                g.translate(defaultToolDistance, 0);
                content.get(i).draw(g, new String[]{});
                g.translate(defaultToolWidth,0);
            }
            lastExtendedRender = currentRender;
        } else {
            height = defaultFoldedHeight;
            currentRender = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = currentRender.getGraphics();
            g.setColor(ColorStorage.defaultShade);
            g.fillRect(0, 0, width, height);
            g.setColor(ColorStorage.defaultTextfieldBorder);
            g.drawRect(0, 0, width, height - 1);
        }
        Graphics g = currentRender.getGraphics();
        elements.forEach(f -> f.draw(g));
        graphics.drawImage(currentRender,0,currentFrame.getHeight()-height,null);
    }

    @Override
    public void mouseEvent(MouseEvent e, EventType type, String ID) {
        elements.forEach(f -> f.mouseEvent(e,type,ID));
        content.forEach(f -> f.mouseEvent(e,type,ID));
        switch(type){
            case Mouse_Moved:
                break;
            case Mouse_Pressed:
                break;
        }
    }

    @Override
    public void keyEvent(KeyEvent e, EventType type, String ID) {

        // loading files
        //if(type == EventType.Key_Pressed){
        //    if(e.getKeyChar()== 't') {
        //        JFrame frame = new JFrame("test");
        //        FileDialog fd = new FileDialog(frame,"Choosa a File",FileDialog.LOAD);
        //        fd.setDirectory(WriterReader.getDirPath());
        //        fd.setVisible(true);
        //        System.out.println(WriterReader.getDirPath() +"\\"+ fd.getFile()); String of target Dir
        //    }
        //}

    }

    @Override
    public void commandThrown(String[] command, String ID) {
        elements.forEach(f -> f.commandThrown(command,ID));
        content.forEach(f -> f.commandThrown(command,ID));

    }

    @Override
    public void setReciever(InteractionRegistry reciever, String ID) {
        this.reciever = reciever;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void load(String path) throws IOException {

    }

    @Override
    public void save(String path) throws IOException {

    }

    //Setter
    public void setContent(ArrayList<Tool> content) {
        this.content = content;
    }

    public void toggleExpanded(){
        expanded = !expanded;
    }

    public void throwCommand(String[] command, String ID){
        reciever.commandThrown(command,ID);
    }

    //Getter


    public AnimatorInstance getParent() {
        return parent;
    }

    public int getToolHeight() {
        return toolHeight;
    }

    public int getToolYOffset() {
        return ToolYOffset;
    }

    public int getTotalHeight() {
        return totalHeight;
    }

    public float getSliderValue(){
        ToolSlider slider = (ToolSlider) elements.get(1);
        return slider.getValue();
    }

    public int getWidth() {
        return width;
    }

    public ArrayList<Tool> getContent() {
        return content;
    }

    public int getToolIndex(String ID){
        for (int i = 0; i < content.size(); i++){
            if(content.get(i).getID().contentEquals(ID))
                return i;
        }
        return -1;
    }

    public int getDefaultExtendedHeight() {
        return defaultExtendedHeight;
    }

    public int getDefaultToolDistance() {
        return defaultToolDistance;
    }

    public int getDefaultToolWidth() {
        return defaultToolWidth;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public int getHeight() {
        return height;
    }
}
