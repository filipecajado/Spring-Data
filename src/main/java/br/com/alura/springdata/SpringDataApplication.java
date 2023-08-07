package br.com.alura.springdata;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.springdata.service.CrudCargoService;
import br.com.alura.springdata.service.CrudFuncionarioService;
import br.com.alura.springdata.service.CrudUnidadeTrabalhoService;
import br.com.alura.springdata.service.RelatorioFuncionarioDinamico;
import br.com.alura.springdata.service.RelatoriosService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final CrudCargoService cargoService;
	private final CrudFuncionarioService funcioanarioService;
	private final CrudUnidadeTrabalhoService unidadeService;
	private final RelatoriosService relatoriosService;
	private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;

	private Boolean system = true;

	public SpringDataApplication(CrudCargoService cargoService, CrudFuncionarioService funcioanarioService,
			CrudUnidadeTrabalhoService unidadeService, RelatoriosService relatoriosService, RelatorioFuncionarioDinamico relatorioFuncionarioDinamico) {
		this.cargoService = cargoService;
		this.funcioanarioService = funcioanarioService;
		this.unidadeService = unidadeService;
		this.relatoriosService = relatoriosService;
		this.relatorioFuncionarioDinamico = relatorioFuncionarioDinamico;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scan = new Scanner(System.in);

		while (system) {
			System.out.println("Qual acao voce quer executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionario");
			System.out.println("3 - Unidade de Trabalho");
			System.out.println("4 - Relatorios");
			System.out.println("5 - Relatorio dinamico");

			int action = scan.nextInt();

			switch (action) {
			case 1:
				cargoService.inicial(scan);
				break;
			case 2:
				funcioanarioService.inicial(scan);
				break;
			case 3:
				unidadeService.inicial(scan);
				break;
			case 4:
				relatoriosService.inicial(scan);
				break;
			case 5:
				relatorioFuncionarioDinamico.inicial(scan);
				break;
			default:
				system = false;
				break;
			}
		}

	}

}
