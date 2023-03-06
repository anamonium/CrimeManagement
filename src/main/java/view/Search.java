package view;

import entity.ArrestReportEntity;
import entity.CrimeReportEntity;
import manageDatabase.ManageDatabase;
import reports.ArrestReport;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Search {
    private JPanel panel1;
    private JList<String> resultList;
    DefaultListModel<String> listModel;
    private JButton showDetailsButton;

    static JFrame searchFrame;
    private static Search searchForm;

    List<CrimeReportEntity> listOfCrimes;
    List<ArrestReportEntity> listOfArrests;
public Search() {

    listModel = new DefaultListModel<>();
    ManageDatabase mgd = new ManageDatabase();

    listOfCrimes = mgd.getAllCrimeReports();
    listOfArrests = mgd.getAllArrestReports();

    for(CrimeReportEntity c: listOfCrimes){
        listModel.addElement("Crime " + c.getCriminalChargesByIdCrimeType().getCrimeType() + " " + c.getReportByIdReport().getDate());
    }

    for(ArrestReportEntity a: listOfArrests){
        listModel.addElement("Arrest " + a.getCriminalChargesByIdCriminalCharges().getCrimeType() + a.getReportByIdReport().getDate());
    }

    resultList.setModel(listModel);


    showDetailsButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = resultList.getSelectedIndex();
            int crimeSize = listOfCrimes.size();
            int arrestSize = listOfArrests.size();

            if(index <= crimeSize - 1){
                CrimeReportEntity cri = listOfCrimes.get(index);
                Crime.showDetails(cri);
            }
            else{
                index = index -crimeSize;
                ArrestReportEntity arr = listOfArrests.get(index);
                Arrest.showArrestDetails(arr);
            }
        }
    });
}

    public void showSearch(){
        searchFrame = new JFrame();
        searchForm = new Search();
        searchFrame.setContentPane(searchForm.panel1);
        searchFrame.pack();
        searchFrame.setVisible(true);
    }
}
