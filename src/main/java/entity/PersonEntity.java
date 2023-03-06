package entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "person", schema = "crimeManagament-TOproject")
@NamedQuery(name = "personBySSN", query = "SELECT p from PersonEntity p where p.socialSecurityNumber = ?1")
public class PersonEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_person")
    private int idPerson;
//    @Basic
//    @Column(name = "id_address")
//    private Integer idAddress;
    @Basic
    @Column(name = "first_name")
    private String firstName;
    @Basic
    @Column(name = "middle_name")
    private String middleName;
    @Basic
    @Column(name = "last_name")
    private String lastName;
    @Basic
    @Column(name = "social_security_number")
    private String socialSecurityNumber;
    @Basic
    @Column(name = "phone")
    private String phone;
    @OneToMany(mappedBy = "personByIdPerson", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<ArrestedPersonEntity> arrestedPeopleByIdPerson;
    @OneToMany(mappedBy = "personByIdVictim", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<CrimeReportEntity> crimeReportsByIdPerson;
    @OneToMany(mappedBy = "personByIdWitness", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<CrimeWitnessesEntity> crimeWitnessesByIdPerson;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_address", referencedColumnName = "id_address")
    private AddressEntity addressByIdAddress;
    @OneToMany(mappedBy = "personByIdOwner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<VehicleEntity> vehiclesByIdPerson;

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

//    public Integer getIdAddress() {
//        return idAddress;
//    }
//
//    public void setIdAddress(Integer idAddress) {
//        this.idAddress = idAddress;
//    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonEntity that = (PersonEntity) o;

        if (idPerson != that.idPerson) return false;
        //if (idAddress != null ? !idAddress.equals(that.idAddress) : that.idAddress != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (middleName != null ? !middleName.equals(that.middleName) : that.middleName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (socialSecurityNumber != null ? !socialSecurityNumber.equals(that.socialSecurityNumber) : that.socialSecurityNumber != null)
            return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPerson;
        //result = 31 * result + (idAddress != null ? idAddress.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (socialSecurityNumber != null ? socialSecurityNumber.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }

    public Collection<ArrestedPersonEntity> getArrestedPeopleByIdPerson() {
        return arrestedPeopleByIdPerson;
    }

    public void setArrestedPeopleByIdPerson(Collection<ArrestedPersonEntity> arrestedPeopleByIdPerson) {
        this.arrestedPeopleByIdPerson = arrestedPeopleByIdPerson;
    }

    public Collection<CrimeReportEntity> getCrimeReportsByIdPerson() {
        return crimeReportsByIdPerson;
    }

    public void setCrimeReportsByIdPerson(Collection<CrimeReportEntity> crimeReportsByIdPerson) {
        this.crimeReportsByIdPerson = crimeReportsByIdPerson;
    }

    public Collection<CrimeWitnessesEntity> getCrimeWitnessesByIdPerson() {
        return crimeWitnessesByIdPerson;
    }

    public void setCrimeWitnessesByIdPerson(Collection<CrimeWitnessesEntity> crimeWitnessesByIdPerson) {
        this.crimeWitnessesByIdPerson = crimeWitnessesByIdPerson;
    }

    public AddressEntity getAddressByIdAddress() {
        return addressByIdAddress;
    }

    public void setAddressByIdAddress(AddressEntity addressByIdAddress) {
        this.addressByIdAddress = addressByIdAddress;
    }

    public Collection<VehicleEntity> getVehiclesByIdPerson() {
        return vehiclesByIdPerson;
    }

    public void setVehiclesByIdPerson(Collection<VehicleEntity> vehiclesByIdPerson) {
        this.vehiclesByIdPerson = vehiclesByIdPerson;
    }
}
