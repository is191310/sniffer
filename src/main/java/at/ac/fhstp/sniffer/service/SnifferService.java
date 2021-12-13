package at.ac.fhstp.sniffer.service;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.fhstp.sniffer.entity.Pubdate;
import at.ac.fhstp.sniffer.entity.Sniffer;
import at.ac.fhstp.sniffer.exceptions.SnifferExceptions;
import at.ac.fhstp.sniffer.exceptions.SnifferExceptionsNotfound;
import at.ac.fhstp.sniffer.repository.PubdateRepository;
import at.ac.fhstp.sniffer.repository.SnifferRepository;

@Service
public class SnifferService 
{
    SnifferRepository snifferRepository;
    PubdateRepository pubdateRepository;
    
    @Autowired
    public SnifferService(SnifferRepository snifferRepository, PubdateRepository pubdateRepository) 
    {
        this.snifferRepository = snifferRepository;
        this.pubdateRepository = pubdateRepository;
    } 

    public Sniffer registerSniffer(String name)
    {
        if(!(name.isBlank()))
        {
            return snifferRepository.save(new Sniffer(name));
        }
        else
        {
            throw new SnifferExceptions("Cannot create Sniffer, username is blank");
        }
    }

    public Set<Sniffer> getAllSniffers() 
    {
        Set<Sniffer> sniffers = new HashSet<Sniffer>();
        snifferRepository.findAll().forEach(sniffer -> sniffers.add(sniffer));
        return sniffers;
    }

    public Sniffer getSnifferbyId(int id)
    {
        if(snifferRepository.existsById(id))
        {
            return snifferRepository.findById(id).get();
        }
        else
        {
            throw new SnifferExceptionsNotfound("Sniffer with ID '" + id + "' not found");
        }
    }

    public void follow(int fromid, int fid)
    {
        if(snifferRepository.existsById(fromid))
        {
            Sniffer from = snifferRepository.findById(fromid).get();
            if(snifferRepository.existsById(fid))
            {
                if(!(fromid == fid))
                {
                    Sniffer follow = snifferRepository.findById(fid).get();
                follow.setfollowed_by(from);
                from.setfollowed(follow);
                snifferRepository.save(from);
                snifferRepository.save(follow);
                }
                else
                {
                    throw new SnifferExceptions("You cannot follow yourself!");
                }
            }
            else
            {
                throw new SnifferExceptionsNotfound("Sniffer with ID '" + fid + "' not found");
            }
        }
        else
        {
            throw new SnifferExceptionsNotfound("Sniffer with ID '" + fromid + "' not found");
        }
    }

    public Set<Sniffer> getFollower(int id)
    {
        if(snifferRepository.existsById(id))
        {
            return snifferRepository.findById(id).get().getfollowed_by();
        }
        else
        {
            throw new SnifferExceptionsNotfound("Sniffer with ID '" + id + "' not found");
        }
    }

    public Set<Sniffer> getFollowed(int id)
    {
        if(snifferRepository.existsById(id))
        {
            return snifferRepository.findById(id).get().getfollowed();
        }
        else
        {
            throw new SnifferExceptionsNotfound("Sniffer with ID '" + id + "' not found");
        }

    }
 
    public Set<Pubdate> getTimeline(int id)
    {
        Set<Pubdate> timeline = new TreeSet<Pubdate>();
        if(snifferRepository.existsById(id))
        {
            Sniffer sniffer = snifferRepository.findById(id).get();
            for(Pubdate p : pubdateRepository.findAll())
            {
                for(Sniffer s : sniffer.getfollowed())
                {
                    if(p.getPowner().equals(s))
                    {
                        timeline.add(p);
                        for(Pubdate pp : s.getShared())
                        {
                            timeline.add(pp);
                        }
                    }
                }
            }
        }
        else
        {
            throw new SnifferExceptionsNotfound("Sniffer with ID '" + id + "' not found");
        }
        return timeline;
    }

    public void deleteSniffer(int id)
    {
        if(snifferRepository.existsById(id))
        {   
            snifferRepository.deleteById(id);
        }
        else
        {
            throw new SnifferExceptionsNotfound("Sniffer with ID '" + id + "' not found");
        }
    }
}
