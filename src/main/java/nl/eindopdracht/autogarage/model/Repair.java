package nl.eindopdracht.autogarage.model;

import nl.eindopdracht.autogarage.Enumeration.RepairStatus;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Table(name = "repair")
public class Repair {

    @Id
    @SequenceGenerator(
            name = "repair_sequence",
            sequenceName = "repair_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "repair_sequence"
    )
    @Column(name = "RepairId")
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "parts_used",
            joinColumns = @JoinColumn(name = "repair_id"),
            inverseJoinColumns = @JoinColumn(name = "part_id")
    )
    private List<Part> usedPart = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "repairActions_performed",
            joinColumns = @JoinColumn(name = "repair_id"),
            inverseJoinColumns = @JoinColumn(name = "repairAction_id")
    )
    private List<RepairAction> repairAction = new ArrayList<>();

    @Column(name = "Status")
    @NotBlank(message = "Veld is leeg!")
    @Enumerated(EnumType.ORDINAL)
    private RepairStatus status;

    @OneToOne(mappedBy = "repair")
    private Receipt receipt;

    public Repair() {
    }

    public Repair(Long id, List<Part> usedPart, List<RepairAction> repairAction, RepairStatus status) {
        this.id = id;
        this.usedPart = usedPart;
        this.repairAction = repairAction;
        this.status = status;
    }

    public Repair(List<Part> usedPart, List<RepairAction> repairAction, RepairStatus status) {
        this.usedPart = usedPart;
        this.repairAction = repairAction;
        this.status = status;
    }

    //Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Part> getUsedPart() {
        return usedPart;
    }

    public void setUsedPart(List<Part> usedPart) {
        this.usedPart = usedPart;
    }

    public List<RepairAction> getRepairAction() {
        return repairAction;
    }

    public void setRepairAction(List<RepairAction> repairAction) {
        this.repairAction = repairAction;
    }

    public RepairStatus getStatus() {
        return status;
    }

    public void setStatus(RepairStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Inspection{" +
                "id=" + id +
                ", used parts='" + usedPart +  '\'' +
                ", performed repair actions='" + repairAction + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
