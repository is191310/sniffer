package at.ac.fhstp.sniffer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashSet;
import java.util.Set;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import at.ac.fhstp.sniffer.entity.Comments;
import at.ac.fhstp.sniffer.entity.Pupdate;
import at.ac.fhstp.sniffer.entity.Sniffer;
import at.ac.fhstp.sniffer.service.CommentService;
import at.ac.fhstp.sniffer.service.PupdateService;
import at.ac.fhstp.sniffer.service.SnifferService;

@SpringBootTest
@AutoConfigureMockMvc
class SnifferApplicationTests {

	@MockBean
	private SnifferService snifferService;
	@MockBean
	private CommentService commentService;
	@MockBean
	private PupdateService pupdateService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void shouldReturnSniffers() throws Exception 
	{
		this.mockMvc.perform(get("/sniffer"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void shouldReturnPupdate() throws Exception 
	{
		this.mockMvc.perform(get("/pupdate"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void shouldReturnComment() throws Exception 
	{
		this.mockMvc.perform(get("/comments"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void testGetExample() throws Exception 
	{
		Set<Sniffer> sniffers = new HashSet<>();
		Sniffer sniffer = new Sniffer();
		sniffer.setId(1);
		sniffer.setName("Test");
		sniffers.add(sniffer);
		Mockito.when(snifferService.getAllSniffers()).thenReturn(sniffers);
		mockMvc.perform(get("/sniffer"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].name", Matchers.equalTo("Test")));
	}

	@Test
	public void main() 
	{
		SnifferApplication.main(new String [] {});
	}

	@Test
    public void testDeleteExample() throws Exception 
	{
        Mockito.when(snifferService.deleteSniffer(ArgumentMatchers.anyInt())).thenReturn("Sniffer is deleted!!");
        MvcResult requestResult = mockMvc.perform(delete("/sniffer/deleteMapping").param("sniffer-id", "1"))
                .andExpect(status().isOk()).andExpect(status().isOk()).andReturn();
        String result = requestResult.getResponse().getContentAsString();
        assertEquals(result, "Sniffer is deleted!!");
    }

	@Test
	void testComment() throws Exception 
	{
		Set<Comments> comments = new HashSet<>();
		Comments comment = new Comments();
		Pupdate pup = new Pupdate();
		comment.setPup(pup);
		comment.setId(1);
		comment.setText("Wilder Kommentar");
		comments.add(comment);
		Mockito.when(commentService.getAllComments()).thenReturn(comments);
		mockMvc.perform(get("/comments"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].text", Matchers.equalTo("Wilder Kommentar")));
	}
	
	@Test
	void testPupdates() throws Exception 
	{
		Set<Pupdate> pupdates = new HashSet<>();
		Pupdate pupdate = new Pupdate();
		pupdate.setId(1);
		pupdate.setTitle("Titel");
		pupdates.add(pupdate);
		Mockito.when(pupdateService.getAllPupdates()).thenReturn(pupdates);
		mockMvc.perform(get("/pupdate"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].title", Matchers.equalTo("Titel")));
	}

}
