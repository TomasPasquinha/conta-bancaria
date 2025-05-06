package contabancaria.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import Conta_Bancaria.Repository.ContaRepository;
import contabancaria.model.Conta;

public class ContaController implements ContaRepository {

    private ArrayList<Conta> listaContas = new ArrayList<>();
    private int numero = 1;

    public int gerarNumero() {
        return numero++;
    }

    @Override
    public void procurarPorNumero(int numero) {
        Optional<Conta> conta = buscarNaCollection(numero);

        if (conta.isPresent())
            conta.get().visualizar();
        else
            System.out.println("Conta número " + numero + " não foi encontrada.");
    }

    @Override
    public void listarTodas() {
        for (var conta : listaContas) {
            conta.visualizar();
        }
    }
    
    @Override
	public void listarPorTitular(String titular) {
    	
    	List<Conta> listaTitulares = listaContas.stream()
    			.filter(c -> c.getTitular().contains(titular))
    			.collect(Collectors.toList());
    	
    	if(listaTitulares.isEmpty())
    		System.out.printf("\nNenhuma conta foi encontrada com base no critério: %s", titular);
    		
    	for(var conta : listaTitulares)
    		conta.visualizar();
    		
    }

    @Override
    public void cadastrar(Conta conta) {
        listaContas.add(conta);
        System.out.println("Conta cadastrada com sucesso!");
    }

    @Override
    public void atualizar(Conta conta) {
        Optional<Conta> buscarConta = buscarNaCollection(conta.getNumero());

        if (buscarConta.isPresent()) {
            listaContas.set(listaContas.indexOf(buscarConta.get()), conta);
            System.out.printf("Conta número %d foi atualizada com sucesso!\n", conta.getNumero());
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    @Override
    public void deletar(int numero) {
        Optional<Conta> conta = buscarNaCollection(numero);

        if (conta.isPresent()) {
            if (listaContas.remove(conta.get()))
                System.out.printf("Conta número %d deletada com sucesso!\n", numero);
        } else {
            System.out.printf("Conta número %d não encontrada.\n", numero);
        }
    }

    @Override
    public void sacar(int numero, float valor) {
        Optional<Conta> conta = buscarNaCollection(numero);

        if (conta.isPresent()) {
            if (conta.get().sacar(valor) == true)
                System.out.println("Saque realizado com sucesso!");
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    @Override
    public void depositar(int numero, float valor) {
        Optional<Conta> conta = buscarNaCollection(numero);

        if (conta.isPresent()) {
            conta.get().depositar(valor);
            System.out.println("Depósito realizado com sucesso!");
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    @Override
    public void transferir(int numeroOrigem, int numeroDestino, float valor) {
        Optional<Conta> contaOrigem = buscarNaCollection(numeroOrigem);
        Optional<Conta> contaDestino = buscarNaCollection(numeroDestino);

        if (contaOrigem.isPresent() && contaDestino.isPresent()) {
            if (contaOrigem.get().sacar(valor)) {
                contaDestino.get().depositar(valor);
                System.out.println("Transferência realizada com sucesso!");
            }
        } else {
            System.out.println("Conta de origem ou destino não encontrada.");
        }
    }

    // Método auxiliar
    public Optional<Conta> buscarNaCollection(int numero) {
        for (var conta : listaContas) {
            if (conta.getNumero() == numero)
                return Optional.of(conta);
        }
        return Optional.empty();
    }

	
		
		
	}

