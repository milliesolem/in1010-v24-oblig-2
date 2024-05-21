import java.util.ArrayList;
import java.util.HashMap;

class FletteTrad extends Thread{


    Monitor2 m2;

    public FletteTrad(Monitor2 m2){
        this.m2 = m2;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            while(m2.size()>1){
                ArrayList<HashMap<String, Subsekvens>> tmp = m2.hentUtToElectricBoogaloo(m2.size()-1, m2.size()-2);
                HashMap<String, Subsekvens> fletta = SubsekvensRegister.slaaSammen(tmp.get(0), tmp.get(1));
                m2.settInn(fletta);
            }
            assert m2.size()>0;
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }

    Monitor2 getMonitor(){
        return this.m2;
    }
}