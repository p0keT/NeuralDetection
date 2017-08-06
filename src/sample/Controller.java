package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    @FXML
    public ImageView IVRecognized;

    ArrayList<int[][]> images = new ArrayList<>();
    Weight weight1 = new Weight("region1.txt", 80, 80);
    Weight weight2 = new Weight("region2.txt", 80, 80);
    Weight weight3 = new Weight("region3.txt", 80, 80);
    Weight weight4 = new Weight("region4.txt", 80, 80);
    Weight weight5 = new Weight("region5.txt", 80, 80);

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        //*************************************************************************************************************
        //*************************************************************************************************************
        Weight weight = new Weight("TestHuman.txt", 80, 80);

        images.add(new RWFile().readBufferedToBinaryImage("test1.jpg"));

        BufferedImage bi = toBufferedImage(images.get(0));

        detection(bi, weight);

        IVRecognized.setImage(SwingFXUtils.toFXImage(bi, null));
        //*************************************************************************************************************
        //*************************************************************************************************************

    }

    public void detection(BufferedImage bi, Weight weight) {

        ArrayList<NeuralObject> objects = new ArrayList<>();

        Graphics g = bi.createGraphics();
        int objHeight = weight.getWeight().length;
        int objWidth = weight.getWeight()[0].length;
        System.out.println("*******" + objHeight + "*" + objWidth);


        int[][] tempRect = new int[objHeight][objWidth];
        for (int k = objHeight - 1; k < images.get(0).length; k += 10) {
            for (int l = objWidth - 1; l < images.get(0)[0].length; l += 10) {
                for (int i = 0 + k - (objHeight - 1); i < objHeight + k - (objHeight - 1); i++) {
                    for (int j = 0 + l - (objWidth - 1); j < objWidth + l - (objWidth - 1); j++) {
                        tempRect[i - k + (objHeight - 1)][j - l + (objWidth - 1)] = images.get(0)[i][j];
                    }
                }
                int centerOfRectX = k - (objHeight - 1) + objWidth / 2;
                int centerOfRectY = l - (objWidth - 1) + objHeight / 2;
                int rectX = k - (objHeight - 1);
                int rectY = l - (objWidth - 1);

                if (centerOfRectX < images.get(0)[0].length / 2 && centerOfRectY < images.get(0).length / 2) {
                    //System.out.println(l+"///"+k);
                    Neuron neuron1 = new Neuron(tempRect.length, tempRect[0].length, tempRect);
                    neuron1.setWeight(weight1.getWeight());


                    neuron1.mul_w();
                    neuron1.Sum();
                    if (neuron1.Rez()) {
                        objects.add(new NeuralObject(k - (objHeight - 1), l - (objWidth - 1), objHeight, objWidth, neuron1.getSum(), 40));

                        //System.out.println(" - True, Sum = " + NW1.sum);
                    }
                }
                //**************
                if (centerOfRectX > images.get(0)[0].length / 2 && centerOfRectY < images.get(0).length / 2) {
                    Neuron neuron2 = new Neuron(tempRect.length, tempRect[0].length, tempRect);
                    neuron2.setWeight(weight2.getWeight());


                    neuron2.mul_w();
                    neuron2.Sum();
                    if (neuron2.Rez()) {
                        objects.add(new NeuralObject(k - (objHeight - 1), l - (objWidth - 1), objHeight, objWidth, neuron2.getSum(), 40));

                        //System.out.println(" - True, Sum = " + NW1.sum);
                    }
                }
                //**************
                if (centerOfRectX < images.get(0)[0].length / 2 && centerOfRectY > images.get(0).length / 2) {
                    Neuron neuron3 = new Neuron(tempRect.length, tempRect[0].length, tempRect);
                    neuron3.setWeight(weight3.getWeight());


                    neuron3.mul_w();
                    neuron3.Sum();
                    if (neuron3.Rez()) {
                        objects.add(new NeuralObject(k - (objHeight - 1), l - (objWidth - 1), objHeight, objWidth, neuron3.getSum(), 40));

                        //System.out.println(" - True, Sum = " + NW1.sum);
                    }
                }
                //**************
                if (centerOfRectX > images.get(0)[0].length / 2 && centerOfRectY > images.get(0).length / 2) {
                    Neuron neuron4 = new Neuron(tempRect.length, tempRect[0].length, tempRect);
                    neuron4.setWeight(weight4.getWeight());


                    neuron4.mul_w();
                    neuron4.Sum();
                    if (neuron4.Rez()) {
                        objects.add(new NeuralObject(rectX, rectY, objHeight, objWidth, neuron4.getSum(), 40));

                        //System.out.println(" - True, Sum = " + NW1.sum);
                    }
                }
                //**************
                if (centerOfRectX > images.get(0)[0].length / 4 && centerOfRectY > images.get(0).length / 4 &&
                        centerOfRectX < images.get(0)[0].length / 4 * 3 && centerOfRectY < images.get(0).length / 4 * 3) {
                    Neuron neuron5 = new Neuron(tempRect.length, tempRect[0].length, tempRect);
                    neuron5.setWeight(weight5.getWeight());


                    neuron5.mul_w();
                    neuron5.Sum();
                    if (neuron5.Rez()) {
                        objects.add(new NeuralObject(k - (objHeight - 1), l - (objWidth - 1), objHeight, objWidth, neuron5.getSum(), 40));

                        //System.out.println(" - True, Sum = " + NW1.sum);
                    }
                }
            }


            Neuron.removeNeuralObjects(objects, 40);



            for (int i = 0; i < objects.size(); i++) {

                if (objects.get(i).isRemove() == false)
                    g.drawRect(objects.get(i).getxPos(), objects.get(i).getyPos(), objects.get(i).getWidth(), objects.get(i).getHeight());
            }
        }
        images.removeAll(images);
    }



    public BufferedImage toBufferedImage(int[][] image){
        BufferedImage bi = new BufferedImage(image.length,image[0].length,BufferedImage.TYPE_BYTE_BINARY);
        System.out.println(bi.getWidth()+"/"+bi.getHeight());

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                if(image[i][j]==0)
                    bi.setRGB(i,j,0x000000);
                else
                    bi.setRGB(i,j,0xffffff);
            }
        }
        return bi;
    }



}
