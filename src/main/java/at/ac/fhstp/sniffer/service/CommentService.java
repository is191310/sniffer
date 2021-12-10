package at.ac.fhstp.sniffer.service;

import java.sql.Date;

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

public Comments creatComment(String comment, int id, Date date) {
    Sniffer c = commentator.findById(id).get();
    return commentRepository.save(new Comments(id, date, c));
}

public void delete(int id) {
    commentRepository.deleteById(id);
    }

}
