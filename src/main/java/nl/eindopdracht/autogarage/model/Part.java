package nl.eindopdracht.autogarage.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "part")
public class Part {

    @Id
    @SequenceGenerator(
            name = "part_sequence",
            sequenceName = "part_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "part_sequence"
    )
    @Column(name = "PartId")
    private Long id;

    @ManyToMany(mappedBy = "usedPart")
    private Set<Repair> repairs = new HashSet<>();

    @Column(name = "Name")
    @NotBlank(message = "Veld is leeg!")
    private String name;

    @Column(name = "Price")
    @NotBlank(message = "Veld is leeg!")
    private Double price;

    @Column(name = "Stock")
    @NotBlank(message = "Veld is leeg!")
    private Integer stock;


    // bean
    public Part() {
    }

    // constructor with id
    public Part(Long id, String name, Double price, Integer stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // constructor without id, db generates id
    public Part(String name, Double price, Integer stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Set<Repair> getRepairs() {
        return repairs;
    }

    @Override
    public String toString() {
        return "Part{" +
                "id=" + id +
                ", name='" + name +  '\'' +
                ", price='" + price + '\'' +
                ", stock='" + stock + '\'' +
                '}';
    }
}
