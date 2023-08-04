package br.com.alura.springdata.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.springdata.orm.Funcionario;
import br.com.alura.springdata.repository.FuncionarioRepository;

@Service
public class RelatoriosService {

	private Boolean system = true;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private final FuncionarioRepository funcionarioRepository;

	public RelatoriosService(FuncionarioRepository funcionarioRepository) {

		this.funcionarioRepository = funcionarioRepository;
	}

	public void inicial(Scanner scan) {
		while (system) {
			System.out.println(" 0 - Sair");
			System.out.println(" 1 - Busca FuncionarioNome");
			System.out.println(" 2 - Busca Salario Maior");
			System.out.println(" 3 - Busca Data Contratacao");

			int action = scan.nextInt();

			switch (action) {
			case 1:
				buscaFuncionarioNome(scan);
				break;
			case 2:
				buscaFuncionarioNomeSalarioMaiorData(scan);
				break;
			case 3:
				buscaFuncionarioDataContratacao(scan);
				break;
			default:
				system = false;
				break;
			}
		}
	}

	private void buscaFuncionarioNome(Scanner scan) {
		System.out.println("Qual nome deseja pesquisar? : ");
		String nome = scan.next();

		List<Funcionario> list = funcionarioRepository.findByNome(nome);

		list.forEach(System.out::println);

	}

	private void buscaFuncionarioNomeSalarioMaiorData(Scanner scan) {
		System.out.println("Qual nome deseja pesquisar? : ");
		String nome = scan.next();

		System.out.println("Qual data contratacao deseja pesquisar? : ");
		String data = scan.next();

		LocalDate localDate = LocalDate.parse(data, formatter);

		System.out.println("Qual salario deseja pesquisar? : ");
		Double salario = scan.nextDouble();

		List<Funcionario> list = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, localDate);

		list.forEach(System.out::println);
	}

	private void buscaFuncionarioDataContratacao(Scanner scan) {
		System.out.println("Qual data contratacao deseja pesquisar? : ");
		String data = scan.next();

		LocalDate localDate = LocalDate.parse(data, formatter);
		List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(localDate);

		list.forEach(System.out::println);
	}

}
