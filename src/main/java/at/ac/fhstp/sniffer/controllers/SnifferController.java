package at.ac.fhstp.sniffer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import at.ac.fhstp.sniffer.Entity.Sniffer;
import at.ac.fhstp.sniffer.service.SnifferService;

@RestController("SnifferController")
@RequestMapping("/sniffer")
public class SnifferController 
{
    @Autowired
    SnifferService sniff;

    @GetMapping("/register")
    public Sniffer reg(@RequestParam(required = true)String name)
    {
        return sniff.registerSniffer(name);
    }

    @GetMapping("/del")
    public void del(@RequestParam(required = true)int id)
    {
        sniff.deleteSniffer(id);
    }

    @GetMapping("/id")
    public Sniffer getId(@RequestParam(required = true)int id)
    {
        return sniff.getSnifferbyId(id);
    }

    @GetMapping("/all")
    public List<Sniffer> getAll()
    {
        return sniff.getAllSniffers();
    }

    
}
