package entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "crime_report", schema = "crimeManagament-TOproject")
@NamedQuery(name = "allCrimeRecords", query = "SELECT c from CrimeReportEntity c")
public class CrimeReportEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_crime_report")
    private int idCrimeReport;
//    @Basic
//    @Column(name = "id_victim")
//    private int idVictim;
//    @Basic
//    @Column(name = "id_crime_type")
//    private int idCrimeType;
//    @Basic
//    @Column(name = "id_report")
//    private Integer idReport;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_victim", referencedColumnName = "id_person", nullable = false)
    private PersonEntity personByIdVictim;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_crime_type", referencedColumnName = "ID_CrimeType", nullable = false)
    private CriminalChargesEntity criminalChargesByIdCrimeType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_report", referencedColumnName = "id_report")
    private ReportEntity reportByIdReport;
    @OneToMany(mappedBy = "crimeReportByIdCrimeReport", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<CrimeWitnessesEntity> crimeWitnessesByIdCrimeReport;

    public int getIdCrimeReport() {
        return idCrimeReport;
    }

    public void setIdCrimeReport(int idCrimeReport) {
        this.idCrimeReport = idCrimeReport;
    }

//    public int getIdVictim() {
//        return idVictim;
//    }
//
//    public void setIdVictim(int idVictim) {
//        this.idVictim = idVictim;
//    }

//    public int getIdCrimeType() {
//        return idCrimeType;
//    }
//
//    public void setIdCrimeType(int idCrimeType) {
//        this.idCrimeType = idCrimeType;
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

        CrimeReportEntity that = (CrimeReportEntity) o;

        if (idCrimeReport != that.idCrimeReport) return false;
//        if (idVictim != that.idVictim) return false;
        //if (idCrimeType != that.idCrimeType) return false;
        //if (idReport != null ? !idReport.equals(that.idReport) : that.idReport != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCrimeReport;
//        result = 31 * result + idVictim;
        //result = 31 * result + idCrimeType;
       // result = 31 * result + (idReport != null ? idReport.hashCode() : 0);
        return result;
    }

    public PersonEntity getPersonByIdVictim() {
        return personByIdVictim;
    }

    public void setPersonByIdVictim(PersonEntity personByIdVictim) {
        this.personByIdVictim = personByIdVictim;
    }

    public CriminalChargesEntity getCriminalChargesByIdCrimeType() {
        return criminalChargesByIdCrimeType;
    }

    public void setCriminalChargesByIdCrimeType(CriminalChargesEntity criminalChargesByIdCrimeType) {
        this.criminalChargesByIdCrimeType = criminalChargesByIdCrimeType;
    }

    public ReportEntity getReportByIdReport() {
        return reportByIdReport;
    }

    public void setReportByIdReport(ReportEntity reportByIdReport) {
        this.reportByIdReport = reportByIdReport;
    }

    public Collection<CrimeWitnessesEntity> getCrimeWitnessesByIdCrimeReport() {
        return crimeWitnessesByIdCrimeReport;
    }

    public void setCrimeWitnessesByIdCrimeReport(Collection<CrimeWitnessesEntity> crimeWitnessesByIdCrimeReport) {
        this.crimeWitnessesByIdCrimeReport = crimeWitnessesByIdCrimeReport;
    }
}
