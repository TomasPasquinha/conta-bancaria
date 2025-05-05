package contabancaria.model;

import java.text.NumberFormat;

public class ContaCorrente extends Conta {

    private float limite;

    // Construtor da ContaCorrente
    public ContaCorrente(int numero, int agencia, int tipo, String titular, float saldo, float limite) {
        super(numero, agencia, tipo, titular, saldo); // Chama o construtor da superclasse (Conta)
        this.limite = limite; // Inicializa o limite da conta corrente
    }

    // Getter e Setter para o limite da conta corrente
    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }
    
    // Sobrescrita do método sacar, que considera o limite da conta corrente
    @Override
    public boolean sacar(float valor) {
        // Verifica se o valor do saque é maior que o saldo disponível somado ao limite
        if ((this.getSaldo() + this.limite) < valor) {
            System.out.println("\nSaldo é Insuficiente");
            return false;
        }
        
        // Realiza o saque, diminuindo o saldo da conta
        this.setSaldo(this.getSaldo() - valor);
        return true;
    }
    
    // Sobrescrita do método visualizar para exibir informações específicas da Conta Corrente
    @Override
    public void visualizar() {
        // Formatação de moeda
        NumberFormat nfMoeda = NumberFormat.getCurrencyInstance();
        
        // Chama o método visualizar da classe mãe (Conta)
        super.visualizar();
        
        // Exibe o limite da conta formatado como moeda
        System.out.println("Limite da conta: " + nfMoeda.format(this.limite));
    }
}
