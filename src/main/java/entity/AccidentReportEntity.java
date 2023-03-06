package entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "accident_report", schema = "crimeManagament-TOproject")
public class AccidentReportEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_accident_report")
    private int idAccidentReport;
//    @Basic
//    @Column(name = "id_accident_type")
//    private int idAccidentType;
//    @Basic
//    @Column(name = "id_report")
//    private Integer idReport;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_accident_type", referencedColumnName = "id_accident_type", nullable = false)
    private AccidentTypeEntity accidentTypeByIdAccidentType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_report", referencedColumnName = "id_report")
    private ReportEntity reportByIdReport;
    @OneToMany(mappedBy = "accidentReportByIdAccidentReport", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<AccidentVehiclesEntity> accidentVehiclesByIdAccidentReport;

    public int getIdAccidentReport() {
        return idAccidentReport;
    }

    public void setIdAccidentReport(int idAccidentReport) {
        this.idAccidentReport = idAccidentReport;
    }

//    public int getIdAccidentType() {
//        return idAccidentType;
//    }
//
//    public void setIdAccidentType(int idAccidentType) {
//        this.idAccidentType = idAccidentType;
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

        AccidentReportEntity that = (AccidentReportEntity) o;

        if (idAccidentReport != that.idAccidentReport) return false;
        //if (idAccidentType != that.idAccidentType) return false;
        //if (idReport != null ? !idReport.equals(that.idReport) : that.idReport != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAccidentReport;
       // result = 31 * result + idAccidentType;
        //result = 31 * result + (idReport != null ? idReport.hashCode() : 0);
        return result;
    }

    public AccidentTypeEntity getAccidentTypeByIdAccidentType() {
        return accidentTypeByIdAccidentType;
    }

    public void setAccidentTypeByIdAccidentType(AccidentTypeEntity accidentTypeByIdAccidentType) {
        this.accidentTypeByIdAccidentType = accidentTypeByIdAccidentType;
    }

    public ReportEntity getReportByIdReport() {
        return reportByIdReport;
    }

    public void setReportByIdReport(ReportEntity reportByIdReport) {
        this.reportByIdReport = reportByIdReport;
    }

    public Collection<AccidentVehiclesEntity> getAccidentVehiclesByIdAccidentReport() {
        return accidentVehiclesByIdAccidentReport;
    }

    public void setAccidentVehiclesByIdAccidentReport(Collection<AccidentVehiclesEntity> accidentVehiclesByIdAccidentReport) {
        this.accidentVehiclesByIdAccidentReport = accidentVehiclesByIdAccidentReport;
    }
}
