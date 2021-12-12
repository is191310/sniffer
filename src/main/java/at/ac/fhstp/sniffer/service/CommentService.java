package at.ac.fhstp.sniffer.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.fhstp.sniffer.entity.Comments;
import at.ac.fhstp.sniffer.entity.Sniffer;
import at.ac.fhstp.sniffer.exceptions.*;
import at.ac.fhstp.sniffer.repository.CommentRepository;
import at.ac.fhstp.sniffer.repository.SnifferRepository;

@Service
public class CommentService 
{
    CommentRepository commentRepository;
    SnifferRepository snifferRepository;
    
    @Autowired
    public CommentService(CommentRepository commentRepository, SnifferRepository snifferRepository) 
    {
        this.commentRepository = commentRepository;
        this.snifferRepository = snifferRepository;
    }

    public Comments creatComment(String comment, int fromid) 
    {
        if(snifferRepository.existsById(fromid))
        {
            Sniffer c = snifferRepository.findById(fromid).get();
            if(!comment.isBlank())
            {
                return commentRepository.save(new Comments(comment, c));
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

    public Set<Comments> getAllComments() 
    {
        Set<Comments> commentList = new HashSet<Comments>();
        commentRepository.findAll().forEach(a -> commentList.add(a));
        return commentList;
    }

    public Comments getComment(int id)
    {
        if(commentRepository.existsById(id))
        {
            return commentRepository.findById(id).get();
        }
        else
        {
            throw new SnifferExceptionsNotfound("Comment with ID '" + id + "' not found");
        }
        
    }

    public void delete(int id) 
    {
        if(commentRepository.existsById(id))
        {
            commentRepository.deleteById(id);
        }
        else
        {
            throw new SnifferExceptionsNotfound("Comment with ID '" + id + "' not found");
        }

    }

    public void likeComment(int cid, int fromid) 
    {
        if(snifferRepository.existsById(fromid))
        {
            Sniffer from = snifferRepository.findById(fromid).get();
            if(commentRepository.existsById(cid))
            {
                Comments likecom = commentRepository.findById(cid).get();
                likecom.setliked_by(from);
                commentRepository.save(likecom);
            }
            else
            {
                throw new SnifferExceptionsNotfound("Comment with ID '" + cid + "' not found");
            }
        }
        else
        {
            throw new SnifferExceptionsNotfound("Sniffer with ID '" + fromid + "' not found");
        }
    }
}
