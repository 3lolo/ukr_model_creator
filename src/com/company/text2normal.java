package com.company;

import gcardone.junidecode.Translit;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class text2normal {

    public static ArrayList<String>  wordSet = new ArrayList<String>();
    public static ArrayList<String>  phoneSet = new ArrayList<String>();

    public static void main(String[] args) throws IOException {

    //    System.out.println("ПОЛIТИКИ = "+ Translit.toTrans("ПОЛIТИКИ"));
     //   System.out.println("ЮНОНА = "+ Translit.toTrans("ЮНОНА"));
//        System.out.println("НЮША = "+ Translit.toTrans("НЮША"));
  //      System.out.println("НЯША = "+ Translit.toTrans("НЯША"));
        System.out.println("ІГНОРУЮТЬ = "+ Translit.toTrans("ІГНОРУЮТЬ"));
    /*    System.out.println("ялицю = "+ Translit.toTranslit("));
        System.out.println("ялицю = "+ Translit.toTranslit("ялиця"));
        System.out.println("юнона = "+ Translit.toTranslit("юнона"));
        System.out.println("юлька = "+ Translit.toTranslit("юлька"));
        System.out.println("інший = "+ Translit.toTranslit("інший"));
        System.out.println("привіт = "+ Translit.toTranslit("привіт"));

        System.out.println("приємний = "+ Translit.toTranslit("приємний"));

*/
        readDict("C:\\Users\\Grass\\IdeaProjects\\ukr_model\\src\\ukr_model\\etc\\ukr_model.vocab");

        String outFile1 = "C:\\Users\\Grass\\IdeaProjects\\ukr_model\\src\\ukr_model\\etc\\ukr_model_train.transcription";
        String outFile2 = "C:\\Users\\Grass\\IdeaProjects\\ukr_model\\src\\ukr_model\\etc\\ukr_model_train.fileids";
        String outFile4 = "C:\\Users\\Grass\\IdeaProjects\\ukr_model\\src\\ukr_model\\etc\\ukr_model.dic";
        String outFile5 = "C:\\Users\\Grass\\IdeaProjects\\ukr_model\\src\\ukr_model\\etc\\ukr_model.vocab";
        String inFile = "C:\\Users\\Grass\\IdeaProjects\\ukr_model\\src\\ukr_model\\pomm.txt";

        BufferedWriter out1 = new BufferedWriter(new FileWriter(outFile1));
        BufferedWriter out2 = new BufferedWriter(new FileWriter(outFile2));
        BufferedWriter out4 = new BufferedWriter(new FileWriter(outFile4));
        BufferedWriter out5 = new BufferedWriter(new FileWriter(outFile5));
        BufferedReader in = new BufferedReader(new FileReader(inFile));


        String line;
        while ((line = in.readLine()) != null) {
            line = line.replaceAll("[«»\"\',–-—!?:;.]+", "").toLowerCase();
            line = line.replaceAll("[  ]+", " ");
            line = line.replaceAll("[-]+", "");
            line = line.replaceAll(" "," ");
            line = line.replaceAll("  "," ");
            //Pattern pattern = Pattern.compile("uk_[0-9][0-9][0-9][0-9]");
            //Matcher matcher = pattern.matcher(line);
           // matcher.find();
            //String file_name = matcher.group().replaceAll(" ", "");
            String file_name = line.split(" ")[0];
            String text = line.replace(file_name + " ", "");
            out1.write("<s> " + text.toUpperCase() +  " </s>" + " ("+file_name.split("/")[1]+")"+ "\n");
            out1.flush();
            out2.write(file_name + "\n");
            out2.flush();
            addToDict(text.toUpperCase());
           // addToDict(line);
        }
        writeDict(wordSet, out4);
        writeWordSet(wordSet,out5);
        writePhone(phoneSet, out5);
        out1.close();
        out2.close();
        in.close();
    }

    public static void addToDict(String line){
        String[] mas = line.split(" ");
        for(String str :mas) {
            str = str.replace(" ","");
            if (wordSet.indexOf(str) < 0&& str!="\n") {
                wordSet.add(str);
            }
        }
        Collections.sort(wordSet);
    }
    public static void writeWordSet(ArrayList<String> wordSet,BufferedWriter writer) throws IOException {
        for(String str:wordSet){
            writer.write(str.toUpperCase()+"\n");
            writer.flush();
        }
        writer.close();
    }
    public static void writeDict(ArrayList<String> wordSet,BufferedWriter writer) throws IOException {
        String[] str= Translit.toTranslit(wordSet);
        for(int i = 0 ; i < str.length;i++  ){
//            String value = Translit.toTrans(str[i]);
            String st = (wordSet.get(i).toUpperCase() + " "+str[i]  + "\n");
            writer.write(st);
            writer.flush();
        }
    }
    public static void writePhone(ArrayList<String> wordSet,BufferedWriter writer) throws IOException {
        String[] str= Translit.toTranslit(wordSet);
        for(int i = 0 ; i < str.length;i++  ){
            writer.write(wordSet.get(i).toUpperCase()+"\n");
            writer.flush();
        }
    }

    private static void readDict(String in) throws IOException {
        BufferedReader out = new BufferedReader(new FileReader(in));
        String str = "";
        while ((str = out.readLine())!= null){
            wordSet.add(str);
        }


    }
}
