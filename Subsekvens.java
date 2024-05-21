

class Subsekvens{
    public final String subsekvens;
    private int antall;
    public Subsekvens(int antall, String subsekvens){
        this.antall = antall;
        this.subsekvens = subsekvens;
    }
    public String toString(){
        return String.format("(%s,%d)", subsekvens, antall);
    }
    public int getAntall(){
        return this.antall;
    }
    public void setAntall(int a){
        this.antall = a;
    }
}