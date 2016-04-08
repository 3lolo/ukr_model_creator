package com.company;

import java.io.*;

/**
 * Created by Grass on 31.03.2016.
 */
public class Ukr_2_Ru {

    static String inFile = "C:\\Users\\Grass\\IdeaProjects\\ukr_model\\src\\ukr_model\\ukr_model_train.transcription";
    static String outFile = "C:\\Users\\Grass\\IdeaProjects\\ukr_model\\src\\ukr_model\\ukr_ru_model_train.transcription";
    public static  void main(String[] args ) throws IOException {
        String line;
        BufferedReader in = new BufferedReader(new FileReader(inFile));

        BufferedWriter out = new BufferedWriter(new FileWriter(outFile));
        while ((line = in.readLine()) != null) {
            out.write(getUpdated(line).toLowerCase()+"\n");
            out.flush();
        }
        out.close();
        in.close();
    }


    public static String getUpdated(String str){
       StringBuilder sb = new StringBuilder(str);
        for(int i = 0; i < sb.length();i++){
            char a = sb.charAt(i);
             switch (a) {
                 case 'Е':
                     sb.setCharAt(i,'Э');
                     break;
                case 'Є':
                    sb.setCharAt(i,'Е');
                break;
                case 'Ї':
                    sb.setCharAt(i,'Й');
                    sb.append('И');
                break;
                case 'Ґ':
                    sb.setCharAt(i,'Г');
                break;
                 case 'И':
                     sb.setCharAt(i,'Ы');
                     break;
                case 'І':
                    sb.setCharAt(i,'И');
                break;

             }
        }
        return sb.toString();
    }
}
