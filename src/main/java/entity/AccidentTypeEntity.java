package entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "accident_type", schema = "crimeManagament-TOproject")
public class AccidentTypeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_accident_type")
    private int idAccidentType;
    @Basic
    @Column(name = "accident_type")
    private String accidentType;
    @OneToMany(mappedBy = "accidentTypeByIdAccidentType", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<AccidentReportEntity> accidentReportsByIdAccidentType;

    public int getIdAccidentType() {
        return idAccidentType;
    }

    public void setIdAccidentType(int idAccidentType) {
        this.idAccidentType = idAccidentType;
    }

    public String getAccidentType() {
        return accidentType;
    }

    public void setAccidentType(String accidentType) {
        this.accidentType = accidentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccidentTypeEntity that = (AccidentTypeEntity) o;

        if (idAccidentType != that.idAccidentType) return false;
        if (accidentType != null ? !accidentType.equals(that.accidentType) : that.accidentType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAccidentType;
        result = 31 * result + (accidentType != null ? accidentType.hashCode() : 0);
        return result;
    }

    public Collection<AccidentReportEntity> getAccidentReportsByIdAccidentType() {
        return accidentReportsByIdAccidentType;
    }

    public void setAccidentReportsByIdAccidentType(Collection<AccidentReportEntity> accidentReportsByIdAccidentType) {
        this.accidentReportsByIdAccidentType = accidentReportsByIdAccidentType;
    }
}
