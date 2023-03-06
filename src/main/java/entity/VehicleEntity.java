package entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "vehicle", schema = "crimeManagament-TOproject")
public class VehicleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_vehicle")
    private int idVehicle;
//    @Basic
//    @Column(name = "id_owner")
//    private Integer idOwner;
    @Basic
    @Column(name = "plates_number")
    private String platesNumber;
    @Basic
    @Column(name = "colour")
    private String colour;
    @Basic
    @Column(name = "type")
    private String type;
    @Basic
    @Column(name = "brand")
    private String brand;
    @Basic
    @Column(name = "model")
    private String model;
    @OneToMany(mappedBy = "vehicleByIdVehicle", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<AccidentVehiclesEntity> accidentVehiclesByIdVehicle;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_owner", referencedColumnName = "id_person")
    private PersonEntity personByIdOwner;

    public int getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(int idVehicle) {
        this.idVehicle = idVehicle;
    }

//    public Integer getIdOwner() {
//        return idOwner;
//    }
//
//    public void setIdOwner(Integer idOwner) {
//        this.idOwner = idOwner;
//    }

    public String getPlatesNumber() {
        return platesNumber;
    }

    public void setPlatesNumber(String platesNumber) {
        this.platesNumber = platesNumber;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VehicleEntity that = (VehicleEntity) o;

        if (idVehicle != that.idVehicle) return false;
        //if (idOwner != null ? !idOwner.equals(that.idOwner) : that.idOwner != null) return false;
        if (platesNumber != null ? !platesNumber.equals(that.platesNumber) : that.platesNumber != null) return false;
        if (colour != null ? !colour.equals(that.colour) : that.colour != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (brand != null ? !brand.equals(that.brand) : that.brand != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idVehicle;
//        result = 31 * result + (idOwner != null ? idOwner.hashCode() : 0);
        result = 31 * result + (platesNumber != null ? platesNumber.hashCode() : 0);
        result = 31 * result + (colour != null ? colour.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        return result;
    }

    public Collection<AccidentVehiclesEntity> getAccidentVehiclesByIdVehicle() {
        return accidentVehiclesByIdVehicle;
    }

    public void setAccidentVehiclesByIdVehicle(Collection<AccidentVehiclesEntity> accidentVehiclesByIdVehicle) {
        this.accidentVehiclesByIdVehicle = accidentVehiclesByIdVehicle;
    }

    public PersonEntity getPersonByIdOwner() {
        return personByIdOwner;
    }

    public void setPersonByIdOwner(PersonEntity personByIdOwner) {
        this.personByIdOwner = personByIdOwner;
    }
}
