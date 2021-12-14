package at.ac.fhstp.sniffer.controllers;
/*
import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ch.qos.logback.core.net.ObjectWriter;

import at.ac.fhstp.sniffer.repository.CommentRepository;
import at.ac.fhstp.sniffer.controllers.CommentController;
import at.ac.fhstp.sniffer.entity.Comments;
import at.ac.fhstp.sniffer.entity.Sniffer;



@RunWith(MockitoJUnitRunner.class)
public class CommentControllerTest {
    
    private MockMvc mockMvc;
    
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = (ObjectWriter) objectMapper.writer();

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentController commentController;

    Date date;
    Sniffer sniff = new Sniffer();
   
    Comments TEST_CASE = new Comments(1, date,sniff);
    
    @BeforeAll
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
    }

    @Test
    public void  addComment_success() throws Exception {

       // Mockito.when(commentRepository.findAll()).thenReturn(TEST_CASE);
       // https://www.youtube.com/watch?v=KYkEMuA50yE
       // 1:09:00
    }
}*/