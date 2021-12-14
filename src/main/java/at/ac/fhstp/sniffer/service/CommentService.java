package at.ac.fhstp.sniffer.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.fhstp.sniffer.entity.*;
import at.ac.fhstp.sniffer.exceptions.*;
import at.ac.fhstp.sniffer.repository.*;

@Service
public class CommentService 
{
    CommentRepository commentRepository;
    SnifferRepository snifferRepository;
    PubdateRepository pubdateRepository;
    
    @Autowired
    public CommentService(CommentRepository commentRepository, SnifferRepository snifferRepository,  PubdateRepository pubdateRepository) 
    {
        this.commentRepository = commentRepository;
        this.snifferRepository = snifferRepository;
        this.pubdateRepository = pubdateRepository;
    }

    public Comments creatComment(String comment, int fromid) 
    {
        if(!snifferRepository.existsById(fromid))
        {
            throw new SnifferExceptionsNotfound("Sniffer with ID '" + fromid + "' not found");
        }
        if(comment.isBlank())
        {
            throw new SnifferExceptions("Cannot create comment, comment is blank");
        }

        Sniffer c = snifferRepository.findById(fromid).get();
        
        return commentRepository.save(new Comments(comment, c));
    }

    public Set<Comments> getAllComments() 
    {
        Set<Comments> commentList = new HashSet<Comments>();
        commentRepository.findAll().forEach(a -> commentList.add(a));
        return commentList;
    }

    public Comments getComment(int id)
    {
        if(!commentRepository.existsById(id))
        {
            throw new SnifferExceptionsNotfound("Comment with ID '" + id + "' not found");
        }
        
        return commentRepository.findById(id).get();     
    }

    public void delete(int id) 
    {
        if(!(commentRepository.existsById(id)))
        {
            throw new SnifferExceptionsNotfound("Comment with ID '" + id + "' not found");
        }
       
        for(Pubdate p :  pubdateRepository.findAll())
        {
            for(Comments c : p.getComment())
            {
                if(c.equals(commentRepository.findById(id).get()))
                {
                        p.removeComment(c);
                        pubdateRepository.save(p);
                }
            }
        }
        commentRepository.deleteById(id);
    }

    public void likeComment(int cid, int fromid) 
    {
        if(!snifferRepository.existsById(fromid))
        {
            throw new SnifferExceptionsNotfound("Sniffer with ID '" + fromid + "' not found");
        }
        if(!commentRepository.existsById(cid))
        {
            throw new SnifferExceptionsNotfound("Comment with ID '" + cid + "' not found");
        }

        Sniffer from = snifferRepository.findById(fromid).get();
        Comments likecom = commentRepository.findById(cid).get();
        
        likecom.setliked_by(from);
        commentRepository.save(likecom);
    }
}
