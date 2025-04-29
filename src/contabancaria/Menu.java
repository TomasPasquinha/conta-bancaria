package contabancaria;

import java.util.Scanner;

import contabancaria.model.ContaCorrente;
import contabancaria.model.ContaPoupanca;
import contabancaria.util.Cores;

public class Menu {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int opcao;

		// Teste Conta Corrente
		ContaCorrente cc1 = new ContaCorrente(2, 456, 1, "Renata Negrini", 600000, 600000);
		cc1.visualizar();

		cc1.sacar(700000);
		cc1.visualizar();

		cc1.depositar(300000);
		cc1.visualizar();

	      //Teste Conta Poupan�a
        ContaPoupanca cp1 = new ContaPoupanca(3, 789, 2, "Lorenzo de Jesus", 100000, 15);
        cp1.visualizar();
        cp1.sacar(100000);
        cp1.visualizar();
        cp1.depositar(50000);
        cp1.visualizar();
        
		while (true) {
			System.out.println("\n|___________________________________________________________________________|");
			System.out.println("\n|_____________________________________________________________________________|");

			System.out.println(Cores.TEXT_PURPLE_BOLD + Cores.ANSI_GREEN_BACKGROUND
					+ "============================================\n"
					+ "             CONTA BANC�RIA                 \n"
					+ "============================================\n" + "1 - Criar Conta\n"
					+ "2 - Listar todas as Contas\n" + "3 - Buscar Conta por N�mero\n"
					+ "4 - Atualizar Dados da Conta\n" + "5 - Apagar Conta\n" + "6 - Sacar\n" + "7 - Depositar\n"
					+ "8 - Transferir valores entre Contas\n" + "0 - Sair\n"
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
				System.out.println("Voc� escolheu: Criar Conta");
				break;
			case 2:
				System.out.println("Voc� escolheu: Listar todas as Contas");
				break;
			case 3:
				System.out.println("Voc� escolheu: Buscar Conta por N�mero");
				break;
			case 4:
				System.out.println("Voc� escolheu: Atualizar Dados da Conta");
				break;
			case 5:
				System.out.println("Voc� escolheu: Apagar Conta");
				break;
			case 6:
				System.out.println("Voc� escolheu: Sacar");
				break;
			case 7:
				System.out.println("Voc� escolheu: Depositar");
				break;
			case 8:
				System.out.println("Voc� escolheu: Transferir valores entre Contas");
				break;
			default:
				System.out.println(Cores.TEXT_RED + "Op��o inv�lida. Tente novamente." + Cores.TEXT_RESET);
				break;
			}
		}
	}
}
