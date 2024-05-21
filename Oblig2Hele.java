


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Oblig2Hele {
    // antall flettetråder
    private static int ANTALL_FTRADER = 8;
    // toleranse, altså hvor mange flere forekomster person1 skal ha av en subsekvens for
    // at den skal regnes som dominant ifht. person2
    private static int THRESHOLD = 7;  


    public static void main(String[] args){

        String filsti = args[0];
        ArrayList<Monitor2> ssr = lesMetadata(filsti);
        Monitor2 m21 = ssr.get(0); // person med virus
        Monitor2 m22 = ssr.get(1); // person uten virus
        flettSSR(m21);
        flettSSR(m22);
        //System.out.println(m21.size());
        //System.out.println(m22.size());
        HashMap<String, Subsekvens> person1 = m21.taUt(0);
        HashMap<String, Subsekvens> person2 = m22.taUt(0);
        //System.out.println(SubsekvensRegister.max(person1));

        for(String ss:person1.keySet()){
            int n1 = person1.get(ss).getAntall();
            int n2 = person2.containsKey(ss)?person2.get(ss).getAntall():0;
            if(n2+THRESHOLD<=n1){
                System.out.println(String.format("Person med virus = %s | Person uten virus = %s", person1.get(ss), (n2>0)?person2.get(ss):String.format("(%s,0)",ss)));
            }
        }
    }
    static ArrayList<Monitor2> lesMetadata(String filsti){
        Monitor2 res = new Monitor2();
        Monitor2 res2 = new Monitor2();
        ArrayList<LeseTrad> trader = new ArrayList<LeseTrad>();
        try{
            List<String> filer = Files.readAllLines(Paths.get(filsti + "metadata.csv"));

            for(String fil:filer){
                String[] f = fil.split(",");
                if(f.length != 2){
                    System.out.println(fil);
                    continue;
                }
                String filnavn = f[0];
                boolean hasVirus = Boolean.valueOf(f[1]);
                if(hasVirus){
                    trader.add(new LeseTrad(res, filsti + filnavn));
                }
                else{
                    trader.add(new LeseTrad(res2, filsti + filnavn));
                }
                
            }
            for(LeseTrad t:trader){
                t.run();
            }
        }
        catch(IOException e){
            System.out.println(String.format("Noe gikk galt! ://\n%s",e.toString()));
        }
        ArrayList<Monitor2> r = new ArrayList<Monitor2>();
        r.add(res);
        r.add(res2);
        return r;
    }
    private static void flettSSR(Monitor2 ssr){
        ArrayList<FletteTrad> trader = new ArrayList<FletteTrad>();
        for(int i=0; i<ANTALL_FTRADER; i++){
            trader.add(new FletteTrad(ssr));
        }
        for(FletteTrad ft:trader){
            ft.run();
        }
    }
}