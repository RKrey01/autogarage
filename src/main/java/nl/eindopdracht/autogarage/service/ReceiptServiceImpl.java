package nl.eindopdracht.autogarage.service;

import nl.eindopdracht.autogarage.model.Inspection;
import nl.eindopdracht.autogarage.model.Receipt;
import nl.eindopdracht.autogarage.model.Repair;
import nl.eindopdracht.autogarage.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReceiptServiceImpl implements ReceiptService{
    private final ReceiptRepository repository;

    @Autowired
    public ReceiptServiceImpl(@Lazy ReceiptRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addNewReceipt(Receipt receipt) {
        repository.save(receipt);
    }

    @Override
    public List<Receipt> getReceipts(){
        return repository.findAll();
    }

    @Override
    public Receipt findReceiptById(Long receiptId) {
        Optional<Receipt> result = repository.findById(receiptId);

        Receipt receipt = null;
        if (result.isPresent()) {
            receipt = result.get();
        } else {
            throw new RuntimeException("Bon niet gevonden! ID - " + receiptId);
        }
        return receipt;
    }

    @Override
    public void deleteReceiptById(Long receiptId) {
        boolean exists = repository.existsById(receiptId);
        if (!exists) {
            throw new IllegalStateException("Bon met id " + receiptId + " bestaat niet");
        }
        repository.deleteById(receiptId);
    }

    @Override
    @Transactional
    public Receipt updateReceipt(Long receiptId, Inspection inspection, Repair repair, Double total) {
        Receipt receipt = repository.findById(receiptId)
                .orElseThrow(() -> new IllegalStateException("Bon met id " + receiptId + " bestaat niet"));


        if (inspection != null && inspection.toString().length() > 0 && !Objects.equals(receipt.getInspection(), inspection)) {
            receipt.setInspection(inspection);
        }

        if (repair != null && repair.toString().length() > 0 && !Objects.equals(receipt.getRepair(), repair)) {
            receipt.setRepair(repair);
        }

        if (total != null && total.toString().length() > 0 && !Objects.equals(receipt.getTotal(), total)) {
            receipt.setTotal(total);
        }

        return receipt;
    }
}
