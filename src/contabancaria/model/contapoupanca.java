package contabancaria.model;

public class ContaPoupanca extends Conta {
    
    private int aniversario;

    public ContaPoupanca(int numero, int agencia, int tipo, String titular, float saldo, int aniversario) {
        super(numero, agencia, tipo, titular, saldo); // Chama o construtor da superclasse (Conta)
        this.aniversario = aniversario;  // Inicializa o anivers�rio
    }
    
    // Getter e Setter do anivers�rio da conta
    public int getAniversario() {
        return aniversario;
    }

    public void setAniversario(int aniversario) {
        this.aniversario = aniversario;
    }
    
    // M�todo de visualiza��o sobrescrito para incluir o anivers�rio
    @Override
    public void visualizar() {
        super.visualizar();  // Chama o m�todo visualizar da classe pai (Conta)
        System.out.println("Anivers�rio da conta: " + this.aniversario);  // Exibe o anivers�rio da conta
    }
}
