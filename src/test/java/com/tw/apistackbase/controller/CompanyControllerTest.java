package com.tw.apistackbase.controller;

import com.tw.apistackbase.Service.CompanyService;
import com.tw.apistackbase.core.Company;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CompanyController.class)
@ActiveProfiles(profiles = "test")
class CompanyControllerTest {

    @MockBean
    CompanyService service;

    @Autowired
    MockMvc mvc;

    @Test
    void should_Return_Company_List() throws Exception {
        Iterable<Company> companyList = new ArrayList<>();
        when(service.getAllCompany(1, 5)).thenReturn(companyList);

        ResultActions result = mvc.perform(get("/companies")
                .contentType("application/json;charset=UTF-8")
                .param("page", "1")
                .param("pageSize", "5"));
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", is(companyList)));
    }
}