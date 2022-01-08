package nl.eindopdracht.autogarage.controller;

import nl.eindopdracht.autogarage.model.Inspection;
import nl.eindopdracht.autogarage.model.Receipt;
import nl.eindopdracht.autogarage.model.Repair;
import nl.eindopdracht.autogarage.service.ReceiptServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/receipt")
public class ReceiptController {

    private final ReceiptServiceImpl service;

    @Autowired
    public ReceiptController (ReceiptServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public List<Receipt> getAllReceipts() {
        return service.getReceipts();
    }

    @GetMapping(path = "{receiptId}")
    public Receipt getReceiptById(@PathVariable("receiptId") Long receiptId) {
        return service.findReceiptById(receiptId);
    }

    @PostMapping
    public void registerNewReceipt(@RequestBody Receipt receipt) {
        service.addNewReceipt(receipt);
    }

    @DeleteMapping(path = "{receiptId}")
    public void deleteReceiptById(@PathVariable("receiptId") Long receiptId) {
        service.deleteReceiptById(receiptId);
    }

    @PutMapping(path = "{receiptId}")
    public void updateReceipt(
            @PathVariable("receiptId") Long receiptId,
            @RequestParam(required = false) Inspection inspection,
            @RequestParam(required = false) Repair repair,
            @RequestParam(required = false) Double total) {
        service.updateReceipt(receiptId, inspection, repair, total);
    }
}
