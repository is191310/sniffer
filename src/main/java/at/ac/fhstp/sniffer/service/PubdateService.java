package at.ac.fhstp.sniffer.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.fhstp.sniffer.entity.*;
import at.ac.fhstp.sniffer.exceptions.*;
import at.ac.fhstp.sniffer.repository.*;

@Service
public class PubdateService 
{
    PubdateRepository pubdateRepository;
    SnifferRepository snifferRepository;
    CommentRepository commentRepository;
    CommentService commentService;

    @Autowired
    public PubdateService(PubdateRepository pubdateRepository, SnifferRepository snifferRepository, CommentRepository commentRepository, CommentService commentService) 
    {
        this.pubdateRepository = pubdateRepository;
        this.snifferRepository = snifferRepository;
        this.commentRepository = commentRepository;
        this.commentService = commentService;
    }

    public Pubdate createPub(String title, int id)
    {
        if(title.isBlank())
        {
            throw new SnifferExceptions("Cannot create Pubdate, title is blank");
        }
        if(!snifferRepository.existsById(id))
        {
            throw new SnifferExceptionsNotfound("Sniffer with ID '" + id + "' not found");
        }
        
        Sniffer owner = snifferRepository.findById(id).get();
        
        return pubdateRepository.save(new Pubdate(title, owner));

    }

    public String likePub(int imgid, int fromid)
    {
        if(!snifferRepository.existsById(fromid))
        {
            throw new SnifferExceptionsNotfound("Sniffer with ID '" + fromid + "' not found");
        }
        
        if(!pubdateRepository.existsById(imgid))
        {
            throw new SnifferExceptionsNotfound("Pubdate with ID '" + imgid + "' not found");
        }

        Sniffer from = snifferRepository.findById(fromid).get();
        Pubdate likeimg = pubdateRepository.findById(imgid).get();
        
        likeimg.setliked_by(from);
        pubdateRepository.save(likeimg);
        return "You have liked " + likeimg.getTitle();
    }

    public String unlikePub(int imgid, int fromid)
    {
        if(!snifferRepository.existsById(fromid))
        {
            throw new SnifferExceptionsNotfound("Sniffer with ID '" + fromid + "' not found");
        }
        
        if(!pubdateRepository.existsById(imgid))
        {
            throw new SnifferExceptionsNotfound("Pubdate with ID '" + imgid + "' not found");
        }

        Sniffer from = snifferRepository.findById(fromid).get();
        Pubdate likeimg = pubdateRepository.findById(imgid).get();
        
        likeimg.removeliked_by(from);
        pubdateRepository.save(likeimg);
        return "You have liked " + likeimg.getTitle();
    }



    public String commentPub(String com, int imgid, int fromid)
    {
        if(!pubdateRepository.existsById(imgid))
        {
            throw new SnifferExceptionsNotfound("Pubdate with ID '" + imgid + "' not found");
        }
        if(!snifferRepository.existsById(fromid))
        {
            throw new SnifferExceptionsNotfound("Sniffer with ID '" + fromid + "' not found");
        }
        if(com.isBlank())
        {
            throw new SnifferExceptions("Cannot create comment, comment is blank");
        }
        
        Pubdate comimg = pubdateRepository.findById(imgid).get();
        Comments co = commentService.creatComment(com, fromid, comimg);
        
        comimg.setComment(co);
        pubdateRepository.save(comimg);
        return "You commented " + comimg.getTitle();

    }

    public String share(int fromid, int imgid)
    {
        if(!snifferRepository.existsById(fromid))
        {
            throw new SnifferExceptionsNotfound("Sniffer with ID '" + fromid + "' not found");
            
            
        }    
        if(!pubdateRepository.existsById(imgid))
        {
            throw new SnifferExceptionsNotfound("Pubdate with ID '" + imgid + "' not found");
        }

        Sniffer from = snifferRepository.findById(fromid).get();      
        Pubdate pub = pubdateRepository.findById(imgid).get();
        
        from.setShared(pub);
        snifferRepository.save(from);

        return "You have shared " + pub.getTitle();
    }

    public Set<Pubdate> getAllPubdates() 
    {
        Set<Pubdate> pubdateList = new HashSet<Pubdate>();
        pubdateRepository.findAll().forEach(a -> pubdateList.add(a));
        return pubdateList;
    }

    public Pubdate getPub(int id)
    {
        if(!pubdateRepository.existsById(id))
        {
            throw new SnifferExceptionsNotfound("Pubdate with ID '" + id + "' not found");
            
        }
        
        return pubdateRepository.findById(id).get();
    }

    public String delete(int id) 
    {
        if(!pubdateRepository.existsById(id))
        {
            throw new SnifferExceptionsNotfound("Pubdate with ID '" + id + "' not found");
        }
        
        pubdateRepository.deleteById(id);

        return "Pubdate deleted";
    }
}
