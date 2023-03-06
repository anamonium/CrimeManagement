package view;

import entity.*;
import reports.CrimeReport;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWitness {
    private JPanel panel1;
    private JTextField phoneField;
    private JButton loadPersonBySSNButton;
    private JTextField testimonyField;
    private JButton saveButton;
    private JTextField fNameField;
    private JTextField mNameField;
    private JTextField lNameField;
    private JTextField ssnField;
    private JTextField streetField1;
    private JTextField postalCodeField1;
    private JTextField bNoField1;
    private JTextField countryField1;
    private JTextField stateField1;
    private JTextField cityField1;

    static JFrame crimeFrame;
    private static AddWitness addWitnessForm;
    private CrimeReport crimeReport;



public AddWitness(CrimeReport crime) {

    this.crimeReport = crime;

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
    saveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (fNameField.getText().equals("") || lNameField.getText().equals("") || ssnField.getText().equals("")
                    || streetField1.getText().equals("")
                    || bNoField1.getText().equals("") || postalCodeField1.getText().equals("") || cityField1.getText().equals("")
                    || stateField1.getText().equals("") || countryField1.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please provide all witness's information");
            } else {

                String testimony = testimonyField.getText();

                AddressEntity add = crimeReport.checkFormat(streetField1.getText(), bNoField1.getText(), postalCodeField1.getText(), cityField1.getText(), stateField1.getText(), countryField1.getText());
                PersonEntity per = crime.addPerson(fNameField.getText(), mNameField.getText(), lNameField.getText(), ssnField.getText(), phoneField.getText(), add);

                crimeReport.addWitness(per, testimony);
                crimeFrame.setVisible(false);

                Crime.addWitnessToList(per.getFirstName() + " " +per.getLastName());
            }

        }
    });
}

    public static void initWithWitness(CrimeWitnessesEntity crimeWitness, AddWitness form){
        PersonEntity witness = crimeWitness.getPersonByIdWitness();
        AddressEntity witnessAddress = witness.getAddressByIdAddress();

        form.fNameField.setText(witness.getFirstName());
        form.fNameField.setEditable(false);
        form.mNameField.setText(witness.getMiddleName());
        form.mNameField.setEditable(false);
        form.lNameField.setText(witness.getLastName());
        form.lNameField.setEditable(false);
        form.phoneField.setText(witness.getPhone());
        form.phoneField.setEditable(false);
        form.ssnField.setText(witness.getSocialSecurityNumber());
        form.ssnField.setEditable(false);

        form.streetField1.setText(witnessAddress.getStreet());
        form.streetField1.setEditable(false);
        form.bNoField1.setText(Integer.toString(witnessAddress.getBuildingNumber()));
        form.bNoField1.setEditable(false);
        form.postalCodeField1.setText(witnessAddress.getPostalCode());
        form.postalCodeField1.setEditable(false);
        form.cityField1.setText(witnessAddress.getCity());
        form.cityField1.setEditable(false);
        form.stateField1.setText(witnessAddress.getStateByIdState().getState());
        form.stateField1.setEditable(false);
        form.countryField1.setText(witnessAddress.getStateByIdState().getCountryByIdCountry().getCountry());
        form.countryField1.setEditable(false);

        form.testimonyField.setText(crimeWitness.getTestimony());
        form.testimonyField.setEditable(false);

        form.loadPersonBySSNButton.setEnabled(false);
        form.saveButton.setEnabled(false);
    }
    public static void showWitnessDetails(CrimeWitnessesEntity crimeWitness){
        crimeFrame = new JFrame();
        CrimeReport crim = new CrimeReport();
        addWitnessForm = new AddWitness(crim);
        crimeFrame.setContentPane(addWitnessForm.panel1);
        crimeFrame.pack();
        initWithWitness(crimeWitness, addWitnessForm);
        crimeFrame.setVisible(true);
    }

    public void createAddWitnessView(CrimeReport crimeReport){
        crimeFrame = new JFrame();
        CrimeReport crim = new CrimeReport();
        addWitnessForm = new AddWitness(crimeReport);
        crimeFrame.setContentPane(addWitnessForm.panel1);
        crimeFrame.pack();
        crimeFrame.setVisible(true);
    }
}
