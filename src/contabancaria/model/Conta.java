package contabancaria.model;

import java.text.NumberFormat;

public abstract class Conta {

    // Atributos da Classe 
    private int numero;
    private int agencia;
    private int tipo;
    private String titular;
    private float saldo;

    // Método construtor
    public Conta(int numero, int agencia, int tipo, String titular, float saldo) {
        this.numero = numero;
        this.agencia = agencia;
        this.tipo = tipo;
        this.titular = titular;
        this.saldo = saldo;
    }

    public int getNumero() {
        return numero;
    }

    // Métodos Get e Set
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public boolean sacar(float valor) {
        if (this.saldo < valor) {
            System.out.println("\nSaldo é Insuficiente");
            return false;
        }
        this.saldo -= valor;
        return true;
    }

    public void depositar(float valor) {
        this.saldo += valor;
    }

    public void visualizar() {
        NumberFormat nfMoeda = NumberFormat.getCurrencyInstance();

        String tipoConta = "";  // Corrigido de "tipo" para "tipoConta"
        
        // Definir tipo da conta de acordo com o valor de this.tipo
        switch(this.tipo) {
            case 1:
                tipoConta = "Conta Corrente";
                break;
            case 2:
                tipoConta = "Conta Poupança";
                break;
            default:
                tipoConta = "Inválido";
        }

        // Exibição dos dados da conta
        System.out.println("DADOS DA CONTA.");
        System.out.println("Número da Conta: " + this.numero);
        System.out.println("Número da Agência: " + this.agencia);
        System.out.println("Tipo da conta: " + tipoConta);  // Corrigido para exibir "tipoConta"
        System.out.println("Titular da conta: " + this.titular);
        System.out.println("Saldo da conta: " + nfMoeda.format(this.saldo));
    }
}
