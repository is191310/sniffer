package at.ac.fhstp.sniffer.service;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.fhstp.sniffer.entity.*;
import at.ac.fhstp.sniffer.exceptions.*;
import at.ac.fhstp.sniffer.repository.*;


@Service
public class SnifferService 
{
    SnifferRepository snifferRepository;
    PubdateRepository pubdateRepository;
    CommentRepository commentRepository;
    CommentService commentService;
    
    @Autowired
    public SnifferService(SnifferRepository snifferRepository, CommentRepository commentRepository, PubdateRepository pubdateRepository, CommentService commentService) 
    {
        this.snifferRepository = snifferRepository;
        this.pubdateRepository = pubdateRepository;
        this.commentRepository = commentRepository;
        this.commentService = commentService;
    } 

    public Sniffer registerSniffer(String name)
    {
        if(name.isBlank())
        {
            throw new SnifferExceptions("Cannot create Sniffer, username is blank");
            
        }
        
        return snifferRepository.save(new Sniffer(name));
    }

    public Set<Sniffer> getAllSniffers() 
    {
        Set<Sniffer> sniffers = new HashSet<Sniffer>();
        snifferRepository.findAll().forEach(sniffer -> sniffers.add(sniffer));
        return sniffers;
    }

    public Sniffer getSnifferbyId(int id)
    {
        if(!snifferRepository.existsById(id))
        {
            throw new SnifferExceptionsNotfound("Sniffer with ID '" + id + "' not found");
            
        }
        
        return snifferRepository.findById(id).get();
    }

    public void follow(int fromid, int fid)
    {
        if(!snifferRepository.existsById(fromid))
        {
            throw new SnifferExceptionsNotfound("Sniffer with ID '" + fromid + "' not found");
        }
        if(!snifferRepository.existsById(fid))
        {   
            throw new SnifferExceptionsNotfound("Sniffer with ID '" + fid + "' not found");
        }
        if(fromid == fid)
        {
            throw new SnifferExceptions("You cannot follow yourself!");
        }
        
        Sniffer from = snifferRepository.findById(fromid).get();
        Sniffer follow = snifferRepository.findById(fid).get();
        follow.setfollowed_by(from);
        from.setfollowed(follow);
        snifferRepository.save(from);
        snifferRepository.save(follow);
    }

    public Set<Sniffer> getFollower(int id)
    {
        if(!snifferRepository.existsById(id))
        {
            throw new SnifferExceptionsNotfound("Sniffer with ID '" + id + "' not found");   
        }

        return snifferRepository.findById(id).get().getfollowed_by();
    }

    public Set<Sniffer> getFollowed(int id)
    {
        if(!snifferRepository.existsById(id))
        {
            throw new SnifferExceptionsNotfound("Sniffer with ID '" + id + "' not found");
            
        }
        
        return snifferRepository.findById(id).get().getfollowed();
    }
 
    public Set<Pubdate> getTimeline(int id)
    {
        Set<Pubdate> timeline = new TreeSet<Pubdate>();
        if(!snifferRepository.existsById(id))
        {
            throw new SnifferExceptionsNotfound("Sniffer with ID '" + id + "' not found");
        }
        
        Sniffer sniffer = snifferRepository.findById(id).get();
        
        for(Pubdate p : pubdateRepository.findAll())
        {
            for(Sniffer s : sniffer.getfollowed())
            {
                if(p.getPowner().equals(s))
                {
                    timeline.add(p);
                    
                }
                for(Pubdate pp : s.getShared())
                {
                    timeline.add(pp);
                }
            }
        }
    
        return timeline;
    }

    public void deleteSniffer(int id)
    {
        if(!snifferRepository.existsById(id))
        {   
            throw new SnifferExceptionsNotfound("Sniffer with ID '" + id + "' not found");

        }
        
        Sniffer user = snifferRepository.findById(id).get();
        
        for(Pubdate p : pubdateRepository.findAll())
        {
            for (Sniffer s : p.getliked_by())
            {
                if(s.equals(user))
                {
                    p.removesetliked_by(user);
                    pubdateRepository.save(p);
                }
            }
        }

        for(Pubdate p : pubdateRepository.findAll())
        {
            for (Comments c : p.getComment())
            {
                if(c.getCowner().equals(user))
                {
                    p.removeComment(c);
                    commentRepository.deleteById(c.getId());
                }
            }
        }

        for(Pubdate p : pubdateRepository.findAll())
        {
            if(p.getPowner().equals(snifferRepository.findById(id).get()))
            {
                for(Comments c : commentRepository.findAll())
                {
                    for(Comments cc : p.getComment())
                    {
                        if(c.equals(cc))
                        {
                            p.removeComment(c);
                            commentRepository.deleteById(c.getId());
                        }
                    }
                }
            }
        }

        for(Sniffer s : user.getfollowed())
        {
            s.removefollowed_by(user);
            snifferRepository.save(s);
        }

        for(Sniffer s : user.getfollowed_by())
        {
            s.removeFollowed(user);
        }

        snifferRepository.deleteById(id);
    }
}
