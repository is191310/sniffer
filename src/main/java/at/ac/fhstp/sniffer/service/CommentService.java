package at.ac.fhstp.sniffer.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.fhstp.sniffer.entity.Comments;
import at.ac.fhstp.sniffer.entity.Sniffer;
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

    public Comments creatComment(String comment, int fromid, int imgid) 
    {
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
        return commentRepository.findById(id).get();
    }

    public void delete(int id) 
    {
        commentRepository.deleteById(id);
    }

    public void likeComment(int cid, int fromid) 
    {
        Sniffer from = snifferRepository.findById(fromid).get();
        Comments likecom = commentRepository.findById(cid).get();
        likecom.setliked_by(from);
        commentRepository.save(likecom);
    }
}
