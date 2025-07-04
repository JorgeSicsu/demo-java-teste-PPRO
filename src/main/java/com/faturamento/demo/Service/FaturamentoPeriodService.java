package com.faturamento.demo.Service;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.time.*;
import java.time.temporal.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FaturamentoPeriodService {
    private final Map<LocalDate, String> dataParaPeriodoCache = new ConcurrentHashMap<>();
    private final Map<Integer, List<Periodo>> anoParaPeriodosCache = new ConcurrentHashMap<>();

    public String getPeriodId(LocalDate data) {
        return dataParaPeriodoCache.computeIfAbsent(data, this::calcularPeriodId);
    }

    public List<Periodo> getPeriodosDoAno(int ano) {
        return anoParaPeriodosCache.computeIfAbsent(ano, this::calcularTodosPeriodos);
    }

    private String calcularPeriodId(LocalDate data) {
        List<Periodo> periodos = getPeriodosDoAno(data.getYear());
        for (Periodo p : periodos) {
            if (!data.isBefore(p.inicio) && !data.isAfter(p.fim)) {
                return p.periodId;
            }
        }
        return null;
    }

    private List<Periodo> calcularTodosPeriodos(int ano) {
        List<Periodo> periodos = new ArrayList<>();
        LocalDate inicio = LocalDate.of(ano, 1, 1);
        LocalDate fim = LocalDate.of(ano, 12, 31);

        int contador = 1;
        LocalDate atual = inicio;
        while (!atual.isAfter(fim)) {
            LocalDate proximo = proximoInicioPeriodo(atual.plusDays(1), fim);
            if (proximo == null || proximo.isBefore(atual)) break;
            LocalDate termino = proximo.minusDays(1);
            if (termino.isAfter(fim)) termino = fim;
            periodos.add(new Periodo("%d-%d".formatted(ano, contador++), atual, termino));
            atual = proximo;
        }

        return periodos;
    }

    public List<String> calcularPeriodosPorDatas(List<LocalDate> datas)
    {
        List<String> resultado = new ArrayList<>();
        for (LocalDate data : datas) {
            List<Periodo> periodos = getPeriodosDoAno(data.getYear());
            for (Periodo p : periodos) {
                if (!data.isBefore(p.inicio) && !data.isAfter(p.fim)) {
                    resultado.add(p.periodId + " ==> Data: " + data);
                    break; // pula para próxima data após encontrar o período
                }
            }
        }
        return resultado;
    }
    private LocalDate proximoInicioPeriodo(LocalDate inicio, LocalDate max) {
        for (LocalDate d = inicio; !d.isAfter(max); d = d.plusDays(1)) {
            if (d.getDayOfWeek() == DayOfWeek.SATURDAY || d.getDayOfMonth() == 1) {
                return d;
            }
        }
        return null;
    }

    public record Periodo(String periodId, LocalDate inicio, LocalDate fim) {}
}