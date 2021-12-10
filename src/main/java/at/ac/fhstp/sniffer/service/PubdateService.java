package at.ac.fhstp.sniffer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import at.ac.fhstp.sniffer.Entity.Pubdate;
import at.ac.fhstp.sniffer.Entity.Sniffer;
import at.ac.fhstp.sniffer.repository.PubdateRepository;
import at.ac.fhstp.sniffer.repository.SnifferRepository;

@Service
public class PubdateService {
    @Autowired
    PubdateRepository pubdateRepository;
    
    @Autowired
    SnifferRepository sniff;


    public Pubdate createPub(String title, int id)
    {
        Sniffer owner = sniff.findById(id).get();

        return pubdateRepository.save(new Pubdate(title, owner));
    }

    public void likePub(int imgid, int fromid)
    {
        Sniffer from = sniff.findById(fromid).get();
        Pubdate likeimg = pubdateRepository.findById(imgid).get();
        likeimg.setliked_by(from);
        pubdateRepository.save(likeimg);
    }

    public List<Pubdate> getAllPubdates() {
        List<Pubdate> pubdateList = new ArrayList<Pubdate>();
        pubdateRepository.findAll().forEach(a -> pubdateList.add(a));
        return pubdateList;
    }

    public void getPub(int id)
    {
        pubdateRepository.findById(id);
    }

    public void saveOrUpdate(Pubdate pubdate) {
        pubdateRepository.save(pubdate);

    }

    public void delete(int id) {
        pubdateRepository.deleteById(id);
    }

}
