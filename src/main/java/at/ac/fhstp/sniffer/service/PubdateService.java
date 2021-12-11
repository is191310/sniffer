package at.ac.fhstp.sniffer.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.fhstp.sniffer.Entity.Comments;
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

    @Autowired
    CommentService commentService;


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

    public void commentPub(String com, int imgid, int fromid)
    {
        Comments co = commentService.creatComment(com, fromid, imgid);
        Pubdate comimg = pubdateRepository.findById(imgid).get();
        comimg.setComment(co);
        pubdateRepository.save(comimg);
    }

    public Set<Pubdate> getAllPubdates() {
        Set<Pubdate> pubdateList = new HashSet<Pubdate>();
        pubdateRepository.findAll().forEach(a -> pubdateList.add(a));
        return pubdateList;
    }

    public Pubdate getPub(int id)
    {
        return pubdateRepository.findById(id).get();
    }

    public void saveOrUpdate(Pubdate pubdate) {
        pubdateRepository.save(pubdate);

    }

    public void delete(int id) {
        pubdateRepository.deleteById(id);
    }

}
