package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Map<String, Integer> candidates = new HashMap<>();

		try {
			System.out.println("Entre com caminho do arquivo contendo os votos: ");
			String path = input.nextLine();
			try (BufferedReader br = new BufferedReader(new FileReader(path))) {
				String line = br.readLine();

				while (line != null) {
					String[] values = line.split(",");

					int votes = 0;
					if (candidates.containsKey(values[0])) {
						votes = candidates.get(values[0]); // Votes that the candidate already had
						votes += Integer.parseInt(values[1]); // More votes
						candidates.put(values[0], votes);
					} else {
						candidates.put(values[0], Integer.parseInt(values[1]));
					}

					line = br.readLine();
				}

				for (String key : candidates.keySet()) {
					System.out.println(key + ": " + candidates.get(key));
				}
			} catch (IOException e) {
				System.out.println("Erro: " + e.getMessage());
			}
		} catch (InputMismatchException e) {
			System.out.println("\nErro de formato. Foi inserido um valor inadequado para a operação.");
			System.out.println("Por favor, reinicie o programa e tenta novamente.");
		} finally {
			if (input != null)
				input.close();
		}
	}
}
