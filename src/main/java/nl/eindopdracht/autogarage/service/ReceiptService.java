package nl.eindopdracht.autogarage.service;

import nl.eindopdracht.autogarage.model.*;

import java.util.List;

public interface ReceiptService {

    List<Receipt> getReceipts();

    Receipt findReceiptById(Long repairId);

    void addNewReceipt(Receipt receipt);

    void deleteReceiptById(Long receiptId);

    Receipt updateReceipt(Long receiptId, Inspection inspection, Repair repair, Double total);
}
