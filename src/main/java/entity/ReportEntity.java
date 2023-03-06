package entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "report", schema = "crimeManagament-TOproject")
public class ReportEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_report")
    private int idReport;
    @Basic
    @Column(name = "date")
    private LocalDate date;
    @Basic
    @Column(name = "description")
    private String description;
//    @Basic
//    @Column(name = "id_location")
//    private int idLocation;
//    @Basic
//    @Column(name = "id_officer")
//    private Integer idOfficer;
    @OneToMany(mappedBy = "reportByIdReport", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<AccidentReportEntity> accidentReportsByIdReport;
    @OneToMany(mappedBy = "reportByIdReport", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<ArrestReportEntity> arrestReportsByIdReport;
    @OneToMany(mappedBy = "reportByIdReport", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<CrimeReportEntity> crimeReportsByIdReport;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_location", referencedColumnName = "id_address", nullable = false)
    private AddressEntity addressByIdLocation;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_officer", referencedColumnName = "id_officer")
    private OfficerEntity officerByIdOfficer;

    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public int getIdLocation() {
//        return idLocation;
//    }
//
//    public void setIdLocation(int idLocation) {
//        this.idLocation = idLocation;
//    }
//
//    public Integer getIdOfficer() {
//        return idOfficer;
//    }
//
//    public void setIdOfficer(Integer idOfficer) {
//        this.idOfficer = idOfficer;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReportEntity that = (ReportEntity) o;

        if (idReport != that.idReport) return false;
        //if (idLocation != that.idLocation) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
//        if (idOfficer != null ? !idOfficer.equals(that.idOfficer) : that.idOfficer != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idReport;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
//        result = 31 * result + idLocation;
//        result = 31 * result + (idOfficer != null ? idOfficer.hashCode() : 0);
        return result;
    }

    public Collection<AccidentReportEntity> getAccidentReportsByIdReport() {
        return accidentReportsByIdReport;
    }

    public void setAccidentReportsByIdReport(Collection<AccidentReportEntity> accidentReportsByIdReport) {
        this.accidentReportsByIdReport = accidentReportsByIdReport;
    }

    public Collection<ArrestReportEntity> getArrestReportsByIdReport() {
        return arrestReportsByIdReport;
    }

    public void setArrestReportsByIdReport(Collection<ArrestReportEntity> arrestReportsByIdReport) {
        this.arrestReportsByIdReport = arrestReportsByIdReport;
    }

    public Collection<CrimeReportEntity> getCrimeReportsByIdReport() {
        return crimeReportsByIdReport;
    }

    public void setCrimeReportsByIdReport(Collection<CrimeReportEntity> crimeReportsByIdReport) {
        this.crimeReportsByIdReport = crimeReportsByIdReport;
    }

    public AddressEntity getAddressByIdLocation() {
        return addressByIdLocation;
    }

    public void setAddressByIdLocation(AddressEntity addressByIdLocation) {
        this.addressByIdLocation = addressByIdLocation;
    }

    public OfficerEntity getOfficerByIdOfficer() {
        return officerByIdOfficer;
    }

    public void setOfficerByIdOfficer(OfficerEntity officerByIdOfficer) {
        this.officerByIdOfficer = officerByIdOfficer;
    }
}
