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
    PupdateRepository pupdateRepository;
    
    @Autowired
    public CommentService(CommentRepository commentRepository, SnifferRepository snifferRepository,  PupdateRepository pupdateRepository) 
    {
        this.commentRepository = commentRepository;
        this.snifferRepository = snifferRepository;
        this.pupdateRepository = pupdateRepository;
    }

    public Comments creatComment(String comment, int fromid, Pupdate pub) 
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
        
        return commentRepository.save(new Comments(comment, c, pub));
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

    public String delete(int id) 
    {
        if(!(commentRepository.existsById(id)))
        {
            throw new SnifferExceptionsNotfound("Comment with ID '" + id + "' not found");
        }

        commentRepository.deleteById(id);

        return "Comment deleted";
    }
}
