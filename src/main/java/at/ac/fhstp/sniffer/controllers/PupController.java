package at.ac.fhstp.sniffer.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.ac.fhstp.sniffer.entity.Pupdate;
import at.ac.fhstp.sniffer.service.PupdateService;


@RestController("PubController")
@RequestMapping("/pupdate")
public class PupController 
{
    PupdateService pupdateService;
    
    @Autowired
    public PupController(PupdateService pupdateService) 
    {
        this.pupdateService = pupdateService;
    }

    @PostMapping("{id}/create/{title}")
    public Pupdate add(@PathVariable String title, @PathVariable int id)
    {
        return pupdateService.createPup(title, id);
    }

    @DeleteMapping("{id}")
    public String del(@PathVariable int id)
    {
        return pupdateService.delete(id);
    }

    @GetMapping("{id}")
    public Pupdate getId(@PathVariable int id)
    {
        return pupdateService.getPup(id);
    }

    @PostMapping("{fromid}/like/{imgid}")
    public String like(@PathVariable int imgid, @PathVariable int fromid)
    {
        return pupdateService.likePup(imgid, fromid);
    }

    @PostMapping("{fromid}/unlike/{imgid}")
    public String unlike(@PathVariable int imgid, @PathVariable int fromid)
    {
        return pupdateService.unlikePup(imgid, fromid);
    }

    @PostMapping("{fromid}/comment/{imgid}/{comment}")
    public String comment(@PathVariable String comment, @PathVariable int imgid, @PathVariable int fromid)
    {
        
        return pupdateService.commentPup(comment, imgid, fromid);
    }
    
    @PostMapping("/{fromid}/share/{imgid}")
    public String getFollowed(@PathVariable("fromid")int fromid, @PathVariable("imgid")int imgid)
    {
        return pupdateService.share(fromid, imgid);
    }

    @GetMapping()
    public Set<Pupdate> getAll()
    {
        return pupdateService.getAllPupdates();
    }
}
