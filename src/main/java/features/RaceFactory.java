package features;

import java.util.ArrayList;
import java.util.List;

public class RaceFactory {

    public static List<IFlyweight> flyweightList = new ArrayList<>();

    public static IFlyweight getFlyweight(String name){
        if(flyweightList.contains(name)) {
            int index = flyweightList.indexOf(name);
            return flyweightList.get(index);
        }
        else{
            IFlyweight tmp = new Race(name);
            flyweightList.add(tmp);
            return tmp;
        }
    }

}
