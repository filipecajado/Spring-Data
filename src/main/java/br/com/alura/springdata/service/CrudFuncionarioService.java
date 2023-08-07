package br.com.alura.springdata.service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.alura.springdata.orm.Cargo;
import br.com.alura.springdata.orm.Funcionario;
import br.com.alura.springdata.orm.UnidadeTrabalho;
import br.com.alura.springdata.repository.CargoRepository;
import br.com.alura.springdata.repository.FuncionarioRepository;
import br.com.alura.springdata.repository.UnidadeTrabalhoRepository;

@Service
public class CrudFuncionarioService {
	
	private Boolean system = true;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private final FuncionarioRepository funcionarioRepository;
	
	private final UnidadeTrabalhoRepository unidadeRepository;
	
	private final CargoRepository cargoRepository;

	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository,
			UnidadeTrabalhoRepository unidadeRepository, CargoRepository cargoRepository) {
		this.funcionarioRepository = funcionarioRepository;
		this.unidadeRepository = unidadeRepository;
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
				visualizar(scan);
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
		System.out.println("Nome: ");
		String nome = scan.next();
		
		System.out.println("CPF: ");
		String cpf = scan.next();
		
		System.out.println("Salario: ");
		Double salario = scan.nextDouble();
		
		System.out.println("Data de contratacao: ");
		String dataContratacao = scan.next();
		
		System.out.println("Digite o cargoId: ");
		Integer cargo_id = scan.nextInt();
		
		List<UnidadeTrabalho> unidades = unidade(scan);
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
		Optional<Cargo> cargo = cargoRepository.findById(cargo_id);
		funcionario.setCargo(cargo.get());
		funcionario.setUnidadeTrabalhos(unidades);
		funcionarioRepository.save(funcionario);
		System.out.println("Salvo");
	}
	
	private List<UnidadeTrabalho> unidade (Scanner scan){
		Boolean isTrue = true;
		List<UnidadeTrabalho> unidades = new ArrayList<>();
		
		while(isTrue) {
			System.out.println("Digite o unidadeId (Para sair digite 0");
			Integer unidadeId = scan.nextInt();
			
			if(unidadeId != 0) {
				Optional<UnidadeTrabalho> unidade = unidadeRepository.findById(unidadeId);
				unidades.add(unidade.get());
			} else {
				isTrue = false;
			}
		}
		
		return unidades;
	}
	
	private void atualizar(Scanner scan) {
		System.out.println("Id: ");
		Integer id = scan.nextInt();
		
		System.out.println("Nome: ");
		String nome = scan.next();
		
		System.out.println("CPF: ");
		String cpf = scan.next();
		
		System.out.println("Salario: ");
		Double salario = scan.nextDouble();
		
		System.out.println("Data de contratacao: ");
		String dataContratacao = scan.next();
		
		System.out.println("Digite o cargoId: ");
		Integer cargo_id = scan.nextInt();
		
		
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(LocalDate.parse(dataContratacao));
		Optional<Cargo> cargo = cargoRepository.findById(cargo_id);
		funcionario.setCargo(cargo.get());

		funcionarioRepository.save(funcionario);

		System.out.println("Atualizado");
	}
	
	private void visualizar(Scanner scan) {
	 System.out.println("Qual página voce deseja visualizar ");
	 Integer page = scan.nextInt();	
	 
	 Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.ASC, "nome"));
	
	 Page<Funcionario> funcionarios	= funcionarioRepository.findAll(pageable);
	 
	 System.out.println(funcionarios);
	 System.out.println("Pagina atual " + funcionarios.getNumber());
	 System.out.println("Total elemento " + funcionarios.getTotalElements());
	 funcionarios.forEach(funcionario -> System.out.println(funcionario));
	 
	}
	
	
	private void deletar(Scanner scan) {
		System.out.println("Id: ");
		int id = scan.nextInt();	
		funcionarioRepository.deleteById(id);
		System.out.println("Deletado");
	}
	
	
}
