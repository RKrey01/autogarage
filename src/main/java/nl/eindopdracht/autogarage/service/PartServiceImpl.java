package nl.eindopdracht.autogarage.service;

import nl.eindopdracht.autogarage.model.Part;
import nl.eindopdracht.autogarage.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PartServiceImpl implements PartService{

    private final PartRepository partRepository;

    @Autowired
    public PartServiceImpl(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Override
    public List<Part> getParts(){
        return partRepository.findAll();
    }

    @Override
    public Part findPartById(Long partId) {
        Optional<Part> result = partRepository.findById(partId);

        Part part = null;
        if (result.isPresent()) {
            part = result.get();
        } else {
            throw new RuntimeException("Onderdeel niet gevonden! ID - " + partId);
        }

        return part;
    }

    @Override
    public void addNewPart(Part part) {
        Optional<Part> partOptional = partRepository.findPartByName(part.getName());
        if (partOptional.isPresent()) {
            throw new IllegalStateException("naam in gebruik");
        }
        partRepository.save(part);
    }

    @Override
    public void deletePartById(Long partId) {
        boolean exists = partRepository.existsById(partId);
        if (!exists) {
            throw new IllegalStateException("Onderdeel met id " + partId + " bestaat niet");
        }
        partRepository.deleteById(partId);
    }

    @Override
    @Transactional
    public Part updatePart(Long partId, String name, Double price, Integer stock) {
        Part part = partRepository.findById(partId)
                .orElseThrow(() -> new IllegalStateException("Onderdeel met id " + partId + " bestaat niet"));

        if (name != null && name.length() > 0 && !Objects.equals(part.getName(), name)) {
            Optional<Part> partOptional = partRepository.findPartByName(name);
            if (partOptional.isPresent()) {
                throw new IllegalStateException("naam in gebruik");
            }

            part.setName(name);
        }

        if (price != null && price > 0L && !Objects.equals(part.getPrice(), price)) {
            part.setPrice(price);
        }

        if (stock != null && stock > 0 && !Objects.equals(part.getStock(), stock)) {
            part.setStock(stock);
        }

        return part;
    }
}
