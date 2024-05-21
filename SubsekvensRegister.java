

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubsekvensRegister {
    private ArrayList<HashMap<String,Subsekvens>> hashBeholder;
    public SubsekvensRegister(){
        this.hashBeholder = new ArrayList<HashMap<String,Subsekvens>>();
    }

    public void settInn(HashMap<String,Subsekvens> sub){
        this.hashBeholder.add(sub);
    }

    public HashMap<String,Subsekvens> taUt(int index){
        return this.hashBeholder.remove(index);
    }
    public int size(){
        return this.hashBeholder.size();
    }

    public static HashMap<String,Subsekvens> lesFil(String filsti){
        HashMap<String,Subsekvens> res = new HashMap<String,Subsekvens>();
        int i = 0;
        String g;
        try{
            String innhold = new String(Files.readAllBytes(Paths.get(filsti)), StandardCharsets.UTF_8);
            //IN1140 pensum :P
            Matcher m = Pattern.compile("[A-Z]{3}").matcher(innhold);
            while(m.find(i++)){
                res.put((g = m.group()), new Subsekvens(1, g));
            }
        }
        catch(Exception e){
            System.out.println("Noe gikk galt ://\n" + e.toString());
            return res;
        }
        return res;
    }
    public static HashMap<String,Subsekvens> slaaSammen(HashMap<String,Subsekvens> hm1, HashMap<String,Subsekvens>hm2){
        HashMap<String,Subsekvens> res = new HashMap<String,Subsekvens>();
        res.putAll(hm1);
        for(String k:hm2.keySet()){
            if (res.keySet().contains(k)){
                res.get(k).setAntall(res.get(k).getAntall() + hm2.get(k).getAntall());
                continue;
            }
            res.put(k, hm2.get(k));
        }
        return res;
    }
    public static Subsekvens max(HashMap<String,Subsekvens> hm){
        Subsekvens res = new Subsekvens(0, "XXX");
        for(String k:hm.keySet()){
            if(hm.get(k).getAntall() > res.getAntall()){
                res = hm.get(k);
            }
        }
        return res;
    }
}
