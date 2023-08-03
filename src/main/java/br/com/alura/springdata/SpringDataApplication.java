package br.com.alura.springdata;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.springdata.service.CrudCargoService;
import br.com.alura.springdata.service.CrudFuncionarioService;
import br.com.alura.springdata.service.CrudUnidadeTrabalhoService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner{

	private final CrudCargoService cargoService;
	private final CrudFuncionarioService funcioanarioService;
	private final CrudUnidadeTrabalhoService unidadeService;
	
	private Boolean system = true;
	
	public SpringDataApplication(CrudCargoService cargoService, CrudFuncionarioService funcioanarioService, CrudUnidadeTrabalhoService unidadeService) {
		this.cargoService = cargoService;
		this.funcioanarioService = funcioanarioService;
		this.unidadeService = unidadeService;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);			
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scan = new Scanner(System.in);
		
		while(system) {
			System.out.println("Qual acao voce quer executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionario");
			System.out.println("3 - Unidade de Trabalho");
			
			int action = scan.nextInt();
			if(action == 1) {
				cargoService.inicial(scan);
			} else if (action == 2){
				funcioanarioService.inicial(scan);
			} else if (action == 3){
				unidadeService.inicial(scan);
			}else {
				system = false;
			}
		
		}
		

	}

}
