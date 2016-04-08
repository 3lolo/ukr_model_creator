package com.company;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Grass on 21.03.2016.
 */
public class Phonetic {
    ArrayList<String> files_name = new ArrayList<>();
    public static int counter = 1;
    public static int speakerCnt = 0;



    public static  void main(String[] args ){

        File folder = new File("C:\\Users\\Grass\\Desktop\\dd");

        File[] folders = folder.listFiles();
        String prevPath = "";
        for (File path:folders){
            try {
                String mainP = path.getPath().split("-")[0];
                if(!mainP.equals(prevPath)) {
                    speakerCnt++;
                    String dirP = "C:\\Users\\Grass\\Desktop\\model\\speaker_"+speakerCnt;
                    new File(dirP).mkdir();
                }
                prevPath = mainP;
                readPom(path.getPath());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //UnicodeConverter.main(new String[]{"давньоруської"});
    }


    public static void readPom(String path) throws IOException {
        File[] audioF = new File(path+"\\wav").listFiles();
        File   pommF  = new File(path+"\\etc\\prompts-original");
        BufferedReader pommR  = new BufferedReader(new FileReader(pommF));
        String line = "";
        int i = 0 ;
        while ((line = pommR.readLine())!=null){
            Pattern pattern = Pattern.compile("uk_[0-9][0-9][0-9][0-9]");
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            String file_name = matcher.group();

            String text = line.replace(file_name + " ", "");
            file_name = numberConverter(counter);
            writeToFile("C:\\Users\\Grass\\IdeaProjects\\ukr_model\\src\\ukr_model\\pomm.txt","speaker_"+speakerCnt+"/"+file_name+" "+text);
            copyFileUsingChannel(audioF[i],new File("C:\\Users\\Grass\\IdeaProjects\\ukr_model\\src\\ukr_model\\wav\\speaker_"+speakerCnt+"\\"+file_name+".wav"));
            i++;
            counter++;
        }
    }

    private static void writeToFile(String path,String text ) throws IOException {
        FileWriter pommR  = new FileWriter(path,true);
        pommR.write(text+"\n");
        pommR.close();
    }
    private static void copyFileUsingChannel(File source, File dest) throws IOException {
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destChannel = new FileOutputStream(dest).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        }finally{
            sourceChannel.close();
            destChannel.close();
        }
    }



    private static String numberConverter(int num){
        StringBuilder tag =  new StringBuilder("uk_0000");
        int i = tag.length()-1;
        while (num!=0){
            tag.setCharAt(i,(""+num%10).charAt(0));
            num/=10;
            i--;
        }
        return tag.toString();
    }

}

