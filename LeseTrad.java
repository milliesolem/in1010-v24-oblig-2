class LeseTrad extends Thread{

    String filnavn;
    Monitor1 m1;

    public LeseTrad(Monitor1 m1, String filnavn){
        this.m1 = m1;
        this.filnavn = filnavn;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            m1.settInn(SubsekvensRegister.lesFil(filnavn));
        }
        catch(Exception e){

        }
    }

    Monitor1 getMonitor(){
        return this.m1;
    }
}