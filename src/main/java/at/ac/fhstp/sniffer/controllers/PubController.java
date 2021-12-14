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
    public PubController(PubdateService pubdateService) {
        this.pubdateService = pubdateService;
    }

    @PostMapping("{id}/add/{title}")
    public Pubdate add(@PathVariable String title, @PathVariable int id)
    {
        return pubdateService.createPub(title, id);
    }

    @DeleteMapping("{id}")
    public void del(@PathVariable int id)
    {
        pubdateService.delete(id);
    }

    @GetMapping("{id}")
    public Pubdate getId(@PathVariable int id)
    {
        return pubdateService.getPub(id);
    }

    @PostMapping("{fromid}/like/{imgid}")
    public void add(@PathVariable int imgid, @PathVariable int fromid)
    {
        pubdateService.likePub(imgid, fromid);
    }

    @PostMapping("{fromid}/comment/{imgid}/{comment}")
    public void comment(@PathVariable String comment, @PathVariable int imgid, @PathVariable int fromid)
    {
        
        pubdateService.commentPub(comment, imgid, fromid);
    }
    
    @PostMapping("/{fromid}/share/{imgid}")
    public void getFollowed(@PathVariable("fromid")int fromid, @PathVariable("imgid")int imgid)
    {
        pubdateService.share(fromid, imgid);
    }

    @GetMapping("/{id}/share")
    public Set<Pubdate> getShare(@PathVariable("id")int id)
    {
        return pubdateService.getShares(id);
    }

    @GetMapping()
    public Set<Pubdate> getAll()
    {
        return pubdateService.getAllPubdates();
    }
}
