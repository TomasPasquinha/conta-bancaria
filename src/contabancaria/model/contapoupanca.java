package contabancaria.model;

public class ContaPoupanca extends Conta {
    
    private int aniversario;

    public ContaPoupanca(int numero, int agencia, int tipo, String titular, float saldo, int aniversario) {
        super(numero, agencia, tipo, titular, saldo); // Chama o construtor da superclasse (Conta)
        this.aniversario = aniversario;  // Inicializa o aniversário
    }
    
    // Getter e Setter do aniversário da conta
    public int getAniversario() {
        return aniversario;
    }

    public void setAniversario(int aniversario) {
        this.aniversario = aniversario;
    }
    
    // Método de visualização sobrescrito para incluir o aniversário
    @Override
    public void visualizar() {
        super.visualizar();  // Chama o método visualizar da classe pai (Conta)
        System.out.println("Aniversário da conta: " + this.aniversario);  // Exibe o aniversário da conta
    }
}
