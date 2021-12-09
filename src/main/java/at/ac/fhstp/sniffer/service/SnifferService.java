package at.ac.fhstp.sniffer.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.fhstp.sniffer.Entity.Sniffer;
import at.ac.fhstp.sniffer.repository.SnifferRepository;

@Service
public class SnifferService {
    @Autowired
    SnifferRepository snifferRepository;

    public Sniffer registerSniffer(String name)
    {
        return snifferRepository.save(new Sniffer(name));
    }

    public List<Sniffer> getAllSniffers() {
        List<Sniffer> sniffers = new ArrayList<Sniffer>();
        snifferRepository.findAll().forEach(sniffer -> sniffers.add(sniffer));
        return sniffers;
    }

    public Sniffer getSnifferbyId(int id) {
        return snifferRepository.findById(id).get();
    }

    public void saveOrUpdate(Sniffer sniffer) {
        snifferRepository.save(sniffer);
    }

    public void delete(int id)
    {
        snifferRepository.deleteById(id);
    }
}
