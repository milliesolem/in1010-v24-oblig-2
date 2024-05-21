

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Oblig2Del1 {
    public static void main(String[] args){

        String filsti = args[0];
        SubsekvensRegister ssr = lesMetadata(filsti);
        flettSSR(ssr);
        Subsekvens m = SubsekvensRegister.max(ssr.taUt(0));
        System.out.println(m.toString());
    }
    static SubsekvensRegister lesMetadata(String filsti){
        SubsekvensRegister res = new SubsekvensRegister();
        try{
            List<String> filer = Files.readAllLines(Paths.get(filsti + "metadata.csv"));
            for(String fil:filer){
                res.settInn(SubsekvensRegister.lesFil(filsti + fil));
            }
        }
        catch(IOException e){
            System.out.println(String.format("Noe gikk galt! ://\n%s",e.toString()));
        }
        return res;
    }
    private static void flettSSR(SubsekvensRegister ssr){
        if(ssr.size() == 0){
            return;
        }
        while (ssr.size() > 1){
            ssr.settInn(SubsekvensRegister.slaaSammen(ssr.taUt(0), ssr.taUt(0)));
        }
    }
}