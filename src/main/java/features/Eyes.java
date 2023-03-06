package features;

public class Eyes implements IFlyweight{

    private final String eyes;

    public Eyes(String eyes){
        this.eyes = eyes;
    }

    @Override
    public String getName() {
        return this.eyes;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Eyes)) {
            return false;
        }

        Eyes flyweight = (Eyes) o;
        return this.eyes.equals(flyweight.getName());
    }
}
