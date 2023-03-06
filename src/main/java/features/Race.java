package features;

public class Race implements IFlyweight{

    private final String race;

    public Race(String race){
        this.race = race;
    }

    @Override
    public String getName() {
        return this.race;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Race)) {
            return false;
        }

        Race flyweight = (Race) o;
        return this.race.equals(flyweight.getName());
    }

}
