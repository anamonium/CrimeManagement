package entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "address", schema = "crimeManagament-TOproject")
@NamedQuery(name = "getAddress", query = "SELECT a from AddressEntity a where a.street = ?1 and a.buildingNumber = ?2 and a.postalCode = ?3 and a.city = ?4 and a.stateByIdState.state = ?5 and a.stateByIdState.countryByIdCountry.country = ?6")
public class AddressEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_address")
    private int idAddress;
//    @Basic
//    @Column(name = "id_state"
//    private int idState;
//    @Basic
    @Column(name = "street")
    private String street;
    @Basic
    @Column(name = "building_number")
    private Integer buildingNumber;
    @Basic
    @Column(name = "postal_code")
    private String postalCode;
    @Basic
    @Column(name = "city")
    private String city;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_state", referencedColumnName = "id_state", nullable = false)
    private StateEntity stateByIdState;
    @OneToMany(mappedBy = "addressByIdAddress", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<PersonEntity> peopleByIdAddress;
    @OneToMany(mappedBy = "addressByIdLocation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<ReportEntity> reportsByIdAddress;

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

//    public int getIdState() {
//        return idState;
//    }
//
//    public void setIdState(int idState) {
//        this.idState = idState;
//    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(Integer buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressEntity that = (AddressEntity) o;

        if (idAddress != that.idAddress) return false;
        //if (idState != that.idState) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (buildingNumber != null ? !buildingNumber.equals(that.buildingNumber) : that.buildingNumber != null)
            return false;
        if (postalCode != null ? !postalCode.equals(that.postalCode) : that.postalCode != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAddress;
       // result = 31 * result + idState;
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (buildingNumber != null ? buildingNumber.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    public StateEntity getStateByIdState() {
        return stateByIdState;
    }

    public void setStateByIdState(StateEntity stateByIdState) {
        this.stateByIdState = stateByIdState;
    }

    public Collection<PersonEntity> getPeopleByIdAddress() {
        return peopleByIdAddress;
    }

    public void setPeopleByIdAddress(Collection<PersonEntity> peopleByIdAddress) {
        this.peopleByIdAddress = peopleByIdAddress;
    }

    public Collection<ReportEntity> getReportsByIdAddress() {
        return reportsByIdAddress;
    }

    public void setReportsByIdAddress(Collection<ReportEntity> reportsByIdAddress) {
        this.reportsByIdAddress = reportsByIdAddress;
    }
}
