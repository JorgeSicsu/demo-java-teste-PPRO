package com.faturamento.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@SpringBootApplication
public class DemoApplication {

	@Operation(summary = "Retorna a mensagem Hello World")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida")
	})
	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello World! Teste realizado por: Jorge Bastos!";
	}

	@GetMapping("/faturamento")
	@Operation(summary = "Retorna a mensagem Faturamento de Clientes")
	public String faturamento() {
		return "// Faturamento de Clientes //";
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
