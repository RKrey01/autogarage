package nl.eindopdracht.autogarage.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "inspection")
public class Inspection {

    @Id
    @SequenceGenerator(
            name = "inspection_sequence",
            sequenceName = "inspection_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "inspection_sequence"
    )
    @Column(name = "InspectionId")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "CarId")
    @NotBlank(message = "Veld is leeg!")
    private Car car;

    @Column(name = "Date")
    @NotBlank(message = "Veld is leeg!")
    private LocalDate date;

    @Column(name = "Shortcoming")
    @NotBlank(message = "Veld is leeg!")
    private String shortcoming;

    // bean
    public Inspection() {
    }

    // constructor with id
    public Inspection(Long id, Car car, LocalDate date, String shortcoming) {
        this.id = id;
        this.car = car;
        this.date = date;
        this.shortcoming = shortcoming;
    }

    // constructor without id, db generates id
    public Inspection(Car car, LocalDate date, String shortcoming) {
        this.car = car;
        this.date = date;
        this.shortcoming = shortcoming;
    }

    //Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getShortcoming() {
        return shortcoming;
    }

    public void setShortcoming(String shortcoming) {
        this.shortcoming = shortcoming;
    }

    @Override
    public String toString() {
        return "Inspection{" +
                "id=" + id +
                "car=" + car.getLicensePlate() +
                ", date='" + date +  '\'' +
                ", shortcoming='" + shortcoming + '\'' +
                '}';
    }
}
