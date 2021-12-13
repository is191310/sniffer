package at.ac.fhstp.sniffer;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import at.ac.fhstp.sniffer.entity.Sniffer;
import at.ac.fhstp.sniffer.service.SnifferService;

@SpringBootTest
@AutoConfigureMockMvc
class SnifferApplicationTests {

	@MockBean
	private SnifferService snifferService;
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

	@Test
	void testGetExample() throws Exception {
		Set<Sniffer> sniffers = new HashSet<>();
		Sniffer sniffer = new Sniffer();
		sniffer.setId(1);
		sniffer.setName("Arun");
		sniffers.add(sniffer);
		Mockito.when(snifferService.getAllSniffers()).thenReturn(sniffers);
		mockMvc.perform(get("/sniffer"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].name", Matchers.equalTo("Arun")));
	}

}
