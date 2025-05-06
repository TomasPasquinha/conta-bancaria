package contabancaria;

import java.util.Scanner;
import contabancaria.controller.ContaController;
import contabancaria.model.Conta;
import contabancaria.model.ContaCorrente;
import contabancaria.model.ContaPoupanca;
import contabancaria.util.Cores;

public class Menu {

    private static ContaController contaController = new ContaController(); // Controle das contas
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int opcao;

        while (true) {
            System.out.println("\n|___________________________________________________________________________|");
            System.out.println("|___________________________________________________________________________|");
            System.out.println(Cores.TEXT_PURPLE_BOLD + Cores.ANSI_GREEN_BACKGROUND
                + "============================================\n"
                + "             CONTA BANC�RIA                 \n"
                + "============================================\n" 
                + "1 - Criar Conta\n"
                + "2 - Listar todas as Contas\n" 
                + "3 - Buscar Conta por N�mero\n"
                + "4 - Atualizar Dados da Conta\n"
                + "5 - Apagar Conta\n" 
                + "6 - Sacar\n" 
                + "7 - Depositar\n"
                + "8 - Transferir valores entre Contas\n" 
                + "9 - Consultar contas por titular\n"
                + "0 - Sair\n"
                + "============================================" + Cores.TEXT_RESET);

            System.out.print("Entre com a op��o desejada: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(Cores.TEXT_RED + "Por favor, digite apenas n�meros v�lidos!" + Cores.TEXT_RESET);
                continue;
            }

            if (opcao == 0) {
                System.out.println(Cores.TEXT_GREEN_BOLD + "Aplica��o finalizada com sucesso!" + Cores.TEXT_RESET);
                scanner.close();
                System.exit(0);
            }

            switch (opcao) {
                case 1:
                    criarConta();
                    break;
                case 2:
                    listarContas();
                    break;
                case 3:
                    buscarContaPorNumero();
                    break;
                case 4:
                    atualizarConta();
                    break;
                case 5:
                    apagarConta();
                    break;
                case 6:
                    sacar();
                    break;
                case 7:
                    depositar();
                    break;
                case 8:
                    transferir();
                    break;
                case 9:
                    consultarPorTitular();
                    break;
                default:
                    System.out.println(Cores.TEXT_RED + "Op��o inv�lida. Tente novamente." + Cores.TEXT_RESET);
                    break;
            }
        }
    }

    private static void criarConta() {
        System.out.println("Escolha o tipo de conta:");
        System.out.println("1 - Conta Corrente");
        System.out.println("2 - Conta Poupan�a");
        int tipoConta = scanner.nextInt();

        System.out.print("N�mero da Ag�ncia: ");
        int agencia = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        System.out.print("Nome do Titular: ");
        String titular = scanner.nextLine();
        System.out.print("Saldo Inicial: ");
        float saldo = scanner.nextFloat();

        int numeroConta = contaController.gerarNumero();

        if (tipoConta == 1) {
            System.out.print("Limite da Conta Corrente: ");
            float limite = scanner.nextFloat();
            Conta contaCorrente = new ContaCorrente(numeroConta, agencia, tipoConta, titular, saldo, limite);
            contaController.cadastrar(contaCorrente);
        } else if (tipoConta == 2) {
            System.out.print("Dia do Anivers�rio da Conta Poupan�a: ");
            int aniversario = scanner.nextInt();
            Conta contaPoupanca = new ContaPoupanca(numeroConta, agencia, tipoConta, titular, saldo, aniversario);
            contaController.cadastrar(contaPoupanca);
        } else {
            System.out.println(Cores.TEXT_RED + "Tipo de conta inv�lido!" + Cores.TEXT_RESET);
        }
    }

    private static void listarContas() {
        System.out.println("\nListando todas as contas:");
        contaController.listarTodas();
    }

    private static void buscarContaPorNumero() {
        System.out.print("\nInforme o n�mero da conta: ");
        int numeroConta = scanner.nextInt();
        contaController.procurarPorNumero(numeroConta);
    }

    private static void atualizarConta() {
        System.out.print("\nInforme o n�mero da conta a ser atualizada: ");
        int numeroConta = scanner.nextInt();

        // Busca a conta por n�mero
        Conta contaExistente = contaController.buscarNaCollection(numeroConta).orElse(null);

        if (contaExistente != null) {
            // Atualiza os dados da conta
            System.out.print("Novo nome do Titular: ");
            scanner.nextLine(); // Limpar buffer
            String novoTitular = scanner.nextLine();
            System.out.print("Novo saldo: ");
            float novoSaldo = scanner.nextFloat();

            if (contaExistente instanceof ContaCorrente) {
                System.out.print("Novo limite da Conta Corrente: ");
                float novoLimite = scanner.nextFloat();
                ContaCorrente contaCorrenteAtualizada = new ContaCorrente(numeroConta, contaExistente.getAgencia(), contaExistente.getTipo(), novoTitular, novoSaldo, novoLimite);
                contaController.atualizar(contaCorrenteAtualizada);
            } else if (contaExistente instanceof ContaPoupanca) {
                System.out.print("Novo dia do anivers�rio da Conta Poupan�a: ");
                int novoAniversario = scanner.nextInt();
                ContaPoupanca contaPoupancaAtualizada = new ContaPoupanca(numeroConta, contaExistente.getAgencia(), contaExistente.getTipo(), novoTitular, novoSaldo, novoAniversario);
                contaController.atualizar(contaPoupancaAtualizada);
            }
        } else {
            System.out.println(Cores.TEXT_RED + "Conta n�o encontrada!" + Cores.TEXT_RESET);
        }
    }

    private static void apagarConta() {
        System.out.print("\nInforme o n�mero da conta a ser apagada: ");
        int numeroConta = scanner.nextInt();
        contaController.deletar(numeroConta);
    }

    private static void sacar() {
        System.out.print("\nInforme o n�mero da conta: ");
        int numeroConta = scanner.nextInt();
        System.out.print("Informe o valor do saque: ");
        float valor = scanner.nextFloat();
        contaController.sacar(numeroConta, valor);
    }

    private static void depositar() {
        System.out.print("\nInforme o n�mero da conta: ");
        int numeroConta = scanner.nextInt();
        System.out.print("Informe o valor do dep�sito: ");
        float valor = scanner.nextFloat();
        contaController.depositar(numeroConta, valor);
    }

    private static void transferir() {
        System.out.print("\nInforme o n�mero da conta de origem: ");
        int numeroOrigem = scanner.nextInt();
        System.out.print("Informe o n�mero da conta de destino: ");
        int numeroDestino = scanner.nextInt();
        System.out.print("Informe o valor da transfer�ncia: ");
        float valor = scanner.nextFloat();
        contaController.transferir(numeroOrigem, numeroDestino, valor);
    }

    private static void consultarPorTitular() {
        System.out.print("\nInforme o nome (ou parte dele) do titular: ");
        scanner.nextLine(); // limpar buffer
        String titular = scanner.nextLine();
        contaController.listarPorTitular(titular);
    }
}

