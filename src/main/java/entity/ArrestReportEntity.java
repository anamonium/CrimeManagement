package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "arrest_report", schema = "crimeManagament-TOproject")
@NamedQuery(name = "allArrestReports", query = "SELECT a from ArrestReportEntity a")
public class ArrestReportEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_arrest_report")
    private int idArrestReport;
//    @Basic
//    @Column(name = "id_arrested_person")
//    private int idArrestedPerson;
//    @Basic
//    @Column(name = "id_criminal_charges")
//    private int idCriminalCharges;
//    @Basic
//    @Column(name = "id_report")
//    private Integer idReport;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_arrested_person", referencedColumnName = "id_arrested_person", nullable = false)
    private ArrestedPersonEntity arrestedPersonByIdArrestedPerson;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_criminal_charges", referencedColumnName = "ID_CrimeType", nullable = false)
    private CriminalChargesEntity criminalChargesByIdCriminalCharges;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_report", referencedColumnName = "id_report")
    private ReportEntity reportByIdReport;

    public int getIdArrestReport() {
        return idArrestReport;
    }

    public void setIdArrestReport(int idArrestReport) {
        this.idArrestReport = idArrestReport;
    }

//    public int getIdArrestedPerson() {
//        return idArrestedPerson;
//    }
//
//    public void setIdArrestedPerson(int idArrestedPerson) {
//        this.idArrestedPerson = idArrestedPerson;
//    }
//
//    public int getIdCriminalCharges() {
//        return idCriminalCharges;
//    }
//
//    public void setIdCriminalCharges(int idCriminalCharges) {
//        this.idCriminalCharges = idCriminalCharges;
//    }

//    public Integer getIdReport() {
//        return idReport;
//    }
//
//    public void setIdReport(Integer idReport) {
//        this.idReport = idReport;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrestReportEntity that = (ArrestReportEntity) o;

        if (idArrestReport != that.idArrestReport) return false;
//        if (idArrestedPerson != that.idArrestedPerson) return false;
//        if (idCriminalCharges != that.idCriminalCharges) return false;
        //if (idReport != null ? !idReport.equals(that.idReport) : that.idReport != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idArrestReport;
//        result = 31 * result + idArrestedPerson;
//        result = 31 * result + idCriminalCharges;
        //result = 31 * result + (idReport != null ? idReport.hashCode() : 0);
        return result;
    }

    public ArrestedPersonEntity getArrestedPersonByIdArrestedPerson() {
        return arrestedPersonByIdArrestedPerson;
    }

    public void setArrestedPersonByIdArrestedPerson(ArrestedPersonEntity arrestedPersonByIdArrestedPerson) {
        this.arrestedPersonByIdArrestedPerson = arrestedPersonByIdArrestedPerson;
    }

    public CriminalChargesEntity getCriminalChargesByIdCriminalCharges() {
        return criminalChargesByIdCriminalCharges;
    }

    public void setCriminalChargesByIdCriminalCharges(CriminalChargesEntity criminalChargesByIdCriminalCharges) {
        this.criminalChargesByIdCriminalCharges = criminalChargesByIdCriminalCharges;
    }

    public ReportEntity getReportByIdReport() {
        return reportByIdReport;
    }

    public void setReportByIdReport(ReportEntity reportByIdReport) {
        this.reportByIdReport = reportByIdReport;
    }
}
