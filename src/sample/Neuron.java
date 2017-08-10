package sample;

import java.util.ArrayList;

/**
 * Created by Sergiy on 7/20/2017.
 */
public class Neuron {

    private int[][] mul; // Тут будем хранить отмасштабированные сигналы

    private int[][] weight; // Массив для хранения весов
    private int[][] input; // Входная информация
    private int limit = 5000; // Порог - выбран экспериментально, для быстрого обучения
    private int sum ; // Тут сохраним сумму масштабированных сигналов

    private int height;
    private int width;

    public Neuron(int height, int width, int[][] inputImage) // Задаем свойства при создании объекта
    {
        this.height=height;
        this.width=width;
        mul = new int[height][ width];
        input = new int[height][ width];
        input = inputImage; // Получаем входные данные

    }
    public void mul_w()
    {
        for (int i = 0; i <height; i++)
        {
            for (int j = 0; j <width; j++) // Пробегаем по каждому аксону
            {
                mul[i][ j] = input[i][j]*weight[i][j]; // Умножаем его сигнал (0 или 1) на его собственный вес и сохраняем в массив.
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
