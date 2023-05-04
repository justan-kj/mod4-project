package sg.edu.ntu.m3project.m3project.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import sg.edu.ntu.m3project.m3project.entity.Transport;
import sg.edu.ntu.m3project.m3project.repo.TransportRepository;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TransportController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class TransportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransportRepository mockRepo;

    @Test
    public void givenNoDataExist_whenFetchAll_thenReturnNotFound() throws Exception {
        // mock repository layer
        List<Transport> transports = new ArrayList<>();
        when(mockRepo.findAll()).thenReturn(transports);
    
        // call the method
        this.mockMvc.perform(get("/transport"))
                    .andDo(print())
                    .andExpect(status().isNotFound());
    }

    @Test
    public void givenDataExist_whenFetchAll_thenReturnOk() throws Exception {
        // mock repository layer
        Transport mockTransport = new Transport();
        List<Transport> transports = new ArrayList<>();
        transports.add(mockTransport);
        when(mockRepo.findAll()).thenReturn(transports);
    
        // call the method
        this.mockMvc.perform(get("/transport"))
                    .andDo(print())
                    .andExpect(status().isOk());
    }

}