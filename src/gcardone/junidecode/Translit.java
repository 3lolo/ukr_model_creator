package gcardone.junidecode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Grass on 04.04.2016.
 */
public class Translit {

    private static final Map<String, String> letters = new HashMap<String, String>();
    private static final Map<String, String> shipLetters = new HashMap<String, String>();
    static {
        letters.put("А", "A");
        letters.put("Б", "B");
        letters.put("В", "V");
        letters.put("Г", "G");
        letters.put("Д", "D");
        letters.put("Е", "E");
        letters.put("Є", "YE");
        letters.put("Ї", "YI");
        letters.put("Ж", "ZH");
        letters.put("З", "Z");
        letters.put("І", "I");
        letters.put("Й", "I");
        letters.put("К", "K");
        letters.put("Л", "L");
        letters.put("М", "M");
        letters.put("Н", "N");
        letters.put("О", "O");
        letters.put("П", "P");
        letters.put("Р", "R");
        letters.put("С", "S");
        letters.put("Т", "T");
        letters.put("У", "U");
        letters.put("Ф", "F");
        letters.put("Х", "H");
        letters.put("Ц", "C");
        letters.put("Ч", "CH");
        letters.put("Ш", "SH");
        letters.put("Щ", "SH");
        letters.put("Ь", "");
        letters.put("И", "Y");
        letters.put("Е", "E");
        letters.put("Ю", "YU");
        letters.put("Я", "YA");

        shipLetters.put("B","Б");
        shipLetters.put("V","В");
        shipLetters.put("G","Г");
        shipLetters.put("D","Д");
        shipLetters.put("Z","З");
        shipLetters.put("K","К");
        shipLetters.put("L","Л");
        shipLetters.put("M","М");
        shipLetters.put("N","Н");
        shipLetters.put("P","П");
        shipLetters.put("R","Р");
        shipLetters.put("S","С");
        shipLetters.put("T","Т");
        shipLetters.put("F","Ф");
        shipLetters.put("H","Х");
        shipLetters.put("C","Ц");
        shipLetters.put("Y","И");
        }

    public static String[] toTranslit(ArrayList<String> strs){
        String[] array = new String[strs.size()];
        for(int i = 0 ;i < strs.size();i++){
            array[i] = toTrans(strs.get(i));

        }
        return  array;
    }

    public static String toTrans(String text) {
        StringBuilder sb = new StringBuilder(text.length());
        for (int i = 0; i<text.length(); i++) {
            String l = text.substring(i, i+1).toUpperCase();
            if (letters.containsKey(l)) {
                String st = letters.get(l);
                int c = (int)l.charAt(0);
                if(i >= 1 &&(l.equals("Я")||l.equals("Ь")||l.equals("Ю")||l.equals("І"))){
                    char a1 = sb.charAt(sb.length()-2);
                    String st1 = ""+a1;
                    if(shipLetters.containsKey( st1)){
                        sb.charAt(sb.length()-1);
                        sb.setCharAt(sb.length()-1,a1);
                        sb.append(" ");
                        if(l.equals("Я")){
                           sb.append("A ");
                        }
                        if(l.equals("І")){
                            sb.append("І ");
                        }
                        if(l.equals("Ю")){
                            sb.append("U ");
                        }
                        if(l.equals("Ь")){
                            sb.append("");
                        }
                    }
                    else
                        sb.append(st+" ");
                }
                else
                    sb.append(st+" ");
            }
            else {
                if(!l.equals("'"))
                    sb.append(l+" ");
            }
        }

        return sb.toString();
    }
}