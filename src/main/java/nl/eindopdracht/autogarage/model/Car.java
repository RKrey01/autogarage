package nl.eindopdracht.autogarage.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @SequenceGenerator(
            name = "car_sequence",
            sequenceName = "car_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "car_sequence"
    )

    @Column(name = "CarId")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "CustomerId")
    private Customer customer;

    @OneToMany(mappedBy = "car")
    private Set<Inspection> carInspections = new HashSet<>();

    @Column(name = "LicensePlate", unique = true)
    @NotBlank(message = "Kenteken niet ingevoerd!")
    private String licensePlate;

    @Column(name = "Brand")
    @NotBlank(message = "Merk niet ingevoerd!")
    private String brand;

    @Column(name = "Type")
    @NotBlank(message = "Type niet ingevoerd!")
    private String type;

    @Column(name = "ConstructionYear")
    @NotBlank(message = "Bouwjaar niet ingevoerd!")
    private Integer constructionYear;



    // bean
    public Car() {
    }

    // constructor with id
    public Car(Long id, String licensePlate, String brand, String type, Integer constructionYear) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.type = type;
        this.constructionYear = constructionYear;
    }

    // constructor without id, db generates id
    public Car(String licensePlate, String brand, String type, Integer constructionYear) {
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.type = type;
        this.constructionYear = constructionYear;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(Integer constructionYear) {
        this.constructionYear = constructionYear;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Set<Inspection> getCarInspections() {
        return carInspections;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", licensePlate='" + licensePlate + '\'' +
                ", brand='" + brand + '\'' +
                ", type=" + type + '\'' +
                ", constructionYear=" + constructionYear +
                '}';
    }
}
