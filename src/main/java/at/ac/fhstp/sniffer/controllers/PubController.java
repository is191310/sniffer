package at.ac.fhstp.sniffer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.ac.fhstp.sniffer.Entity.Pubdate;
import at.ac.fhstp.sniffer.service.PubdateService;


@RestController("PubController")
@RequestMapping("/pubdate")
public class PubController 
{
    @Autowired
    PubdateService pub;


    
    @GetMapping("{id}/add/{title}")
    public Pubdate add(@PathVariable String title, @PathVariable int id)
    {
        return pub.createPub(title, id);
    }

    @GetMapping("{id}")
    public Pubdate getId(@PathVariable int id)
    {
        return pub.getPub(id);
    }

    @GetMapping("{fromid}/like/{imgid}")
    public void add(@PathVariable int imgid, @PathVariable int fromid)
    {
        pub.likePub(imgid, fromid);
    }

    @GetMapping("{fromid}/comment/{imgid}/{comment}")
    public void comment(@PathVariable String comment, @PathVariable int imgid, @PathVariable int fromid)
    {
        
        pub.commentPub(comment, imgid, fromid);
    }
    

    @GetMapping()
    public List<Pubdate> getAll()
    {
        return pub.getAllPubdates();
    }
}
