package entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "state", schema = "crimeManagament-TOproject")
@NamedQuery(name = "getState", query = "Select p from StateEntity p where p.state = ?1 and p.countryByIdCountry.country = ?2")
public class StateEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_state")
    private int idState;
//    @Basic
//    @Column(name = "id_country")
//    private int idCountry;
    @Basic
    @Column(name = "state")
    private String state;
    @OneToMany(mappedBy = "stateByIdState", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<AddressEntity> addressesByIdState;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_country", referencedColumnName = "id_country", nullable = false)
    private CountryEntity countryByIdCountry;

    public int getIdState() {
        return idState;
    }

    public void setIdState(int idState) {
        this.idState = idState;
    }

//    public int getIdCountry() {
//        return idCountry;
//    }
//
//    public void setIdCountry(int idCountry) {
//        this.idCountry = idCountry;
//    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StateEntity that = (StateEntity) o;

        if (idState != that.idState) return false;
//        if (idCountry != that.idCountry) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idState;
//        result = 31 * result + idCountry;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    public Collection<AddressEntity> getAddressesByIdState() {
        return addressesByIdState;
    }

    public void setAddressesByIdState(Collection<AddressEntity> addressesByIdState) {
        this.addressesByIdState = addressesByIdState;
    }

    public CountryEntity getCountryByIdCountry() {
        return countryByIdCountry;
    }

    public void setCountryByIdCountry(CountryEntity countryByIdCountry) {
        this.countryByIdCountry = countryByIdCountry;
    }
}
