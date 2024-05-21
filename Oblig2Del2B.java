


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Oblig2Del2B {
    // antall flettetråder
    private static int ANTALL_FTRADER = 8;


    public static void main(String[] args){

        String filsti = args[0];
        Monitor2 ssr = lesMetadata(filsti);
        flettSSR(ssr);
        Subsekvens m = SubsekvensRegister.max(ssr.taUt(0));
        System.out.println(m.toString());
    }
    static Monitor2 lesMetadata(String filsti){
        Monitor2 res = new Monitor2();
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