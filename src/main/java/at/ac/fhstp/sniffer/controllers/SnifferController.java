package at.ac.fhstp.sniffer.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import at.ac.fhstp.sniffer.entity.Pubdate;
import at.ac.fhstp.sniffer.entity.Sniffer;
import at.ac.fhstp.sniffer.service.SnifferService;

@RestController("SnifferController")
@RequestMapping("/sniffer")
public class SnifferController 
{
    SnifferService snifferService;

    @Autowired
    public SnifferController(SnifferService snifferService) {
        this.snifferService = snifferService;
    }

    @PostMapping("/register/{name}")
    public Sniffer reg(@PathVariable("name") String name) {
        return snifferService.registerSniffer(name);
    }

    @DeleteMapping("{id}")
    public void delId(@PathVariable("id") int id) {
        snifferService.deleteSniffer(id);
    }

    @GetMapping("{id}")
    public Sniffer getId(@PathVariable("id") int id) {
        return snifferService.getSnifferbyId(id);
    }

    @GetMapping("/{id}/follower")
    public Set<Sniffer> getFollower(@PathVariable("id") int id) {
        return snifferService.getFollower(id);
    }

    @GetMapping("/{id}/followed")
    public Set<Sniffer> getFollowed(@PathVariable("id") int id) {
        return snifferService.getFollowed(id);
    }

    @PostMapping("/{fromid}/follow/{fid}")
    public void follow(@PathVariable("fromid") int fromid, @PathVariable("fid") int fid) {
        snifferService.follow(fromid, fid);
    }

    @PostMapping("/{fromid}/share/{imgid}")
    public void getFollowed(@PathVariable("fromid") int fromid, @PathVariable("imgid") int imgid) {
        snifferService.share(fromid, imgid);
    }

    @GetMapping("/{id}/share")
    public Set<Pubdate> getShare(@PathVariable("id") int id) {
        return snifferService.getShares(id);
    }

    @GetMapping("/{id}/timeline")
    public Set<Pubdate> timeline(@PathVariable("id") int id) {
        return snifferService.getTimeline(id);
    }

    @GetMapping()
    public Set<Sniffer> getAll() {
        return snifferService.getAllSniffers();
    }

    @DeleteMapping("/deleteMapping")
    public ResponseEntity<String> deleteEntity (@RequestParam ("sniffer-id") int snifferId) {
    String response = snifferService.deleteSniffer(snifferId);
    return new ResponseEntity<>(response, HttpStatus.OK);
}
}
