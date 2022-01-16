package br.com.zappi.projeto;

import br.com.zappi.projeto.modelo.Tabuleiro;
import br.com.zappi.projeto.visao.TabuleiroConsole;

public class Aplicacao {

	public static void main(String[] args) {

		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 3);

		new TabuleiroConsole(tabuleiro);

	}

}
