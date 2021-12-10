package at.ac.fhstp.sniffer.controllers;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import at.ac.fhstp.sniffer.Entity.Comments;

import at.ac.fhstp.sniffer.service.CommentService;

@RestController("CommentController")
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/addcomment")
    public Comments add(@RequestParam(required = true) String comment, int id, Date date) {
        return commentService.creatComment(comment, id, date);
    }

    @GetMapping("/like")
    public void add(@RequestParam(required = true) int cid, int fromid) {
        commentService.likeComment(cid, fromid);
    }

}
