package sample;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Sergiy on 7/31/2017.
 *
 */
public class Weight {

    private String path;
    private int height;
    private int width;
    private int[][] weight;

    public int[][] getWeight() {
        return weight;
    }



    Weight(String path){

        this.path = path;
        weight_load();

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void weight_load(){
        try {
            readFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * зберігає weights в файл
     * @throws IOException
     */
    public void save() throws IOException
    {

        FileWriter filewriter = new FileWriter(new File(path));

        for (int i=0;i<weight.length;++i) {
            for (int j = 0; j < weight[0].length; ++j)
                filewriter.write(weight[i][j]+"\n");
            filewriter.write("+\n");
        }
        filewriter.flush();
    }
    /**
     * Resize dimension of weights (походу така штука не прокатить)
     * @param pixels that will be resized
     * @param w1 - initial width
     * @param h1 - initial height
     * @param w2 - new width
     * @param h2 - new height
     * @return resized array
     */
    public int[][] resize(int[][] pixels,int w1,int h1,int w2,int h2) {
        int[][] temp = new int[h2][w2] ;
        double x_ratio = w1/(double)w2 ;
        double y_ratio = h1/(double)h2 ;
        double px, py ;
        for (int i=0;i<h2;i++) {
            for (int j=0;j<w2;j++) {
                px = Math.floor(j*x_ratio) ;
                py = Math.floor(i*y_ratio) ;
                temp[i][j] = pixels[(int)((py*w1)+px)/w1][(int)((py*w1)+px)%w1] ;
            }
        }
        return temp ;
    }

//    /**
//     * зчитує weights з файлу
//     * @param path петь к файлу
//     * @return повертає список слів
//     */
//    public void readFile(String path){
//        ArrayList<String> s = new ArrayList<>();
//
//        String str;
//
//        BufferedReader in = null;
//        try {
//            in = new BufferedReader(new FileReader(path));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        for (int i = 0; i <weight.length ; i++) {
//            for (int j = 0; j < weight[0].length; j++) {
//                try {
//                    weight[i][j]= Integer.parseInt(in.readLine());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//    }

    /**
     * зчитує weights з файлу
     * @param path шлях до файлу
     * @return повертає список слів
     */
    public void readFile(String path) throws IOException{
        ArrayList<ArrayList<String>> s = new ArrayList<>();

        String str;

        BufferedReader in = null;
        in = new BufferedReader(new FileReader(path));


        int height =0;
        int width =0;
        s.add(new ArrayList<>());
        while ((str=in.readLine())!=null) {
            if(str.equals("+")) {
                height++;
                s.add(new ArrayList<>());
            }else {
                s.get(s.size()-1).add(str);
                width++;
            }
        }

        width=width/height;

        weight = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                weight[i][j]=Integer.parseInt(s.get(i).get(j));
            }
        }

    }
}
