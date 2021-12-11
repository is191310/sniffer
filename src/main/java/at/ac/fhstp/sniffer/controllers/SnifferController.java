package at.ac.fhstp.sniffer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.ac.fhstp.sniffer.Entity.Pubdate;
import at.ac.fhstp.sniffer.Entity.Sniffer;
import at.ac.fhstp.sniffer.service.SnifferService;

@RestController("SnifferController")
@RequestMapping("/sniffer")
public class SnifferController 
{
    @Autowired
    SnifferService sniff;

    @GetMapping("/register/{name}")
    public Sniffer reg(@PathVariable("name")String name)
    {
        return sniff.registerSniffer(name);
    }

    @GetMapping("/del/{id}")
    public void del(@PathVariable("id")int id)
    {
        sniff.deleteSniffer(id);
    }

    @GetMapping("{id}")
    public Sniffer getId(@PathVariable("id")int id)
    {
        return sniff.getSnifferbyId(id);
    }

    @GetMapping("/{id}/follower")
    public List<Sniffer> getFollower(@PathVariable("id")int id)
    {
        return sniff.getFollower(id);
    }

    @GetMapping("/{id}/followed")
    public List<Sniffer> getFollowed(@PathVariable("id")int id)
    {
        return sniff.getFollowed(id);
    }

    @GetMapping("/{fromid}/follow/{fid}")
    public void follow(@PathVariable("fromid")int fromid, @PathVariable("fid")int fid)
    {
        sniff.follow(fromid, fid);
    }

    @GetMapping("/{fromid}/share/{imgid}")
    public void getFollowed(@PathVariable("fromid")int fromid, @PathVariable("imgid")int imgid)
    {
        sniff.share(fromid, imgid);
    }

    @GetMapping("/{id}/share")
    public List<Pubdate> getShare(@PathVariable("id")int id)
    {
        return sniff.getShares(id);
    }

    @GetMapping("/{id}/timeline")
    public List<Pubdate> timeline(@PathVariable("id")int id)
    {
        return sniff.getTimeline(id);
    }

    @GetMapping()
    public List<Sniffer> getAll()
    {
        return sniff.getAllSniffers();
    }

    
}
