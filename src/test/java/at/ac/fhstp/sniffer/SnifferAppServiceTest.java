package at.ac.fhstp.sniffer;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import at.ac.fhstp.sniffer.controllers.SnifferController;
import at.ac.fhstp.sniffer.repository.SnifferRepository;
import at.ac.fhstp.sniffer.service.SnifferService;


public class SnifferAppServiceTest {
private MockMvc mockMvc;

@Mock
private SnifferRepository snifferRepository;

@InjectMocks
private SnifferService snifferService;

@Test
public void 
}
