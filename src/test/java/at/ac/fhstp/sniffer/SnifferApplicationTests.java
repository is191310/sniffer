package at.ac.fhstp.sniffer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import at.ac.fhstp.sniffer.repository.SnifferRepository;

@SpringBootTest
@AutoConfigureMockMvc
class SnifferApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	SnifferRepository snifferRepository;

	@Test
	void shouldReturnSniffers() throws Exception {
		this.mockMvc.perform(get("/sniffer"))
				.andDo(print())
				.andExpect(status().isOk());
	}

}
