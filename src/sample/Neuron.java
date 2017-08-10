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
        mul = new int[height][ width];
        input = new int[height][ width];
        input = inputImage; // �������� ������� ������

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
