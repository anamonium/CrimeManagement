package features;

public class Hair implements IFlyweight{

    private final String hair;

    public Hair(String hair){
        this.hair = hair;
    }

    @Override
    public String getName() {
        return this.hair;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Hair)) {
            return false;
        }
        Hair flyweight = (Hair)o;
        return this.hair.equals(flyweight.getName());
    }

}
