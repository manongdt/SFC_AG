package main;

import java.util.Scanner;
import java.util.regex.Pattern;

import controller.ControllerTournoi;

public class Main {

	private static ControllerTournoi cT;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Pattern pattern = Pattern.compile("^[SC]$");
		String s = "";
		do {
			System.out
					.print("Choisissez le mode console ou graphique (C ou S): ");
			s = sc.nextLine();
		} while (!pattern.matcher(s).find());
		cT = new ControllerTournoi(s);
		cT.start(s);
		
		sc.close();
	}

}
