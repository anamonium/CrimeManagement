package reports;

import entity.*;
import manageDatabase.ManageDatabase;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

public class Report {

    protected ManageDatabase manageDatabase;
    public Report(){
        //this.reportEntity = reportEntity;
        this.manageDatabase = new ManageDatabase();
    }

    public PersonEntity loadPersonBySSN(String SSN){
        return manageDatabase.getPersonBySSN(SSN);
    }


    public List<String> charges(){
        return manageDatabase.charges();
    }

    public AddressEntity checkFormat(String street, String buildingNo, String postalCode, String city, String state, String country){
        street = convertString(street);
        postalCode = convertString(postalCode);
        city = convertString(city);
        state = convertString(state);
        country = convertString(country);
        int build;

        try {
             build = Integer.parseInt(buildingNo);
        }catch(NumberFormatException ex){
            build = 0;
        }

        return manageDatabase.addAddress(street, build, postalCode, city, manageDatabase.addState(state, manageDatabase.addCountry(country)));
    }

    public PersonEntity addPerson(String name, String mName, String lName, String ssn, String phone, AddressEntity address){
        name = convertString(name);
        mName = convertString(mName);
        lName = convertString(lName);
        ssn = convertString(ssn);
        phone = convertString(phone);

        return manageDatabase.addPerson(name, mName, lName, ssn, phone, address);
    }

    protected String convertString(String name){
        if(name == null)
            return "";
        if(name.equals(""))
            return "";
        return name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
    }

    protected LocalDate convertToDate(String date){
        try {
            LocalDate localDate = LocalDate.parse(date);
            return localDate;
        } catch (DateTimeParseException e) {

            return null;
        }
    }

    public CriminalChargesEntity charge(String charge){
        return manageDatabase.crime(charge);
    }

    public ReportEntity newReport(String date, String description, AddressEntity location, OfficerEntity officer){
        LocalDate dat = convertToDate(date);
        return manageDatabase.addReport(dat, description, location, officer);
    }
}
