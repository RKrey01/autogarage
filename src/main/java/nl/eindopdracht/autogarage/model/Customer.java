package nl.eindopdracht.autogarage.model;

import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    @Column(name = "CustomerId")
    private Long id;

    @OneToMany(mappedBy = "customer")
    @Column(name = "Car")
    @NotBlank(message = "Veld is leeg!")
    @ToString.Exclude
    private List<Car> customerCars = new ArrayList<>();

    @Column(name = "FirstName")
    @NotBlank(message = "Veld is leeg!")
    private String firstName;

    @Column(name = "LastName")
    @NotBlank(message = "Veld is leeg!")
    private String lastName;

    @Column(name = "Address")
    @NotBlank(message = "Veld is leeg!")
    private String address;

    @Column(name = "Zipcode")
    @NotBlank(message = "Veld is leeg!")
    private String zipcode;

    @Column(name = "Email", unique = true) // niet de zelfde email registreren.
    @NotBlank(message = "Veld is leeg!")
    @Email
    private String email;

    @Column(name = "DateOfBirth")
    @NotBlank(message = "Veld is leeg!")
    private LocalDate dob;

    @Column(name = "PhoneNumber")
    @NotBlank(message = "Veld is leeg!")
    private Integer phoneNumber;


    // bean
    public Customer() {
    }

    // constructor with id
    public Customer(Long id, String firstName, String lastName, String address,
                    String zipcode, String email, LocalDate dob, Integer phoneNumber, List<Car> customerCars) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.zipcode = zipcode;
        this.email = email;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.customerCars = customerCars;
    }

    // constructor without id, db generates id
    public Customer(String firstName, String lastName, String address, String zipcode, String email, LocalDate dob, Integer phoneNumber, List<Car> customerCars) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.zipcode = zipcode;
        this.email = email;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.customerCars = customerCars;
    }

    //voor test!
    public Customer(String firstName, String lastName, String address,
                    String zipcode, String email, LocalDate dob, Integer phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.zipcode = zipcode;
        this.email = email;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Car> getCustomerCars() {
        return customerCars;
    }

    public void setCustomerCars(List<Car> customerCars) {
        this.customerCars = customerCars;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + firstName + " " + lastName +  '\'' +
                ", address='" + address + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", car=" + customerCars +
                '}';
    }
}
