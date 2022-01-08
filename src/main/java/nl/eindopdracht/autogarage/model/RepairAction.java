package nl.eindopdracht.autogarage.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "repairaction")
public class RepairAction {

    @Id
    @SequenceGenerator(
            name = "repair_action_sequence",
            sequenceName = "repair_action_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "repair_action_sequence"
    )
    @Column(name = "RepairActionId")
    private Long id;

    @ManyToMany(mappedBy = "repairAction")
    private Set<Repair> repairs = new HashSet<>();

    @Column(name = "Name")
    @NotBlank(message = "Veld is leeg!")
    private String name;

    @Column(name = "Price")
    @NotBlank(message = "Veld is leeg!")
    private Double price;

    public RepairAction() {
    }

    public RepairAction(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public RepairAction(String name, Double price) {
        this.name = name;
        this.price = price;
    }

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

    public Set<Repair> getRepairs() {
        return repairs;
    }

    @Override
    public String toString() {
        return "RepairAction{" +
                "id=" + id +
                ", name='" + name +  '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
