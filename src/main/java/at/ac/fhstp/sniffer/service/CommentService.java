package at.ac.fhstp.sniffer.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.fhstp.sniffer.Entity.Comments;
import at.ac.fhstp.sniffer.Entity.Sniffer;
import at.ac.fhstp.sniffer.repository.CommentRepository;
import at.ac.fhstp.sniffer.repository.SnifferRepository;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    SnifferRepository commentator;

    public Comments creatComment(String comment, int fromid, int imgid) {
        Sniffer c = commentator.findById(fromid).get();
        return commentRepository.save(new Comments(comment, c));
    }

    public Set<Comments> getAllComments() {
        Set<Comments> commentList = new HashSet<Comments>();
        commentRepository.findAll().forEach(a -> commentList.add(a));
        return commentList;
    }

    public void delete(int id) {
        commentRepository.deleteById(id);
    }

    public void likeComment(int cid, int fromid) {
        Sniffer from = commentator.findById(fromid).get();
        Comments likecom = commentRepository.findById(cid).get();
        likecom.setliked_by(from);
        commentRepository.save(likecom);
    }
}