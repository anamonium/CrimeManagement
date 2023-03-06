package view;

import Officer.Officer;
import entity.*;
import reports.CrimeReport;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

public class Crime {
    private JPanel panel1;
    private JTextField fNameField;
    private JTextField dateField;
    private JLabel reportField;
    private JTextField mNameField;
    private JTextField streetField1;
    private JTextField postalCodeField1;
    private JTextField cityField1;
    private JTextField ssnField;
    private JTextField phoneField;
    private JTextField streetField2;
    private JButton saveButton;
    private JTextField bNoField2;
    private JTextField postalCodeField2;
    private JLabel officerNumberLabel;
    JList<String> witnessList;
    DefaultListModel<String> listModel;
    private JButton addWitnessButton;
    private JButton loadPersonBySSNButton;
    private JComboBox crimeBox;
    private JTextField lNameField;
    private JTextField stateField1;
    private JTextField countryField1;
    private JTextField bNoField1;
    private JTextField cityField2;
    private JTextField stateField2;
    private JTextField countryField2;
    private JTextField descriptionField;
    private JLabel reportNumber;

    static JFrame crimeFrame;
    private static Crime crimeForm;
    private CrimeReport crimeReport;

    private CrimeReportEntity crmRep;

public Crime(CrimeReport report) {

    this.crimeReport = report;
    listModel = new DefaultListModel<>();

    loadPersonBySSNButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            PersonEntity person = crimeReport.loadPersonBySSN(ssnField.getText());

            if(person == null)
                JOptionPane.showMessageDialog(null, "Taka osoba nie istnieje");
            else{
                AddressEntity address = person.getAddressByIdAddress();
                fNameField.setText(person.getFirstName());
                mNameField.setText(person.getMiddleName());
                lNameField.setText(person.getLastName());
                phoneField.setText(person.getPhone());
                streetField1.setText(address.getStreet());
                bNoField1.setText(Integer.toString(address.getBuildingNumber()));
                countryField1.setText(address.getStateByIdState().getCountryByIdCountry().getCountry());
                stateField1.setText(address.getStateByIdState().getState());
                cityField1.setText(address.getCity());
                postalCodeField1.setText(address.getPostalCode());

            }
        }
    });
    addWitnessButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(addWitnessButton.getText().equals("Add witness")) {
                AddWitness addWitness = new AddWitness(crimeReport);
                addWitness.createAddWitnessView(crimeReport);
            }
            else{
                int index = witnessList.getSelectedIndex();
                List<CrimeWitnessesEntity> wit = (List<CrimeWitnessesEntity>) crmRep.getCrimeWitnessesByIdCrimeReport();
                AddWitness.showWitnessDetails(wit.get(index));
            }
        }
    });
    saveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            if(fNameField.getText().equals("") || lNameField.getText().equals("") || ssnField.getText().equals("")
                    || streetField1.getText().equals("")
                    ||bNoField1.getText().equals("") || postalCodeField1.getText().equals("") || cityField1.getText().equals("")
                    ||stateField1.getText().equals("") || countryField1.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please provide all victim's information");
            }
            else if(streetField2.getText().equals("") || postalCodeField2.getText().equals("") || cityField2.getText().equals("")
                    || stateField2.getText().equals("") || countryField2.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please provide all information about location of the crime");
            }
            else {
                AddressEntity addressWictim = crimeReport.checkFormat(streetField1.getText(), bNoField1.getText(), postalCodeField1.getText(), cityField1.getText(), stateField1.getText(), countryField1.getText());
                AddressEntity addressCrime = crimeReport.checkFormat(streetField2.getText(), bNoField2.getText(), postalCodeField2.getText(), cityField2.getText(), stateField2.getText(), countryField2.getText());
                OfficerEntity off = Officer.getInstance(new OfficerEntity()).getOfficerEntity();
                ReportEntity report1 = crimeReport.newReport(dateField.getText(), descriptionField.getText(), addressCrime, off);

                PersonEntity person = crimeReport.addPerson(fNameField.getText(), mNameField.getText(), lNameField.getText(), ssnField.getText(), phoneField.getText(), addressWictim);

                CriminalChargesEntity charges = crimeReport.charge((String) crimeBox.getSelectedItem());

                HashMap<PersonEntity, String> witnesses = crimeReport.getWitnesses();

                CrimeReportEntity crm = crimeReport.insertCrimeReport(person, charges, report1);

                for (PersonEntity i : witnesses.keySet()) {
                    crimeReport.insertWitness(i, crm, witnesses.get(i));
                }

                crimeFrame.setVisible(false);
            }

        }
    });
}

    public static void initFormNewReport(Crime crimeForm , CrimeReport crimeReport){
        List<String> charges = crimeReport.charges();
        for(int i = 0; i < charges.size(); i++){
            crimeForm.crimeBox.addItem(charges.get(i));
        }
    }

    public static void showCrimeReport(CrimeReportEntity crimeReport, Crime crimeForm){
        ReportEntity rep = crimeReport.getReportByIdReport();
        List<CrimeWitnessesEntity> witnesses = (List<CrimeWitnessesEntity>) crimeReport.getCrimeWitnessesByIdCrimeReport();
        PersonEntity victim = crimeReport.getPersonByIdVictim();
        CriminalChargesEntity crimeType = crimeReport.getCriminalChargesByIdCrimeType();
        AddressEntity crimeAddress = rep.getAddressByIdLocation();
        AddressEntity victimAddress = victim.getAddressByIdAddress();

        crimeForm.crmRep = crimeReport;

        crimeForm.reportNumber.setText(Integer.toString(rep.getIdReport()));
        crimeForm.dateField.setText(rep.getDate().toString());
        crimeForm.dateField.setEditable(false);

        crimeForm.fNameField.setText(victim.getFirstName());
        crimeForm.fNameField.setEditable(false);
        crimeForm.mNameField.setText(victim.getMiddleName());
        crimeForm.mNameField.setEditable(false);
        crimeForm.lNameField.setText(victim.getLastName());
        crimeForm.lNameField.setEditable(false);
        crimeForm.phoneField.setText(victim.getPhone());
        crimeForm.phoneField.setEditable(false);
        crimeForm.ssnField.setText(victim.getSocialSecurityNumber());
        crimeForm.ssnField.setEditable(false);

        crimeForm.streetField1.setText(victimAddress.getStreet());
        crimeForm.streetField1.setEditable(false);
        crimeForm.bNoField1.setText(Integer.toString(victimAddress.getBuildingNumber()));
        crimeForm.bNoField1.setEditable(false);
        crimeForm.postalCodeField1.setText(victimAddress.getPostalCode());
        crimeForm.postalCodeField1.setEditable(false);
        crimeForm.cityField1.setText(victimAddress.getCity());
        crimeForm.cityField1.setEditable(false);
        crimeForm.stateField1.setText(victimAddress.getStateByIdState().getState());
        crimeForm.stateField1.setEditable(false);
        crimeForm.countryField1.setText(victimAddress.getStateByIdState().getCountryByIdCountry().getCountry());
        crimeForm.countryField1.setEditable(false);

        crimeForm.streetField2.setText(crimeAddress.getStreet());
        crimeForm.streetField2.setEditable(false);
        crimeForm.bNoField2.setText(Integer.toString(crimeAddress.getBuildingNumber()));
        crimeForm.bNoField2.setEditable(false);
        crimeForm.postalCodeField2.setText(crimeAddress.getPostalCode());
        crimeForm.postalCodeField2.setEditable(false);
        crimeForm.cityField2.setText(crimeAddress.getCity());
        crimeForm.cityField2.setEditable(false);
        crimeForm.stateField2.setText(crimeAddress.getStateByIdState().getState());
        crimeForm.stateField2.setEditable(false);
        crimeForm.countryField2.setText(crimeAddress.getStateByIdState().getCountryByIdCountry().getCountry());
        crimeForm.countryField2.setEditable(false);

        crimeForm.descriptionField.setText(rep.getDescription());
        crimeForm.descriptionField.setEditable(false);

        crimeForm.officerNumberLabel.setText(Integer.toString(rep.getOfficerByIdOfficer().getBadgeNumber()));
        crimeForm.reportNumber.setText(Integer.toString(rep.getIdReport()));

        for(CrimeWitnessesEntity c: witnesses){
            crimeForm.listModel.addElement(c.getPersonByIdWitness().getFirstName() + " " + c.getPersonByIdWitness().getLastName());
        }

        crimeForm.witnessList.setModel(crimeForm.listModel);

        crimeForm.crimeBox.removeAllItems();
        crimeForm.crimeBox.addItem(crimeType.getCrimeType());

        crimeForm.saveButton.setEnabled(false);
        crimeForm.addWitnessButton.setText("Show witness details");

    }

    public static void addWitnessToList(String elem){
        crimeForm.listModel.addElement(elem);
        crimeForm.witnessList.setModel(crimeForm.listModel);
    }

    public static void createCrimeView(){
        crimeFrame = new JFrame();
        CrimeReport crim = new CrimeReport();
        crimeForm = new Crime(crim);
        crimeFrame.setContentPane(crimeForm.panel1);
        crimeFrame.pack();
        initFormNewReport(crimeForm, crim);
        crimeFrame.setVisible(true);
    }

    public static void showDetails(CrimeReportEntity crimeReport){
        crimeFrame = new JFrame();
        CrimeReport crim = new CrimeReport();
        crimeForm = new Crime(crim);
        crimeFrame.setContentPane(crimeForm.panel1);
        crimeFrame.pack();
        showCrimeReport(crimeReport,crimeForm);
        crimeFrame.setVisible(true);
    }
}
