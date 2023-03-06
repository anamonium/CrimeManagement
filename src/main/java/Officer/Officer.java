package Officer;

import entity.OfficerEntity;

public class Officer {

    public static Officer officer;
    OfficerEntity officerEntity;

    private Officer(OfficerEntity off){
        this.officerEntity = off;
    }

    public static Officer getInstance(OfficerEntity off){
       if(officer == null) {
           officer = new Officer(off);
       }

       return officer;
    }

    public OfficerEntity getOfficerEntity() {
        return officerEntity;
    }
}
