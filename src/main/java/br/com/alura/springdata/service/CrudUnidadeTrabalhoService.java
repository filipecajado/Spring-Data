package br.com.alura.springdata.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.springdata.orm.UnidadeTrabalho;
import br.com.alura.springdata.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService {
	
	private Boolean system = true;
	
	private final UnidadeTrabalhoRepository unidadeRepository;

	public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeRepository) {
		this.unidadeRepository = unidadeRepository;
	}
	
	public void inicial(Scanner scan) {
		while(system) {
			System.out.println(" 0 - Sair");
			System.out.println(" 1 - Cadastrar");
			System.out.println(" 2 - Atualizar");
			System.out.println(" 3 - Visualizar");
			System.out.println(" 4 - Deletar");
			
			int action = scan.nextInt();
			
			switch (action) {
			case 1:
				salvar(scan);
				break;
			case 2:
				atualizar(scan);
				break;
			case 3:
				visualizar();
				break;
			case 4:
				deletar(scan);
				break;
			default:
				system = false;
				break;
			}
		}
		salvar(scan);
	}
	
	private void salvar(Scanner scan) {
		System.out.println("Descrição da unidade");
		String descricao = scan.next();
		
		System.out.println("Endereco");
		String endereco = scan.next();
		
		UnidadeTrabalho unidade = new UnidadeTrabalho();
		unidade.setDescricao(descricao);
		unidade.setEndereco(endereco);
		
		unidadeRepository.save(unidade);
		System.out.println("Salvo");
	}
	
	private void atualizar(Scanner scan) {
		System.out.println("Id: ");
		int id = scan.nextInt();
		System.out.println("Descrição da unidade");
		String descricao = scan.next();
		
		System.out.println("Endereco");
		String endereco = scan.next();
		
		UnidadeTrabalho unidade = new UnidadeTrabalho();
		unidade.setId(id);
		unidade.setDescricao(descricao);
		unidade.setEndereco(endereco);
		
		unidadeRepository.save(unidade);
		System.out.println("Atualizado");
	}
	
	private void visualizar() {
	 Iterable<UnidadeTrabalho> unidades	= unidadeRepository.findAll();
	 unidades.forEach(unidade -> System.out.println(unidade));
	 
	}
	
	
	
	private void deletar(Scanner scan) {
		System.out.println("Id: ");
		int id = scan.nextInt();	
		unidadeRepository.deleteById(id);
		System.out.println("Deletado");
	}
	
	
}
