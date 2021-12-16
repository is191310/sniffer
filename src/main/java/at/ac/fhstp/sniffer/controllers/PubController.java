package at.ac.fhstp.sniffer.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.ac.fhstp.sniffer.entity.Pubdate;
import at.ac.fhstp.sniffer.service.PubdateService;


@RestController("PubController")
@RequestMapping("/pubdate")
public class PubController 
{
    PubdateService pubdateService;
    
    @Autowired
    public PubController(PubdateService pubdateService) 
    {
        this.pubdateService = pubdateService;
    }

    @PostMapping("{id}/add/{title}")
    public Pubdate add(@PathVariable String title, @PathVariable int id)
    {
        return pubdateService.createPub(title, id);
    }

    @DeleteMapping("{id}")
    public String del(@PathVariable int id)
    {
        return pubdateService.delete(id);
    }

    @GetMapping("{id}")
    public Pubdate getId(@PathVariable int id)
    {
        return pubdateService.getPub(id);
    }

    @PostMapping("{fromid}/like/{imgid}")
    public String like(@PathVariable int imgid, @PathVariable int fromid)
    {
        return pubdateService.likePub(imgid, fromid);
    }

    @PostMapping("{fromid}/unlike/{imgid}")
    public String unlike(@PathVariable int imgid, @PathVariable int fromid)
    {
        return pubdateService.unlikePub(imgid, fromid);
    }

    @PostMapping("{fromid}/comment/{imgid}/{comment}")
    public String comment(@PathVariable String comment, @PathVariable int imgid, @PathVariable int fromid)
    {
        
        return pubdateService.commentPub(comment, imgid, fromid);
    }
    
    @PostMapping("/{fromid}/share/{imgid}")
    public String getFollowed(@PathVariable("fromid")int fromid, @PathVariable("imgid")int imgid)
    {
        return pubdateService.share(fromid, imgid);
    }

    @GetMapping()
    public Set<Pubdate> getAll()
    {
        return pubdateService.getAllPubdates();
    }
}
