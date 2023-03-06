package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "accident_vehicles", schema = "crimeManagament-TOproject")
public class AccidentVehiclesEntity {
//    @Basic
//    @Column(name = "id_vehicle")
//    private int idVehicle;
//    @Basic
//    @Column(name = "id_accident_report")
//    private int idAccidentReport;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_vehicle", referencedColumnName = "id_vehicle", nullable = false)
    private VehicleEntity vehicleByIdVehicle;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_accident_report", referencedColumnName = "id_accident_report", nullable = false)
    private AccidentReportEntity accidentReportByIdAccidentReport;

//    public int getIdVehicle() {
//        return idVehicle;
//    }
//
//    public void setIdVehicle(int idVehicle) {
//        this.idVehicle = idVehicle;
//    }
//
//    public int getIdAccidentReport() {
//        return idAccidentReport;
//    }
//
//    public void setIdAccidentReport(int idAccidentReport) {
//        this.idAccidentReport = idAccidentReport;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccidentVehiclesEntity that = (AccidentVehiclesEntity) o;

//        if (idVehicle != that.idVehicle) return false;
//        if (idAccidentReport != that.idAccidentReport) return false;
        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        //result = 31 * result + idAccidentReport;
        result = 31 * result + id;
        return result;
    }

    public VehicleEntity getVehicleByIdVehicle() {
        return vehicleByIdVehicle;
    }

    public void setVehicleByIdVehicle(VehicleEntity vehicleByIdVehicle) {
        this.vehicleByIdVehicle = vehicleByIdVehicle;
    }

    public AccidentReportEntity getAccidentReportByIdAccidentReport() {
        return accidentReportByIdAccidentReport;
    }

    public void setAccidentReportByIdAccidentReport(AccidentReportEntity accidentReportByIdAccidentReport) {
        this.accidentReportByIdAccidentReport = accidentReportByIdAccidentReport;
    }
}
