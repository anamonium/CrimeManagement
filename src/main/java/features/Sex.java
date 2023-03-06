package features;

public class Sex implements IFlyweight{

    private final String sex;

    public Sex(String sex){
        this.sex = sex;
    }

    @Override
    public String getName() {
        return this.sex;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Sex)) {
            return false;
        }

        Sex flyweight = (Sex)o;
        return this.sex.equals(flyweight.getName());
    }

}
