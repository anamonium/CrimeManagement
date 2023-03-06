package manageDatabase;

import entity.*;
import jakarta.persistence.*;
import org.hibernate.Session;
import view.Crime;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManageDatabase {

    EntityManagerFactory fac;
    EntityManager man;
    EntityTransaction tr;
    Session s;

    TypedQuery<PersonEntity> personBySSN;
    TypedQuery<StateEntity> getState;
    TypedQuery<CountryEntity> getCountry;

    TypedQuery<AddressEntity> getAddress;
    TypedQuery<String> charges;

    TypedQuery<ArrestedPersonEntity> arrestedPerson;

    TypedQuery<CriminalChargesEntity> crimes;

    TypedQuery<OfficerEntity> officer;

    TypedQuery<ArrestReportEntity> allArrestReports;
    TypedQuery<CrimeReportEntity> allCrimeRecords;
    public ManageDatabase(){
        fac = Persistence.createEntityManagerFactory("default");
        man = fac.createEntityManager();
        s = man.unwrap(Session.class);
        tr = man.getTransaction();
    }

    public List<ArrestReportEntity> getAllArrestReports(){
        allArrestReports = man.createNamedQuery("allArrestReports", ArrestReportEntity.class);

        return allArrestReports.getResultList();
    }

    public List<CrimeReportEntity> getAllCrimeReports(){
        allCrimeRecords = man.createNamedQuery("allCrimeRecords", CrimeReportEntity.class);

        return allCrimeRecords.getResultList();
    }

    public PersonEntity addPerson(String fName, String mName, String lName, String SSN, String phone, AddressEntity address){
        personBySSN = man.createNamedQuery("personBySSN", PersonEntity.class);
        personBySSN.setParameter(1, SSN);

        int ilosc = personBySSN.getResultList().size();

        if(ilosc != 0)
            return null;
        else{
            tr.begin();
            PersonEntity person = new PersonEntity();
            person.setFirstName(fName);
            person.setMiddleName(mName);
            person.setLastName(lName);
            person.setSocialSecurityNumber(SSN);
            person.setPhone(phone);
            person.setAddressByIdAddress(address);
            man.persist(person);
            tr.commit();

            return person;
        }
    }

    public AddressEntity addAddress(String street, int buNo, String PoCo, String city, StateEntity state){
        getAddress = man.createNamedQuery("getAddress", AddressEntity.class);
        getAddress.setParameter(1, street);
        getAddress.setParameter(2, buNo);
        getAddress.setParameter(3,PoCo );
        getAddress.setParameter(4, city);
        getAddress.setParameter(5, state.getState());
        getAddress.setParameter(6, state.getCountryByIdCountry().getCountry());

        if(getAddress.getResultList().size() != 0)
            return getAddress.getSingleResult();
        else{
            tr.begin();
            AddressEntity addressEntity = new AddressEntity();
            addressEntity.setStreet(street);
            addressEntity.setBuildingNumber(buNo);
            addressEntity.setPostalCode(PoCo);
            addressEntity.setCity(city);
            addressEntity.setStateByIdState(state);
            man.persist(addressEntity);
            tr.commit();

            return addressEntity;
        }
    }

    public StateEntity addState(String state, CountryEntity country){
        getState = man.createNamedQuery("getState", StateEntity.class);
        getState.setParameter(1, state);
        getState.setParameter(2, country.getCountry());

        if(getState.getResultList().size() !=0 )
            return getState.getSingleResult();
        else{
            tr.begin();
            StateEntity stat = new StateEntity();
            stat.setState(state);
            stat.setCountryByIdCountry(country);
            man.persist(stat);
            tr.commit();

            return stat;
        }
    }

    public CountryEntity addCountry(String name){
        getCountry = man.createNamedQuery("getCountry", CountryEntity.class);
        getCountry.setParameter(1, name);

        if(getCountry.getResultList().size() !=0 )
            return getCountry.getSingleResult();
        else{
            tr.begin();
            CountryEntity country = new CountryEntity();
            country.setCountry(name);
            man.persist(country);
            tr.commit();

            return country;
        }
    }
    public void addArrestReport(){

    }

    public PersonEntity getPersonBySSN(String SSN){
        personBySSN = man.createNamedQuery("personBySSN", PersonEntity.class);
        personBySSN.setParameter(1, SSN);

        int ilosc = personBySSN.getResultList().size();

        if(ilosc == 0)
            return null;
        else{
            return personBySSN.getSingleResult();
        }
    }

    public List<String> charges(){
        charges = man.createNamedQuery("charges", String.class);
        List<String> lista = new ArrayList<>();

        for(String c: charges.getResultList()){
            lista.add(c);
        }

        return lista;
    }

    public ArrestedPersonEntity addArrestedPerson(PersonEntity person, LocalDate dateOfBirth, String locationOfBirth, double heigth, double weigth, String race, String sex, String hair, String eyes){
        arrestedPerson = man.createNamedQuery("arrestedPersonBySSN", ArrestedPersonEntity.class);
        arrestedPerson.setParameter(1, person.getSocialSecurityNumber());
        int ilosc = arrestedPerson.getResultList().size();

        if(ilosc != 0)
            return arrestedPerson.getSingleResult();
        else{
            tr.begin();
            ArrestedPersonEntity arr = new ArrestedPersonEntity();
            arr.setPersonByIdPerson(person);
            arr.setDateOfBirth(dateOfBirth);
            arr.setPlaceOfBirth(locationOfBirth);
            arr.setHeight(heigth);
            arr.setWeight(weigth);
            arr.setRace(race);
            arr.setSex(sex);
            arr.setHair(hair);
            arr.setEyes(eyes);
            man.persist(arr);
            tr.commit();
            return arr;
        }
    }

    public ReportEntity addReport(LocalDate date, String description, AddressEntity location, OfficerEntity officer){
        tr.begin();
        ReportEntity rep = new ReportEntity();
        rep.setDate(date);
        rep.setDescription(description);
        rep.setAddressByIdLocation(location);
        rep.setOfficerByIdOfficer(officer);
        man.persist(rep);
        tr.commit();

        return rep;

    }
    public ArrestReportEntity addArrestReport(ReportEntity rep, ArrestedPersonEntity aPe, CriminalChargesEntity cr){
        tr.begin();
        ArrestReportEntity ar = new ArrestReportEntity();
        ar.setReportByIdReport(rep);
        ar.setArrestedPersonByIdArrestedPerson(aPe);
        ar.setCriminalChargesByIdCriminalCharges(cr);
        man.persist(ar);
        tr.commit();

        return ar;

    }

    public CriminalChargesEntity crime(String crime){
        crimes = man.createNamedQuery("charg", CriminalChargesEntity.class);
        crimes.setParameter(1, crime);

        return crimes.getSingleResult();
    }

    public CrimeReportEntity insertCrimeReport(PersonEntity victim, CriminalChargesEntity crimeType, ReportEntity report){
        tr.begin();
        CrimeReportEntity crime = new CrimeReportEntity();
        crime.setReportByIdReport(report);
        crime.setCriminalChargesByIdCrimeType(crimeType);
        crime.setPersonByIdVictim(victim);
        man.persist(crime);
        tr.commit();

        return crime;
    }

    public void addWitnessToCrimeReport(PersonEntity witness, CrimeReportEntity crimeReport, String testimony){
        tr.begin();
        CrimeWitnessesEntity witnessesEntity = new CrimeWitnessesEntity();
        witnessesEntity.setCrimeReportByIdCrimeReport(crimeReport);
        witnessesEntity.setPersonByIdWitness(witness);
        witnessesEntity.setTestimony(testimony);
        man.persist(witnessesEntity);
        tr.commit();

    }

    public OfficerEntity getOfficer(int badgeNumber, String username, String password){
        officer = man.createNamedQuery("getOfficer", OfficerEntity.class);
        officer.setParameter(1, badgeNumber);
        officer.setParameter(2, username);
        officer.setParameter(3, password);

        if(officer.getSingleResult() != null )
            return officer.getSingleResult();
        else
            return null;
    }
}
