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

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        //*************************************************************************************************************
        //*************************************************************************************************************
        Weight weight = new Weight("TestHuman.txt", 80, 80);

        images.add(new RWFile().readBufferedToBinaryImage("test0.jpg"));

        BufferedImage bi = toBufferedImage(images.get(0));

        detection(bi, weight);

        IVRecognized.setImage(SwingFXUtils.toFXImage(bi, null));
        //*************************************************************************************************************
        //*************************************************************************************************************

    }

    public void detection(BufferedImage bi, Weight weight){

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
                //System.out.println(l+"///"+k);
                Neuron NW1 = new Neuron(tempRect.length, tempRect[0].length, tempRect);
                NW1.setWeight(weight.getWeight());


                NW1.mul_w();
                NW1.Sum();

                if (NW1.Rez()) {
                    objects.add(new NeuralObject(k - (objHeight - 1), l - (objWidth - 1), objHeight, objWidth, NW1.getSum(), 40));

                    System.out.println(" - True, Sum = " + NW1.getSum());
                } else {
                    //System.out.println(" - False, Sum = "+NW1.sum);

                }
            }
        }
        boolean repeat = false;

        for (int j = 0; j < objects.size(); j++) {
            for (int i = 0; i < objects.size(); i++) {
                if (Math.sqrt(Math.pow(Math.abs(objects.get(i).getxPos() - objects.get(j).getxPos()), 2) +
                        Math.pow(Math.abs(objects.get(i).getyPos() - objects.get(j).getyPos()), 2)) < 40 ) {
                    if (!(objects.get(i).isRemove() == true && objects.get(j).isRemove() == true)) {
                        if (objects.get(i).getObjectWeight() > objects.get(j).getObjectWeight() && objects.get(j).isRemove()) {
                            objects.get(j).setRemove(true);
                            if (objects.get(i).isRemove() == true)
                                objects.get(i).setRemove(false);

                        } else {
                            objects.get(i).setRemove(true);
                            if (objects.get(j).isRemove() == true)
                                objects.get(j).setRemove(false);
                        }
                    }
                }
            }
        }

//        boolean repeat = false;
//        do {
//            repeat=false;
//            int maxIndex = 0;
//            for (int i = 0; i < objects.size(); i++) {
//                if (objects.get(maxIndex).getObjectWeight() < objects.get(i).getObjectWeight() && objects.get(i).isIgnore() == false
//                        && objects.get(i).isRemove()==false) {
//                    maxIndex = i;
//                    repeat=true;
//                }
//            }
//            objects.get(maxIndex).setIgnore(true);
//            for (int i = 0; i < objects.size(); i++) {
//                if (Math.sqrt(Math.pow(Math.abs(objects.get(maxIndex).getxPos() - objects.get(i).getxPos()), 2) +
//                        Math.pow(Math.abs(objects.get(maxIndex).getyPos() - objects.get(i).getyPos()), 2)) < 10&&i!=maxIndex) {
//                    objects.get(i).setRemove(true);
//
//                }
//            }
//        }while(repeat==true);



        for (int i = 0; i < objects.size(); i++) {

            if(objects.get(i).isRemove()==false)
                g.drawRect(objects.get(i).getxPos(), objects.get(i).getyPos(), objects.get(i).getWidth(), objects.get(i).getHeight());
        }
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
