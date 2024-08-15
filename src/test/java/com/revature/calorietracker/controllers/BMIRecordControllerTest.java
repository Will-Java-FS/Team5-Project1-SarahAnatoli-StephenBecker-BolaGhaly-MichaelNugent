package com.revature.calorietracker.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.calorietracker.models.BMIRecord;
import com.revature.calorietracker.service.BMIRecordService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BMIRecordController.class)
public class BMIRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BMIRecordService bmiRecordService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddNewBmiRecord() throws Exception {
        // Sample BMIRecord object
        BMIRecord sampleBMIRecord = new BMIRecord();
        sampleBMIRecord.setId(1L);
        //sampleBMIRecord.setBmiValue(22.5);
        // Assuming User and recordedAt fields are set as well

        // Mock the service method
        Mockito.doNothing().when(bmiRecordService).saveBMIRecord(Mockito.any(BMIRecord.class));

        // Perform the PATCH request
        mockMvc.perform(patch("/addbmirecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleBMIRecord)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(sampleBMIRecord)));
    }
}