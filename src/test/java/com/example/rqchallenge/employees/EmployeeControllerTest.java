package com.example.rqchallenge.employees;

import com.example.rqchallenge.employees.utils.TestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService service;
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void getAllEmployees() throws Exception {
        Mockito.when(service.getAllEmployees()).thenReturn(TestUtil.getTestEmployees());
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(14)));
    }

    @Test
    void getEmployeeById() throws Exception {
        Mockito.when(service.getEmployeeById(anyString())).thenReturn(TestUtil.getTestEmployees().get(0));
        mockMvc.perform(get("/01"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(4)));
    }

    @Test
    void getEmployeesByNameSearch() throws Exception {
        Mockito.when(service.getEmployeesByNameSearch(anyString())).thenReturn(TestUtil.getTestEmployees().subList(9, 14));
        mockMvc.perform(get("/search/emp1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(5)));
    }

    @Test
    void getHighestSalaryOfEmployees() throws Exception {
        Mockito.when(service.getHighestSalaryOfEmployees()).thenReturn(14000);
        mockMvc.perform(get("/highestSalary"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getTopTenHighestEarningEmployeeNames() throws Exception {
        List<String> result = TestUtil.getTestEmployees().stream().map(Employee::getName).collect(Collectors.toList());
        Mockito.when(service.getTopTenHighestEarningEmployeeNames()).thenReturn(result);
        mockMvc.perform(get("/highestSalary"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}