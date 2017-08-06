package sample;

import java.util.ArrayList;

/**
 * Created by Sergiy on 7/20/2017.
 */
public class Neuron {

    private int[][] mul; // ��� ����� ������� ������������������ �������

    private int[][] weight; // ������ ��� �������� �����
    private int[][] input; // ������� ����������
    private int limit = 5000; // ����� - ������ ����������������, ��� �������� ��������
    private int sum ; // ��� �������� ����� ���������������� ��������

    private int height;
    private int width;

    public Neuron(int height, int width, int[][] inputImage) // ������ �������� ��� �������� �������
    {
        this.height=height;
        this.width=width;
        //weight = new int[height][width]; // ������������ � �������� ������� (����� ������)
        mul = new int[height][ width];
        input = new int[height][ width];
        input = inputImage; // �������� ������� ������

        //weight_load();
    }
    public void mul_w()
    {
        for (int i = 0; i <height; i++)
        {
            for (int j = 0; j <width; j++) // ��������� �� ������� ������
            {
                mul[i][ j] = input[i][j]*weight[i][j]; // �������� ��� ������ (0 ��� 1) �� ��� ����������� ��� � ��������� � ������.
            }
        }
    }

    public void Sum()
    {
        sum = 0;
        for (int i = 0; i <height; i++)
        {
            for (int j = 0; j <width; j++)
            {
                sum += mul[i][ j];
            }
        }
    }
    public boolean Rez()
    {
        if (sum >= limit)
            return true;
        else return false;
    }

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


    public int[][] getMul() {
        return mul;
    }

    public int[][] getWeight() {
        return weight;
    }

    public int[][] getInput() {
        return input;
    }

    public int getLimit() {
        return limit;
    }

    public int getSum() {
        return sum;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setWeight(int[][] weight) {
        this.weight = weight;
    }
}
