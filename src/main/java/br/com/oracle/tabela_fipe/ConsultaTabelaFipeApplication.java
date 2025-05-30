package br.com.oracle.tabela_fipe;

import br.com.oracle.tabela_fipe.service.Menu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsultaTabelaFipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ConsultaTabelaFipeApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Menu.menu();
	}
}
