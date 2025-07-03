package com.faturamento.demo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.faturamento.demo.Controller.FaturamentoPeriodController;
import com.faturamento.demo.Service.FaturamentoPeriodService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FaturamentoPeriodController.class)
public class FaturamentoPeriodControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FaturamentoPeriodService service;

    @Test
    void deveRetornarPeriodosDoAno() throws Exception {
        var periodo = new FaturamentoPeriodService.Periodo("2024-1", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 6));
        when(service.getPeriodosDoAno(2024)).thenReturn(List.of(periodo));

        mockMvc.perform(get("/periodos/2024"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].periodId").value("2024-1"))
                .andExpect(jsonPath("$[0].inicio").value("2024-01-01"))
                .andExpect(jsonPath("$[0].fim").value("2024-01-06"));
    }

    @Test
    void deveRetornarPeriodIdPorData() throws Exception {
        when(service.getPeriodId(LocalDate.of(2019, 1, 24))).thenReturn("2019-4");

        mockMvc.perform(get("/periodos/periodo")
                        .param("data", "2019-01-24"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.periodId").value("2019-4"));
    }

    @Test
    void deveRetornarErroParaDataInvalida() throws Exception {
        mockMvc.perform(get("/periodos/periodo")
                        .param("data", "data-invalida"))
                .andExpect(status().isBadRequest());
    }

}
