package at.ac.fhstp.sniffer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import at.ac.fhstp.sniffer.Entity.Pubdate;
import at.ac.fhstp.sniffer.service.PubdateService;


@RestController("PubController")
@RequestMapping("/pubdate")
public class PubController 
{
    @Autowired
    PubdateService pub;
    
    @GetMapping("/add")
    public Pubdate add(@RequestParam(required = true)String title, int id)
    {
        return pub.createPub(title, id);
    }

    @GetMapping("/like")
    public void add(@RequestParam(required = true)int imgid, int fromid)
    {
        pub.likePub(imgid, fromid);
    }


    @GetMapping("/all")
    public List<Pubdate> getAll()
    {
        return pub.getAllPubdates();
    }
}
