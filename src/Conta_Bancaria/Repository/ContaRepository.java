package Conta_Bancaria.Repository;

import contabancaria.model.Conta;

public interface ContaRepository {
	
	//Metodo CRUD
	public void procurarPorNumero(int numero);
	public void listarTodas();
	public void cadastrar(Conta conta);
	public void atualizar(Conta conta);
	public void deletar(int conta);
	
	//Metodos Bancarios
	public void sacar(int numero, float valor);
	public void depositar(int numero, float valor);
	public void transferir(int numeroOrigem, int numeroDestino,  float valor);
}
