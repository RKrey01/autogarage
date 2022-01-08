package nl.eindopdracht.autogarage.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "receipt")
public class Receipt {

    @Id
    @SequenceGenerator(
            name = "receipt_sequence",
            sequenceName = "receipt_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "receipt_sequence"
    )
    @Column(name = "ReceiptId")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inspection_id", referencedColumnName = "InspectionId")
    @NotBlank(message = "Veld is leeg!")
    private Inspection inspection;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "repair_id", referencedColumnName = "RepairId")
    @NotBlank(message = "Veld is leeg!")
    private Repair repair;

    @Column(name = "TotalPrice")
    @NotBlank(message = "Veld is leeg!")
    private Double total;

    public Receipt() {
    }

    public Receipt(Long id, Inspection inspection, Repair repair, Double total) {
        this.id = id;
        this.inspection = inspection;
        this.repair = repair;
        this.total = total;
    }

    public Receipt(Inspection inspection, Repair repair, Double total) {
        this.inspection = inspection;
        this.repair = repair;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Inspection getInspection() {
        return inspection;
    }

    public void setInspection(Inspection inspection) {
        this.inspection = inspection;
    }

    public Repair getRepair() {
        return repair;
    }

    public void setRepair(Repair repair) {
        this.repair = repair;
    }

    public List<Part> getUsedParts() {
        return repair.getUsedPart();
    }

    public List<RepairAction> getRepairActionsFromRepair() {
        return repair.getRepairAction();
    }

    public Double getTotal() {
        Double currentTotal = 0.0;

        currentTotal = 45.0;

        for (RepairAction r: getRepairActionsFromRepair()) {
            currentTotal = currentTotal + r.getPrice();
        }

        for (Part p : getUsedParts()) {
            currentTotal = currentTotal + p.getPrice();
        }

        double btw = currentTotal/100.0*21.0;
        currentTotal = currentTotal + btw;

        total = currentTotal;

        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", inspection=" + inspection +
                ", inspection price=" + "45.00" +
                ", repairActions=" + getRepairActionsFromRepair() +
                ", parts=" + getUsedParts() +
                ", total price=" + total +
                '}';
    }
}
