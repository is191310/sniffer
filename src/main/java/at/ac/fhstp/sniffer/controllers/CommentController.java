package at.ac.fhstp.sniffer.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.ac.fhstp.sniffer.entity.Comments;

import at.ac.fhstp.sniffer.service.CommentService;

@RestController("CommentController")
@RequestMapping("/comments")
public class CommentController 
{
    CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /*@GetMapping("{fromid}/like/{cid}")
    public void add(@PathVariable("cid") int cid, @PathVariable("fromid") int fromid) 
    {
        commentService.likeComment(cid, fromid);
    }*/

    @GetMapping("/{id}")
    public Comments getCom(@PathVariable("id")int id)
    {
        return commentService.getComment(id);
    }

    @DeleteMapping("/{id}")
    public void delComment(@PathVariable("id")int id)
    {
        commentService.delete(id);
    }

    @GetMapping()
    public Set<Comments> getAll()
    {
        return commentService.getAllComments();
    }

}
