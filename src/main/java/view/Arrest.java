package view;

import Officer.Officer;
import entity.*;
import features.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import manageDatabase.ManageDatabase;
import reports.ArrestReport;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class Arrest {
    private JPanel panel1;
    private JTextField nameField;
    private JTextField middleNameField;
    private JTextField lastNameField;
    private JTextField SSNField;
    private JTextField phoneField;
    private JTextField streetField1;
    private JTextField postalCodeField1;
    private JTextField bNoField1;
    private JTextField raceField;
    private JTextField sexField;
    private JTextField hairTextField;
    private JTextField eyesField;
    private JTextField heigthField;
    private JTextField weigthField;
    private JTextField dateOfBirthField;
    private JTextField locationOfBirthField;
    private JTextField dateField;
    private JTextField streetField2;
    private JTextField descriptionField;
    private JLabel officerNumberLabel;
    private JButton saveButton;
    private JTextField bNoField2;
    private JTextField postalCodeField2;
    private JTextField countryField1;
    private JTextField stateField1;
    private JTextField cityField1;
    private JButton loadPersonBySSNButton;
    private JComboBox chargesBox;
    private JTextField stateField2;
    private JTextField cityField2;
    private JTextField countryField2;
    private JLabel reportNumberLabel;


    static JFrame arrestFrame;
    private static Arrest arrestForm;

    private ArrestReport arrestReport;

public Arrest(ArrestReport arrest) {
    this.arrestReport = arrest;

    loadPersonBySSNButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ManageDatabase mng = new ManageDatabase();
            String SSN = SSNField.getText();

            PersonEntity personEntity = mng.getPersonBySSN(SSN);
            if(personEntity == null)
                JOptionPane.showMessageDialog(null, "Person with this SSN doesn't exist");
            else{
                AddressEntity addressEntity = personEntity.getAddressByIdAddress();
                nameField.setText(personEntity.getFirstName());
                middleNameField.setText(personEntity.getMiddleName());
                lastNameField.setText(personEntity.getLastName());
                phoneField.setText(personEntity.getPhone());
                streetField1.setText(addressEntity.getStreet());
                bNoField1.setText(Integer.toString(addressEntity.getBuildingNumber()));
                postalCodeField1.setText(addressEntity.getPostalCode());
                cityField1.setText(addressEntity.getCity());
                stateField1.setText(addressEntity.getStateByIdState().getState());
                countryField1.setText(addressEntity.getStateByIdState().getCountryByIdCountry().getCountry());

            }
        }
    });
    saveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            if(nameField.getText().equals("") || lastNameField.getText().equals("") || SSNField.getText().equals("")
            || dateOfBirthField.getText().equals("") || raceField.getText().equals("") || sexField.getText().equals("")
            || hairTextField.getText().equals("") || eyesField.getText().equals("") || streetField1.getText().equals("")
            ||bNoField1.getText().equals("") || postalCodeField1.getText().equals("") || cityField1.getText().equals("")
            ||stateField1.getText().equals("") || countryField1.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please provide all arrestee's information");
            }
            else if(streetField2.getText().equals("") || postalCodeField2.getText().equals("") || cityField2.getText().equals("")
            || stateField2.getText().equals("") || countryField2.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please provide all information about location of the arrest");
            }
            else {

                IFlyweight eyes = EyesFactory.getFlyweight(eyesField.getText());
                IFlyweight hair = HairFactory.getFlyweight(hairTextField.getText());
                IFlyweight race = RaceFactory.getFlyweight(raceField.getText());
                IFlyweight sex = SexFactory.getFlyweight(sexField.getText());

                AddressEntity addressArrest = arrestReport.checkFormat(streetField2.getText(), bNoField2.getText(), postalCodeField2.getText(), cityField2.getText(), stateField2.getText(), countryField2.getText());
                AddressEntity addressArrestee = arrestReport.checkFormat(streetField1.getText(), bNoField1.getText(), postalCodeField1.getText(), cityField1.getText(), stateField1.getText(), countryField1.getText());

                PersonEntity person = arrestReport.addPerson(nameField.getText(), middleNameField.getText(), lastNameField.getText(), SSNField.getText(), phoneField.getText(), addressArrestee);

                ArrestedPersonEntity arestedPerson = arrestReport.savePerson(person, dateOfBirthField.getText(), locationOfBirthField.getText(), heigthField.getText(), weigthField.getText(), race.getName(), sex.getName(), hair.getName(), eyes.getName());

                CriminalChargesEntity charges = arrestReport.charge((String) chargesBox.getSelectedItem());

                OfficerEntity off = Officer.getInstance(new OfficerEntity()).getOfficerEntity();

                ReportEntity rep = arrestReport.newReport(dateField.getText(), descriptionField.getText(), addressArrest, off);

                ArrestReportEntity arrestReportEntity = arrestReport.addArestReport(rep, arestedPerson, charges);

                arrestFrame.setVisible(false);
            }
        }
    });

}

public static void showInfo(ArrestReportEntity arrest, Arrest arrestform){
    ArrestedPersonEntity arrestedPerson = arrest.getArrestedPersonByIdArrestedPerson();
    CriminalChargesEntity criminalCharges = arrest.getCriminalChargesByIdCriminalCharges();
    ReportEntity rep = arrest.getReportByIdReport();
    PersonEntity per = arrestedPerson.getPersonByIdPerson();
    AddressEntity arrestLoc = rep.getAddressByIdLocation();
    AddressEntity arrestPer = per.getAddressByIdAddress();

    arrestform.nameField.setText(per.getFirstName());
    arrestform.nameField.setEditable(false);

    arrestform.middleNameField.setText(per.getMiddleName());
    arrestform.middleNameField.setEditable(false);

    arrestform.lastNameField.setText(per.getLastName());
    arrestform.lastNameField.setEditable(false);

    arrestform.SSNField.setText(per.getSocialSecurityNumber());
    arrestform.SSNField.setEditable(false);

    arrestform.phoneField.setText(per.getPhone());
    arrestform.phoneField.setEditable(false);

    arrestform.streetField1.setText(arrestPer.getStreet());
    arrestform.streetField1.setEditable(false);

    arrestform.postalCodeField1.setText(arrestPer.getPostalCode());
    arrestform.postalCodeField1.setEditable(false);

    arrestform.bNoField1.setText(Integer.toString(arrestPer.getBuildingNumber()));
    arrestform.bNoField1.setEditable(false);

    arrestform.postalCodeField1.setText(arrestPer.getPostalCode());
    arrestform.postalCodeField1.setEditable(false);

    arrestform.stateField1.setText(arrestPer.getStateByIdState().getState());
    arrestform.stateField1.setEditable(false);

    arrestform.cityField1.setText(arrestPer.getCity());
    arrestform.cityField1.setEditable(false);

    arrestform.countryField1.setText(arrestPer.getStateByIdState().getCountryByIdCountry().getCountry());
    arrestform.countryField1.setEditable(false);

    arrestform.raceField.setText(arrestedPerson.getRace());
    arrestform.raceField.setEditable(false);

    arrestform.hairTextField.setText(arrestedPerson.getHair());
    arrestform.hairTextField.setEditable(false);

    arrestform.eyesField.setText(arrestedPerson.getEyes());
    arrestform.eyesField.setEditable(false);

    arrestform.sexField.setText(arrestedPerson.getSex());
    arrestform.sexField.setEditable(false);

    arrestform.heigthField.setText(Double.toString(arrestedPerson.getHeight()));
    arrestform.heigthField.setEditable(false);

    arrestform.weigthField.setText(Double.toString(arrestedPerson.getWeight()));
    arrestform.weigthField.setEditable(false);

    arrestform.dateOfBirthField.setText(arrestedPerson.getDateOfBirth().toString());
    arrestform.dateOfBirthField.setEditable(false);

    arrestform.locationOfBirthField.setText(arrestedPerson.getPlaceOfBirth());
    arrestform.locationOfBirthField.setEditable(false);

    arrestform.dateField.setText(rep.getDate().toString());
    arrestform.dateField.setEditable(false);

    arrestform.descriptionField.setText(rep.getDescription());

    arrestform.streetField2.setText(arrestLoc.getStreet());
    arrestform.streetField2.setEditable(false);

    arrestform.bNoField2.setText(Integer.toString(arrestLoc.getBuildingNumber()));
    arrestform.bNoField2.setEditable(false);

    arrestform.postalCodeField2.setText(arrestLoc.getPostalCode());
    arrestform.postalCodeField2.setEditable(false);

    arrestform.countryField2.setText(arrestLoc.getStateByIdState().getCountryByIdCountry().getCountry());
    arrestform.countryField2.setEditable(false);

    arrestform.stateField2.setText(arrestLoc.getStateByIdState().getState());
    arrestform.stateField2.setEditable(false);

    arrestform.cityField2.setText(arrestLoc.getCity());
    arrestform.cityField2.setEditable(false);

    arrestform.officerNumberLabel.setText(Integer.toString(rep.getOfficerByIdOfficer().getBadgeNumber()));
    arrestform.reportNumberLabel.setText(Integer.toString(rep.getIdReport()));

    arrestform.loadPersonBySSNButton.setEnabled(false);
    arrestform.saveButton.setEnabled(false);

    arrestform.chargesBox.removeAllItems();
    arrestform.chargesBox.addItem(criminalCharges.getCrimeType());


}

    public static void initFormNewReport(Arrest arrestform ,ArrestReport arrestReports){
        List<String> charges = arrestReports.charges();
        for(int i = 0; i < charges.size(); i++){
            arrestform.chargesBox.addItem(charges.get(i));
        }
    }

public static void createArrestView(){
    arrestFrame = new JFrame();
    ArrestReport arr = new ArrestReport();
    arrestForm = new Arrest(arr);
    arrestFrame.setContentPane(arrestForm.panel1);
    arrestFrame.pack();
    initFormNewReport(arrestForm, arr);
    arrestFrame.setVisible(true);
}

public static void showArrestDetails(ArrestReportEntity arrestReport){
    arrestFrame = new JFrame();
    ArrestReport arr = new ArrestReport();
    arrestForm = new Arrest(arr);
    arrestFrame.setContentPane(arrestForm.panel1);
    arrestFrame.pack();
    showInfo(arrestReport, arrestForm);
    arrestFrame.setVisible(true);
}
}
