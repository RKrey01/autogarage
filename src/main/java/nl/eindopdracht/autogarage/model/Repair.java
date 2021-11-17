package nl.eindopdracht.autogarage.model;

import nl.eindopdracht.autogarage.Enumeration.RepairStatus;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Enumeration;
import java.util.List;

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

    @Column(name = "Part")
    @NotBlank(message = "Veld is leeg!")
    private List<Part>  usedPart;

    @Column(name = "RepairAction")
    @NotBlank(message = "Veld is leeg!")
    private List<RepairAction> repairAction;

    @Column(name = "Status")
    @NotBlank(message = "Veld is leeg!")
    private Enumeration<RepairStatus> status;

    public Repair() {
    }

    public Repair(Long id, List<Part> usedPart, List<RepairAction> repairAction, Enumeration<RepairStatus> status) {
        this.id = id;
        this.usedPart = usedPart;
        this.repairAction = repairAction;
        this.status = status;
    }

    public Repair(List<Part> usedPart, List<RepairAction> repairAction, Enumeration<RepairStatus> status) {
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

    public Enumeration<RepairStatus> getStatus() {
        return status;
    }

    public void setStatus(Enumeration<RepairStatus> status) {
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
