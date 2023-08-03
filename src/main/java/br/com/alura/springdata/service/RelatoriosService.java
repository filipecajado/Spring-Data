package br.com.alura.springdata.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.springdata.orm.Funcionario;
import br.com.alura.springdata.repository.FuncionarioRepository;

@Service
public class RelatoriosService {
	
	private Boolean system = true;
	
	private final FuncionarioRepository funcionarioRepository;
	
	public RelatoriosService(FuncionarioRepository funcionarioRepository) {

		this.funcionarioRepository = funcionarioRepository;
	}

	public void inicial(Scanner scan) {
		while(system) {
			System.out.println(" 0 - Sair");
			System.out.println(" 1 - Busca FuncionarioNome");
			
			int action = scan.nextInt();
			
			switch (action) {
			case 1:
				buscaFuncionarioNome(scan);
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
	
}
