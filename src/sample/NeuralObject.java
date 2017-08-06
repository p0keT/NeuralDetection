package sample;

import java.util.ArrayList;

/**
 * Created by Sergiy on 7/31/2017.
 */
public class NeuralObject {
    private int xPos;
    private int yPos;
    private int height;
    private int width;
    private int objectWeight;
    private int deleteRadius;

    /**
     * флаг, що визначає чи необхідно видаляти об"єкт зі списку
     */
    private boolean remove = false;


    public boolean isRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    public NeuralObject(int xPos, int yPos, int height, int width, int objectWeight, int deleteRadius) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.height = height;
        this.width = width;
        this.objectWeight = objectWeight;
        this.deleteRadius = deleteRadius;
    }


    /**
     * Встановлює флаг на видалення у списку об"єктів по заданому радіусу
     * @param objects
     * @param removeRadius
     */
    public static void removeNeuralObjects(ArrayList<NeuralObject> objects, int removeRadius){
        for (int j = 0; j < objects.size(); j++) {
            for (int i = 0; i < objects.size(); i++) {
                if (Math.sqrt(Math.pow(Math.abs(objects.get(i).getxPos() - objects.get(j).getxPos()), 2) +
                        Math.pow(Math.abs(objects.get(i).getyPos() - objects.get(j).getyPos()), 2)) < removeRadius) {
                    if (!(objects.get(i).isRemove() == true && objects.get(j).isRemove() == true)) {
                        if (objects.get(i).getObjectWeight() > objects.get(j).getObjectWeight() && objects.get(j).isRemove()) {
                            objects.get(j).setRemove(true);
                            if (objects.get(i).isRemove() == true)
                                objects.get(j).setRemove(false);

                        } else {
                            objects.get(i).setRemove(true);
                            if (objects.get(j).isRemove() == true)
                                objects.get(i).setRemove(false);
                        }
                    }
                }
            }
        }
    }

    public int getDeleteRadius() {
        return deleteRadius;
    }

    public void setDeleteRadius(int deleteRadius) {
        this.deleteRadius = deleteRadius;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getObjectWeight() {
        return objectWeight;
    }

    public void setObjectWeight(int objectWeight) {
        this.objectWeight = objectWeight;
    }

}
