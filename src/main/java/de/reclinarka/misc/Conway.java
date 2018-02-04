package de.reclinarka.misc;

import com.sun.istack.internal.NotNull;

public class Conway {
    public Conway(int width,int height, double probability){
        this.field = newArray(width,height,probability);
    }
    public Conway(){
        field = new int[][] { {0, 1, 0}, {1, 0, 1}, {0, 1, 0} };
    }

    private int[][] newArray(int width,int height,double probability){
        int[][] field = new int[width][height];
        for(int i = 0; i < field.length; i++){
            for (int c = 0; c < field[0].length; c++){
                if(  Math.random() <= probability )
                    field[i][c] = 1;
            }
        }
        return field;
    }

    public static void main(String[] args) {
        Conway conway = new Conway(20,20,0.10);
        conway.printField();
        System.out.println();
        while(true){
            conway.update();
            conway.printField();
            System.out.println();
        }
    }

    private int[][] field;
    public void update(){
        int[][] temp = new int[field.length][field[0].length];
        for(int i = 0; i < field.length; i++){
            for (int c = 0; c < field[0].length; c++){
                if(field[i][c] == 1){
                    if(checkField(i,c) < 2 && checkField(i,c) > 3){
                        temp[i][c] = 0;
                    } else {
                        temp[i][c] = 1;
                    }
                } else {
                    if(checkField(i,c) == 3){
                        temp[i][c] = 1;
                    }
                }

            }
        }
        field = temp;
    }
    private int checkField(int x, int y){
        int count = 0;
        int add = 0;
        for (int i = (x-1); i <= (x+1); i++){
            for (int c = (y-1); c <= (y+1); c++){
                if(i == x && c == y) {

                } else {
                    try {
                        if (field[i][c] == 1)
                            add = 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        add = 0;
                    }
                    count = add + count;
                }
            }
        }

        return count;
    }
    public void update(int steps) {
        for (int i = 0; i<steps;i++)
            update();
    }
    public void printField(){
        printField(this.field);
    }
    public void printField(@NotNull int[][] field){
        for(int i = 0; i < field.length; i++){
            for (int c = 0; c < field[0].length; c++){
                System.out.printf("%3d",field[i][c]);
            }
            System.out.println();
        }
    }
}
