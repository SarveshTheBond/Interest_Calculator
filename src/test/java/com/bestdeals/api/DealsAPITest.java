package com.bestdeals.api;

import com.bestdeals.service.DealServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Sarvesh
 */
@RunWith(SpringRunner.class)
public class DealsAPITest {

    @InjectMocks
    private DealsAPI dealsAPI;

    private MockMvc mockMvc;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
        DealServiceImpl dealService = Mockito.mock(DealServiceImpl.class);
        Field field = ReflectionUtils.findField(DealsAPI.class, "dealService");
        ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field, dealsAPI, dealService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(dealsAPI).build();

    }

    @Test
    public void testCreateAccount() throws Exception {

        this.mockMvc.perform(post("/bestDeals/deals/createAccount")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{" +
                        "  \"bsb\": 182182," +
                        "  \"openingDate\": \"2021-10-10\"," +
                        "  \"identification\": 111222333" +
                        
                        "}"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testShowTotalInterest() throws Exception {

        this.mockMvc.perform(get("/bestDeals/deals/{accountId}", 111222333))
                .andExpect(status().is2xxSuccessful());

    }
    
    @Test
    public void testCloseAccount() throws Exception {

    	 this.mockMvc.perform(post("/bestDeals/deals/closeAccount")
                 .contentType(MediaType.APPLICATION_JSON_VALUE)
                 .content("{" +
                         "  \"bsb\": 182182," +
                         "  \"closingDate\": \"2021-10-10\"," +
                         "  \"identification\": 111222333" +
                         
                         "}"))
                 .andExpect(status().is2xxSuccessful());

    }
}
