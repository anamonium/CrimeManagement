package reports;

import entity.CrimeReportEntity;
import entity.CriminalChargesEntity;
import entity.PersonEntity;
import entity.ReportEntity;
import view.Crime;

import java.util.HashMap;

public class CrimeReport extends Report{

    private HashMap<PersonEntity, String> witnesses;

    public CrimeReport(){
        super();
        this.witnesses = new HashMap<>();
    }

    public void addWitness(PersonEntity person, String testimony){
        witnesses.put(person, testimony);
    }

    public HashMap<PersonEntity, String> getWitnesses(){
        return this.witnesses;
    }

    public CrimeReportEntity insertCrimeReport(PersonEntity wictim, CriminalChargesEntity crmeType, ReportEntity report){
        return manageDatabase.insertCrimeReport(wictim, crmeType, report);
    }

    public void insertWitness(PersonEntity person, CrimeReportEntity crimeReport, String text){
        manageDatabase.addWitnessToCrimeReport(person, crimeReport, text);
    }


}
