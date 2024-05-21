


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Oblig2Del2A {
    public static void main(String[] args){

        String filsti = args[0];
        Monitor1 ssr = lesMetadata(filsti);
        flettSSR(ssr);
        Subsekvens m = SubsekvensRegister.max(ssr.taUt(0));
        System.out.println(m.toString());
    }
    static Monitor1 lesMetadata(String filsti){
        Monitor1 res = new Monitor1();
        ArrayList<LeseTrad> trader = new ArrayList<LeseTrad>();
        try{
            List<String> filer = Files.readAllLines(Paths.get(filsti + "metadata.csv"));

            for(String fil:filer){
                trader.add(new LeseTrad(res, filsti + fil));
            }
            for(LeseTrad t:trader){
                t.run();
            }
        }
        catch(IOException e){
            System.out.println(String.format("Noe gikk galt! ://\n%s",e.toString()));
        }
        return res;
    }
    private static void flettSSR(Monitor1 ssr){
        if(ssr.size() == 0){
            return;
        }
        while (ssr.size() > 1){
            ssr.settInn(Monitor1.slaaSammen(ssr.taUt(0), ssr.taUt(0)));
        }
    }
}