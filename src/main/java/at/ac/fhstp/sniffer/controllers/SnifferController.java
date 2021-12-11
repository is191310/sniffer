package at.ac.fhstp.sniffer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    

    @GetMapping()
    public List<Sniffer> getAll()
    {
        return sniff.getAllSniffers();
    }

    
}
