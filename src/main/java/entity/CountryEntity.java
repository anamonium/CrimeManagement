package entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "country", schema = "crimeManagament-TOproject")
@NamedQuery(name = "getCountry", query = "SELECT p from CountryEntity p where p.country = ?1")
public class CountryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_country")
    private int idCountry;
    @Basic
    @Column(name = "country")
    private String country;
    @OneToMany(mappedBy = "countryByIdCountry", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<StateEntity> statesByIdCountry;

    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountryEntity country1 = (CountryEntity) o;

        if (idCountry != country1.idCountry) return false;
        if (country != null ? !country.equals(country1.country) : country1.country != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCountry;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    public Collection<StateEntity> getStatesByIdCountry() {
        return statesByIdCountry;
    }

    public void setStatesByIdCountry(Collection<StateEntity> statesByIdCountry) {
        this.statesByIdCountry = statesByIdCountry;
    }
}
