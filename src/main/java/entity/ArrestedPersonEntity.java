package entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Table(name = "arrested_person", schema = "crimeManagament-TOproject")
@NamedQuery(name = "arrestedPersonBySSN", query = "SELECT p from ArrestedPersonEntity p where p.personByIdPerson.socialSecurityNumber = ?1")
public class ArrestedPersonEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_arrested_person")
    private int idArrestedPerson;
    @Basic
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Basic
    @Column(name = "place_of_birth")
    private String placeOfBirth;
    @Basic
    @Column(name = "height")
    private Double height;
    @Basic
    @Column(name = "weight")
    private Double weight;
    @Basic
    @Column(name = "photo")
    private byte[] photo;
    @Basic
    @Column(name = "race")
    private String race;
    @Basic
    @Column(name = "sex")
    private String sex;
    @Basic
    @Column(name = "hair")
    private String hair;
    @Basic
    @Column(name = "eyes")
    private String eyes;
//    @Basic
//    @Column(name = "id_person")
//    private Integer idPerson;
    @OneToMany(mappedBy = "arrestedPersonByIdArrestedPerson", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<ArrestReportEntity> arrestReportsByIdArrestedPerson;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_person", referencedColumnName = "id_person")
    private PersonEntity personByIdPerson;

    public int getIdArrestedPerson() {
        return idArrestedPerson;
    }

    public void setIdArrestedPerson(int idArrestedPerson) {
        this.idArrestedPerson = idArrestedPerson;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHair() {
        return hair;
    }

    public void setHair(String hair) {
        this.hair = hair;
    }

    public String getEyes() {
        return eyes;
    }

    public void setEyes(String eyes) {
        this.eyes = eyes;
    }

//    public Integer getIdPerson() {
//        return idPerson;
//    }
//
//    public void setIdPerson(Integer idPerson) {
//        this.idPerson = idPerson;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrestedPersonEntity that = (ArrestedPersonEntity) o;

        if (idArrestedPerson != that.idArrestedPerson) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(that.dateOfBirth) : that.dateOfBirth != null) return false;
        if (placeOfBirth != null ? !placeOfBirth.equals(that.placeOfBirth) : that.placeOfBirth != null) return false;
        if (height != null ? !height.equals(that.height) : that.height != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
        if (!Arrays.equals(photo, that.photo)) return false;
        if (race != null ? !race.equals(that.race) : that.race != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (hair != null ? !hair.equals(that.hair) : that.hair != null) return false;
        if (eyes != null ? !eyes.equals(that.eyes) : that.eyes != null) return false;
        //if (idPerson != null ? !idPerson.equals(that.idPerson) : that.idPerson != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idArrestedPerson;
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (placeOfBirth != null ? placeOfBirth.hashCode() : 0);
        result = 31 * result + (height != null ? height.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(photo);
        result = 31 * result + (race != null ? race.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (hair != null ? hair.hashCode() : 0);
        result = 31 * result + (eyes != null ? eyes.hashCode() : 0);
       // result = 31 * result + (idPerson != null ? idPerson.hashCode() : 0);
        return result;
    }

    public Collection<ArrestReportEntity> getArrestReportsByIdArrestedPerson() {
        return arrestReportsByIdArrestedPerson;
    }

    public void setArrestReportsByIdArrestedPerson(Collection<ArrestReportEntity> arrestReportsByIdArrestedPerson) {
        this.arrestReportsByIdArrestedPerson = arrestReportsByIdArrestedPerson;
    }

    public PersonEntity getPersonByIdPerson() {
        return personByIdPerson;
    }

    public void setPersonByIdPerson(PersonEntity personByIdPerson) {
        this.personByIdPerson = personByIdPerson;
    }
}
