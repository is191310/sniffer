package at.ac.fhstp.sniffer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;



@SpringBootTest
@AutoConfigureMockMvc
class SnifferApplicationTests {

	@Autowired
	private MockMvc mockMvc;


	@Test
	void shouldReturnSniffers() throws Exception {
		this.mockMvc.perform(get("/sniffer"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void shouldReturnPubdate() throws Exception {
		this.mockMvc.perform(get("/pubdate"))
				.andDo(print())
				.andExpect(status().isOk());
	}
	@Test
	void shouldReturnComment() throws Exception {
		this.mockMvc.perform(get("/comment"))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
}
