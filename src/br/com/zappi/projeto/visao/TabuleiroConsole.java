package br.com.zappi.projeto.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.zappi.projeto.excecao.ExplosaoException;
import br.com.zappi.projeto.excecao.SairException;
import br.com.zappi.projeto.modelo.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner sc = new Scanner(System.in);

	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;

		executarJogo();
	}

	private void executarJogo() {
		try {
			boolean continuar = true;

			while (continuar) {
				cicloDoJogo();

				System.out.println("Deseja Continuar? (S/n) ");
				String resp = sc.next();

				if ("n".equalsIgnoreCase(resp)) {
					continuar = false;
				} else {
					tabuleiro.reiniciar();
				}
			}
		} catch (SairException e) {
			System.out.println("Ate Mais!!!");
		} finally {
			sc.close();
		}
	}

	private void cicloDoJogo() {
		try {
			
			while (!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);
				
				String digitado = capturarValorDigitado("Digite (x, y): ");
				
				Iterator<Integer> xy = Arrays.stream(digitado.split(","))
						.map(e -> Integer.parseInt(e.trim())).iterator();
				
				digitado = capturarValorDigitado("1 - Abrir ou 2 - (Des)marcar");
				
				if ("1".equals(digitado)) {
					tabuleiro.abrir(xy.next(), xy.next());
				} else if("2".equals(digitado)) {
					tabuleiro.marcar(xy.next(), xy.next());
				}
				
			}
			
			System.out.println(tabuleiro);
			System.out.println("Voce ganhou!!!");
		} catch (ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Voce perdeu!!!");
		}
	}

	private String capturarValorDigitado(String texto) {
		System.out.print(texto);
		String digitado = sc.nextLine();

		if ("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		return digitado;

	}
	
}
