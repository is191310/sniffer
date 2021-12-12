package at.ac.fhstp.sniffer.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.fhstp.sniffer.entity.Comments;
import at.ac.fhstp.sniffer.entity.Pubdate;
import at.ac.fhstp.sniffer.entity.Sniffer;
import at.ac.fhstp.sniffer.exceptions.SnifferExceptions;
import at.ac.fhstp.sniffer.exceptions.SnifferExceptionsNotfound;
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
        if(!title.isBlank())
        {
            if(snifferRepository.existsById(id))
            {
                Sniffer owner = snifferRepository.findById(id).get();
                return pubdateRepository.save(new Pubdate(title, owner));
            }
            else
            {
                throw new SnifferExceptionsNotfound("Sniffer with ID '" + id + "' not found");
            }
            
        }
        else
        {
            throw new SnifferExceptions("Cannot create Pubdate, title is blank");
        }
    }

    public void likePub(int imgid, int fromid)
    {
        if(snifferRepository.existsById(fromid))
        {
            Sniffer from = snifferRepository.findById(fromid).get();
            if(pubdateRepository.existsById(imgid))
            {
                Pubdate likeimg = pubdateRepository.findById(imgid).get();
                likeimg.setliked_by(from);
                pubdateRepository.save(likeimg);
            }
            else
            {
                throw new SnifferExceptionsNotfound("Pubdate with ID '" + imgid + "' not found");
            }

        }
        else
        {
            throw new SnifferExceptionsNotfound("Sniffer with ID '" + fromid + "' not found");
        }

    }

    public void commentPub(String com, int imgid, int fromid)
    {
        if(pubdateRepository.existsById(imgid))
        {
            Pubdate comimg = pubdateRepository.findById(imgid).get();
            if(snifferRepository.existsById(fromid))
            {
                if(!com.isBlank())
                {
                    Comments co = commentService.creatComment(com, fromid);
                    comimg.setComment(co);
                    pubdateRepository.save(comimg);
                }
                else
                {
                    throw new SnifferExceptions("Cannot create comment, comment is blank");
                }
            }
            else
            {
                throw new SnifferExceptionsNotfound("Sniffer with ID '" + fromid + "' not found");
            }
        }
        else
        {
            throw new SnifferExceptionsNotfound("Pubdate with ID '" + imgid + "' not found");
        }
    }

    public void share(int fromid, int imgid)
    {
        if(snifferRepository.existsById(fromid))
        {
            Sniffer from = snifferRepository.findById(fromid).get();
            if(pubdateRepository.existsById(imgid))
            {
                Pubdate pub = pubdateRepository.findById(imgid).get();
                from.setShared(pub);
                snifferRepository.save(from);
            }
            else
            {
                throw new SnifferExceptionsNotfound("Pubdate with ID '" + imgid + "' not found");
            }
        }
        else
        {
            throw new SnifferExceptionsNotfound("Sniffer with ID '" + fromid + "' not found");
        }
        
    }

    public Set<Pubdate> getShares(int id)
    {
        if(snifferRepository.existsById(id))
        {
            return snifferRepository.findById(id).get().getShared();
        }
        else
        {
            throw new SnifferExceptionsNotfound("Sniffer with ID '" + id + "' not found");
        }
    }

    public Set<Pubdate> getAllPubdates() 
    {
        Set<Pubdate> pubdateList = new HashSet<Pubdate>();
        pubdateRepository.findAll().forEach(a -> pubdateList.add(a));
        return pubdateList;
    }

    public Pubdate getPub(int id)
    {
        if(pubdateRepository.existsById(id))
        {
            return pubdateRepository.findById(id).get();
        }
        else
        {
            throw new SnifferExceptionsNotfound("Pubdate with ID '" + id + "' not found");
        }
    }

    public void delete(int id) 
    {
        if(pubdateRepository.existsById(id))
        {
            pubdateRepository.deleteById(id);
        }
        else
        {
            throw new SnifferExceptionsNotfound("Pubdate with ID '" + id + "' not found");
        }
    }

}
