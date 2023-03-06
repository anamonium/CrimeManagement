package reports;

import entity.*;

import java.time.LocalDate;

public class ArrestReport extends Report{


    public ArrestReport(){
        super();
    }


    public ArrestedPersonEntity savePerson(PersonEntity person, String dateOfBirth, String locationOfBirth, String heigth, String weigth, String race, String sex, String hair, String eyes){
        locationOfBirth = convertString(locationOfBirth);
        double weig, heig;
        try{
            weig = Double.parseDouble(weigth);
        }catch (NumberFormatException e){
            weig = 0;
        }

        try{
            heig = Double.parseDouble(heigth);
        }catch (NumberFormatException e){
            heig = 0;
        }
        LocalDate date = convertToDate(dateOfBirth);
        race = convertString(race);
        sex = convertString(sex);
        hair = convertString(hair);
        eyes = convertString(eyes);

        return manageDatabase.addArrestedPerson(person, date, locationOfBirth, heig,weig, race, sex, hair, eyes);
    }

    public ArrestReportEntity addArestReport(ReportEntity rep, ArrestedPersonEntity aPe, CriminalChargesEntity cr){
        return manageDatabase.addArrestReport(rep, aPe, cr);
    }
}
