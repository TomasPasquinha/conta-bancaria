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
                + "             CONTA BANCÁRIA                 \n"
                + "============================================\n" 
                + "1 - Criar Conta\n"
                + "2 - Listar todas as Contas\n" 
                + "3 - Buscar Conta por Número\n"
                + "4 - Atualizar Dados da Conta\n"
                + "5 - Apagar Conta\n" 
                + "6 - Sacar\n" 
                + "7 - Depositar\n"
                + "8 - Transferir valores entre Contas\n" 
                + "9 - Consultar contas por titular\n"
                + "0 - Sair\n"
                + "============================================" + Cores.TEXT_RESET);

            System.out.print("Entre com a opção desejada: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(Cores.TEXT_RED + "Por favor, digite apenas números válidos!" + Cores.TEXT_RESET);
                continue;
            }

            if (opcao == 0) {
                System.out.println(Cores.TEXT_GREEN_BOLD + "Aplicação finalizada com sucesso!" + Cores.TEXT_RESET);
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
                    System.out.println(Cores.TEXT_RED + "Opção inválida. Tente novamente." + Cores.TEXT_RESET);
                    break;
            }
        }
    }

    private static void criarConta() {
        System.out.println("Escolha o tipo de conta:");
        System.out.println("1 - Conta Corrente");
        System.out.println("2 - Conta Poupança");
        int tipoConta = scanner.nextInt();

        System.out.print("Número da Agência: ");
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
            System.out.print("Dia do Aniversário da Conta Poupança: ");
            int aniversario = scanner.nextInt();
            Conta contaPoupanca = new ContaPoupanca(numeroConta, agencia, tipoConta, titular, saldo, aniversario);
            contaController.cadastrar(contaPoupanca);
        } else {
            System.out.println(Cores.TEXT_RED + "Tipo de conta inválido!" + Cores.TEXT_RESET);
        }
    }

    private static void listarContas() {
        System.out.println("\nListando todas as contas:");
        contaController.listarTodas();
    }

    private static void buscarContaPorNumero() {
        System.out.print("\nInforme o número da conta: ");
        int numeroConta = scanner.nextInt();
        contaController.procurarPorNumero(numeroConta);
    }

    private static void atualizarConta() {
        System.out.print("\nInforme o número da conta a ser atualizada: ");
        int numeroConta = scanner.nextInt();

        // Busca a conta por número
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
                System.out.print("Novo dia do aniversário da Conta Poupança: ");
                int novoAniversario = scanner.nextInt();
                ContaPoupanca contaPoupancaAtualizada = new ContaPoupanca(numeroConta, contaExistente.getAgencia(), contaExistente.getTipo(), novoTitular, novoSaldo, novoAniversario);
                contaController.atualizar(contaPoupancaAtualizada);
            }
        } else {
            System.out.println(Cores.TEXT_RED + "Conta não encontrada!" + Cores.TEXT_RESET);
        }
    }

    private static void apagarConta() {
        System.out.print("\nInforme o número da conta a ser apagada: ");
        int numeroConta = scanner.nextInt();
        contaController.deletar(numeroConta);
    }

    private static void sacar() {
        System.out.print("\nInforme o número da conta: ");
        int numeroConta = scanner.nextInt();
        System.out.print("Informe o valor do saque: ");
        float valor = scanner.nextFloat();
        contaController.sacar(numeroConta, valor);
    }

    private static void depositar() {
        System.out.print("\nInforme o número da conta: ");
        int numeroConta = scanner.nextInt();
        System.out.print("Informe o valor do depósito: ");
        float valor = scanner.nextFloat();
        contaController.depositar(numeroConta, valor);
    }

    private static void transferir() {
        System.out.print("\nInforme o número da conta de origem: ");
        int numeroOrigem = scanner.nextInt();
        System.out.print("Informe o número da conta de destino: ");
        int numeroDestino = scanner.nextInt();
        System.out.print("Informe o valor da transferência: ");
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

