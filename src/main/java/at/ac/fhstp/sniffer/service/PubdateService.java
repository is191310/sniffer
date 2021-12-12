package at.ac.fhstp.sniffer.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.fhstp.sniffer.entity.Comments;
import at.ac.fhstp.sniffer.entity.Pubdate;
import at.ac.fhstp.sniffer.entity.Sniffer;
import at.ac.fhstp.sniffer.repository.PubdateRepository;
import at.ac.fhstp.sniffer.repository.SnifferRepository;

@Service
public class PubdateService 
{
    PubdateRepository pubdateRepository;
    SnifferRepository snifferRepository;
    CommentService commentService;

    @Autowired
    public PubdateService(PubdateRepository pubdateRepository, SnifferRepository snifferRepository, CommentService commentService) 
    {
        this.pubdateRepository = pubdateRepository;
        this.snifferRepository = snifferRepository;
        this.commentService = commentService;
    }

    public Pubdate createPub(String title, int id)
    {
        Sniffer owner = snifferRepository.findById(id).get();

        return pubdateRepository.save(new Pubdate(title, owner));
    }

    public void likePub(int imgid, int fromid)
    {
        Sniffer from = snifferRepository.findById(fromid).get();
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

    public Set<Pubdate> getAllPubdates() 
    {
        Set<Pubdate> pubdateList = new HashSet<Pubdate>();
        pubdateRepository.findAll().forEach(a -> pubdateList.add(a));
        return pubdateList;
    }

    public Pubdate getPub(int id)
    {
        return pubdateRepository.findById(id).get();
    }

    public void saveOrUpdate(Pubdate pubdate) 
    {
        pubdateRepository.save(pubdate);

    }

    public void delete(int id) 
    {
        pubdateRepository.deleteById(id);
    }

}
