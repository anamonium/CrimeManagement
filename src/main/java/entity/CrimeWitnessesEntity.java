package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "crime_witnesses", schema = "crimeManagament-TOproject")
public class CrimeWitnessesEntity {
//    @Basic
//    @Column(name = "id_crime_report")
//    private int idCrimeReport;
//    @Basic
//    @Column(name = "id_witness")
//    private int idWitness;
    @Basic
    @Column(name = "testimony")
    private String testimony;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_crime_report", referencedColumnName = "id_crime_report", nullable = false)
    private CrimeReportEntity crimeReportByIdCrimeReport;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_witness", referencedColumnName = "id_person", nullable = false)
    private PersonEntity personByIdWitness;

//    public int getIdCrimeReport() {
//        return idCrimeReport;
//    }
//
//    public void setIdCrimeReport(int idCrimeReport) {
//        this.idCrimeReport = idCrimeReport;
//    }
//
//    public int getIdWitness() {
//        return idWitness;
//    }
//
//    public void setIdWitness(int idWitness) {
//        this.idWitness = idWitness;
//    }

    public String getTestimony() {
        return testimony;
    }

    public void setTestimony(String testimony) {
        this.testimony = testimony;
    }

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

        CrimeWitnessesEntity that = (CrimeWitnessesEntity) o;

//        if (idCrimeReport != that.idCrimeReport) return false;
//        if (idWitness != that.idWitness) return false;
        if (id != that.id) return false;
        if (testimony != null ? !testimony.equals(that.testimony) : that.testimony != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + 0;
        result = 31 * result + (testimony != null ? testimony.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }

    public CrimeReportEntity getCrimeReportByIdCrimeReport() {
        return crimeReportByIdCrimeReport;
    }

    public void setCrimeReportByIdCrimeReport(CrimeReportEntity crimeReportByIdCrimeReport) {
        this.crimeReportByIdCrimeReport = crimeReportByIdCrimeReport;
    }

    public PersonEntity getPersonByIdWitness() {
        return personByIdWitness;
    }

    public void setPersonByIdWitness(PersonEntity personByIdWitness) {
        this.personByIdWitness = personByIdWitness;
    }
}
