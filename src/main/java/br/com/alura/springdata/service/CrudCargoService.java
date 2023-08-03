package br.com.alura.springdata.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.springdata.orm.Cargo;
import br.com.alura.springdata.repository.CargoRepository;

@Service
public class CrudCargoService {
	
	private Boolean system = true;
	
	private final CargoRepository cargoRepository;

	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
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
		System.out.println("Descrição do cargo");
		String descricao = scan.next();
		
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		
		cargoRepository.save(cargo);
		System.out.println("Salvo");
	}
	
	private void atualizar(Scanner scan) {
		System.out.println("Id: ");
		int id = scan.nextInt();
		System.out.println("Descrição do cargo");
		String descricao = scan.next();
		
		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(descricao);
		
		cargoRepository.save(cargo);
		System.out.println("Atualizado");
	}
	
	private void visualizar() {
	 Iterable<Cargo> cargos	= cargoRepository.findAll();
	 cargos.forEach(cargo -> System.out.println(cargo));
	 
	}
	
	
	
	private void deletar(Scanner scan) {
		System.out.println("Id: ");
		int id = scan.nextInt();	
		cargoRepository.deleteById(id);
		System.out.println("Deletado");
	}
	
	
}
