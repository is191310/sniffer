package at.ac.fhstp.sniffer.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController("PubController")
public class PubController 
{
    @GetMapping("/image")
    public int getImage(@RequestParam(required = true)int id)
    {
        return id;
    }

    @GetMapping("/hello2")
	public String hello(@RequestParam(value = "123", defaultValue = "World") String name) {
		return String.format("Hello %s", name);
	}

  
}
