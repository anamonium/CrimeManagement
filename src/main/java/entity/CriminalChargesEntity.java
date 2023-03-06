package entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "CriminalCharges", schema = "crimeManagament-TOproject")
@NamedQuery(name = "charges", query = "SELECT c.crimeType from CriminalChargesEntity c")
@NamedQuery(name = "charg", query = "select c from CriminalChargesEntity c where c.crimeType = ?1")
public class CriminalChargesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_CrimeType")
    private int idCrimeType;
    @Basic
    @Column(name = "CrimeType")
    private String crimeType;
    @OneToMany(mappedBy = "criminalChargesByIdCriminalCharges", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<ArrestReportEntity> arrestReportsByIdCrimeType;
    @OneToMany(mappedBy = "criminalChargesByIdCrimeType", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<CrimeReportEntity> crimeReportsByIdCrimeType;

    public int getIdCrimeType() {
        return idCrimeType;
    }

    public void setIdCrimeType(int idCrimeType) {
        this.idCrimeType = idCrimeType;
    }

    public String getCrimeType() {
        return crimeType;
    }

    public void setCrimeType(String crimeType) {
        this.crimeType = crimeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CriminalChargesEntity that = (CriminalChargesEntity) o;

        if (idCrimeType != that.idCrimeType) return false;
        if (crimeType != null ? !crimeType.equals(that.crimeType) : that.crimeType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCrimeType;
        result = 31 * result + (crimeType != null ? crimeType.hashCode() : 0);
        return result;
    }

    public Collection<ArrestReportEntity> getArrestReportsByIdCrimeType() {
        return arrestReportsByIdCrimeType;
    }

    public void setArrestReportsByIdCrimeType(Collection<ArrestReportEntity> arrestReportsByIdCrimeType) {
        this.arrestReportsByIdCrimeType = arrestReportsByIdCrimeType;
    }

    public Collection<CrimeReportEntity> getCrimeReportsByIdCrimeType() {
        return crimeReportsByIdCrimeType;
    }

    public void setCrimeReportsByIdCrimeType(Collection<CrimeReportEntity> crimeReportsByIdCrimeType) {
        this.crimeReportsByIdCrimeType = crimeReportsByIdCrimeType;
    }
}
