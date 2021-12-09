package at.ac.fhstp.sniffer.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import at.ac.fhstp.sniffer.Entity.Pubdate;
import at.ac.fhstp.sniffer.repository.PubdateRepository;

@Service
public class PubdateService {
    @Autowired
    PubdateRepository pubdateRepository;

    public List<Pubdate> getAllPubdates() {
        List<Pubdate> pubdateList = new ArrayList<Pubdate>();
        pubdateRepository.findAll().forEach(a -> pubdateList.add(a));
        return pubdateList;
    }

    public void saveOrUpdate(Pubdate pubdate) {
        pubdateRepository.save(pubdate);

    }

    public void delete(int id) {
        pubdateRepository.deleteById(id);
    }

}
