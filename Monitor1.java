import java.util.HashMap;

class Monitor1{
    private SubsekvensRegister ss;
    public Monitor1(){
        this.ss = new SubsekvensRegister();
    }
    public synchronized void settInn(HashMap<String,Subsekvens> sub){
        this.ss.settInn(sub);
    }

    public synchronized HashMap<String,Subsekvens> taUt(int index){
        return this.ss.taUt(index);
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