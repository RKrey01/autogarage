package nl.eindopdracht.autogarage.model;

import nl.eindopdracht.autogarage.Enumeration.InspectionStatus;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Enumeration;

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

    @Column(name = "Car")
    @NotBlank(message = "Veld is leeg!")
    private Car car;

    @Column(name = "Status")
    @NotBlank(message = "Veld is leeg!")
    private Enumeration<InspectionStatus> status;

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
    public Inspection(Long id, Car car, Enumeration<InspectionStatus> status, LocalDate date, String shortcoming) {
        this.id = id;
        this.car = car;
        this.status = status;
        this.date = date;
        this.shortcoming = shortcoming;
    }

    // constructor without id, db generates id
    public Inspection(Car car, Enumeration<InspectionStatus> status, LocalDate date, String shortcoming) {
        this.car = car;
        this.status = status;
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

    public Enumeration<InspectionStatus> getStatus() {
        return status;
    }

    public void setStatus(Enumeration<InspectionStatus> status) {
        this.status = status;
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
                ", status='" + status + '\'' +
                ", date='" + date +  '\'' +
                ", shortcoming='" + shortcoming + '\'' +
                '}';
    }
}
