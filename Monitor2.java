import java.util.ArrayList;
import java.util.HashMap;

class Monitor2 extends Monitor1{
    private SubsekvensRegister ss;
    public Monitor2(){
        this.ss = new SubsekvensRegister();
    }
    public synchronized void settInn(HashMap<String,Subsekvens> sub){
        this.ss.settInn(sub);
    }

    public synchronized HashMap<String,Subsekvens> taUt(int index){
        return this.ss.taUt(index);
    }
    public synchronized ArrayList<HashMap<String,Subsekvens>> hentUtToElectricBoogaloo(int index, int index2){
        ArrayList<HashMap<String,Subsekvens>> res = new ArrayList<HashMap<String,Subsekvens>>();
        res.add(this.ss.taUt(index));
        res.add(this.ss.taUt(index2));
        return res;
    }
    public synchronized int size(){
        return this.ss.size();
    }

    public static HashMap<String,Subsekvens> lesFil(String filsti){
        return SubsekvensRegister.lesFil(filsti);
    }
    public static HashMap<String,Subsekvens> slaaSammen(HashMap<String,Subsekvens> hm1, HashMap<String,Subsekvens>hm2){
        return SubsekvensRegister.slaaSammen(hm1, hm2);
    }
    public static Subsekvens max(HashMap<String,Subsekvens> hm){
        return SubsekvensRegister.max(hm);
    }
}