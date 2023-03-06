package entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "officer", schema = "crimeManagament-TOproject")
@NamedQuery(name = "getOfficer", query = "Select o from OfficerEntity o where o.badgeNumber = ?1 and o.username = ?2 and o.password = ?3")
public class OfficerEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_officer")
    private int idOfficer;
    @Basic
    @Column(name = "badge_number")
    private int badgeNumber;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "officerByIdOfficer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<ReportEntity> reportsByIdOfficer;

    public int getIdOfficer() {
        return idOfficer;
    }

    public void setIdOfficer(int idOfficer) {
        this.idOfficer = idOfficer;
    }

    public int getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(int badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OfficerEntity that = (OfficerEntity) o;

        if (idOfficer != that.idOfficer) return false;
        if (badgeNumber != that.badgeNumber) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOfficer;
        result = 31 * result + badgeNumber;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    public Collection<ReportEntity> getReportsByIdOfficer() {
        return reportsByIdOfficer;
    }

    public void setReportsByIdOfficer(Collection<ReportEntity> reportsByIdOfficer) {
        this.reportsByIdOfficer = reportsByIdOfficer;
    }
}
