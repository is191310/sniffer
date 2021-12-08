package at.ac.fhstp.sniffer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SnifferApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnifferApplication.class, args);
	}

	@GetMapping("/image")
    public int getImage(@RequestParam(required = true)int id)
    {
        return id;
    }

}
