package at.ac.fhstp.sniffer.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.fhstp.sniffer.entity.*;
import at.ac.fhstp.sniffer.exceptions.*;
import at.ac.fhstp.sniffer.repository.*;

@Service
public class PupdateService 
{
    PupdateRepository pupdateRepository;
    SnifferRepository snifferRepository;
    CommentRepository commentRepository;
    CommentService commentService;

    @Autowired
    public PupdateService(PupdateRepository pupdateRepository, SnifferRepository snifferRepository, CommentRepository commentRepository, CommentService commentService) 
    {
        this.pupdateRepository = pupdateRepository;
        this.snifferRepository = snifferRepository;
        this.commentRepository = commentRepository;
        this.commentService = commentService;
    }

    public Pupdate createPup(String title, int id)
    {
        if(title.isBlank())
        {
            throw new SnifferExceptions("Cannot create Pupdate, title is blank");
        }
        if(!snifferRepository.existsById(id))
        {
            throw new SnifferExceptionsNotfound("Sniffer with ID '" + id + "' not found");
        }
        
        Sniffer owner = snifferRepository.findById(id).get();
        
        return pupdateRepository.save(new Pupdate(title, owner));

    }

    public String likePup(int imgid, int fromid)
    {
        if(!snifferRepository.existsById(fromid))
        {
            throw new SnifferExceptionsNotfound("Sniffer with ID '" + fromid + "' not found");
        }
        
        if(!pupdateRepository.existsById(imgid))
        {
            throw new SnifferExceptionsNotfound("Pupdate with ID '" + imgid + "' not found");
        }

        Sniffer from = snifferRepository.findById(fromid).get();
        Pupdate likeimg = pupdateRepository.findById(imgid).get();
        
        likeimg.setliked_by(from);
        from.setLiked(likeimg);
        snifferRepository.save(from);
        pupdateRepository.save(likeimg);
        return "You have liked " + likeimg.getTitle();
    }

    public String unlikePup(int imgid, int fromid)
    {
        if(!snifferRepository.existsById(fromid))
        {
            throw new SnifferExceptionsNotfound("Sniffer with ID '" + fromid + "' not found");
        }
        
        if(!pupdateRepository.existsById(imgid))
        {
            throw new SnifferExceptionsNotfound("Pupdate with ID '" + imgid + "' not found");
        }

        Sniffer from = snifferRepository.findById(fromid).get();
        Pupdate likeimg = pupdateRepository.findById(imgid).get();
        
        likeimg.removeliked_by(from);
        pupdateRepository.save(likeimg);
        return "You have liked " + likeimg.getTitle();
    }

    public String commentPup(String com, int imgid, int fromid)
    {
        if(!pupdateRepository.existsById(imgid))
        {
            throw new SnifferExceptionsNotfound("Pupdate with ID '" + imgid + "' not found");
        }
        if(!snifferRepository.existsById(fromid))
        {
            throw new SnifferExceptionsNotfound("Sniffer with ID '" + fromid + "' not found");
        }
        if(com.isBlank())
        {
            throw new SnifferExceptions("Cannot create comment, comment is blank");
        }
        
        Pupdate comimg = pupdateRepository.findById(imgid).get();
        Comments co = commentService.creatComment(com, fromid, comimg);
        
        comimg.setComment(co);
        pupdateRepository.save(comimg);
        return "You commented " + comimg.getTitle();

    }

    public String share(int fromid, int imgid)
    {
        if(!snifferRepository.existsById(fromid))
        {
            throw new SnifferExceptionsNotfound("Sniffer with ID '" + fromid + "' not found");
            
            
        }    
        if(!pupdateRepository.existsById(imgid))
        {
            throw new SnifferExceptionsNotfound("Pupdate with ID '" + imgid + "' not found");
        }

        Sniffer from = snifferRepository.findById(fromid).get();      
        Pupdate pup = pupdateRepository.findById(imgid).get();
        
        from.setShared(pup);
        pup.setShared_by(from);
        snifferRepository.save(from);
        pupdateRepository.save(pup);

        return "You have shared " + pup.getTitle();
    }

    public Set<Pupdate> getAllPupdates() 
    {
        Set<Pupdate> pupdateList = new HashSet<Pupdate>();
        pupdateRepository.findAll().forEach(a -> pupdateList.add(a));
        return pupdateList;
    }

    public Pupdate getPup(int id)
    {
        if(!pupdateRepository.existsById(id))
        {
            throw new SnifferExceptionsNotfound("Pupdate with ID '" + id + "' not found");
            
        }
        
        return pupdateRepository.findById(id).get();
    }

    public String delete(int id) 
    {
        if(!pupdateRepository.existsById(id))
        {
            throw new SnifferExceptionsNotfound("Pupdate with ID '" + id + "' not found");
        }
        
        Pupdate pup = pupdateRepository.findById(id).get();
        
        for(Sniffer s : snifferRepository.findAll())
        {
            for(Pupdate pp : s.getShared())
            {
                if(pup.equals(pp))
                {
                    s.removeShared(pup);
                }
            }
        }
                
        pupdateRepository.deleteById(id);

        return "Pupdate deleted";
    }
}
