package gateway;

import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class TypeConverter {
    public TypeConverter(){}

    /**
     * Covert string
     * @param s to list of integers
     * @return arraylist of integers
     */
    public ArrayList<Integer> strToArrayInt(String s){
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (s.equals("#")|s.equals("")){
            return result;
        }
        String[] lst = s.split("#");
        for (String str: lst){
            result.add(Integer.parseInt(str));
        }
        return result;
    }

    /**
     * convert
     * @param s a string
     * @return pair of datetime
     */
    public Pair<LocalDateTime, LocalDateTime> strToTimePair(String s){
        String[] values = s.split("#");
        return new Pair<LocalDateTime, LocalDateTime>(LocalDateTime.parse(values[0]),
                LocalDateTime.parse(values[1]));
    }

    /**
     * convert
     * @param lst arraylist of integers
     * @return to a string
     */
    public String arrayIntToStr(ArrayList<Integer> lst){
        StringBuilder s = new StringBuilder();
        if (lst.isEmpty()){
            return "#";
        }
        for (int i: lst){
            s.append(Integer.toString(i)).append("#");
        }
        s = new StringBuilder(s.substring(0, s.length() - 1));
        return s.toString();
    }

    /**
     * convert
     * @param time pair time to
     * @return string
     */
    public String timePairToStr(Pair<LocalDateTime, LocalDateTime> time){
        return time.getKey().toString() + "#" + time.getValue().toString();
    }


    /**
     * convert
     * @param s string to
     * @return localdatetime
     */
    public LocalDateTime strToTime(String s){
        return LocalDateTime.parse(s);
    }

    /**
     * convert
     * @param t local date time
     * @return to string
     */
    public String timeToStr(LocalDateTime t){
        return t.toString();
    }

    /**
     * convert
     * @param hm hashmap to
     * @return string
     */
    public String mapToStr(HashMap<String, String> hm){
        if (hm.isEmpty()){
            return "#";
        }
        StringBuilder s = new StringBuilder();
        for (String key: hm.keySet()){
            s.append(key).append("/").append(hm.get(key)).append("#");
        }
        if (s.length() > 0){
            s.deleteCharAt(s.length() - 1);
        }
        return s.toString();
    }

    /**
     * convert
     * @param s string to
     * @return hashmap
     */
    public HashMap<String, String> strToMap(String s){
        if (s.equals("")|s.equals("#")){
            return new HashMap<String, String>();
        }
        String[] pairs = s.split("#");
        HashMap<String, String> h = new HashMap<String, String>();
        for (String pair: pairs){
            String[] lst = pair.split("/");
            h.put(lst[0], lst[1]);
        }
        return h;
    }
}
