package com.tss.test;

import java.util.Scanner;

import com.tss.model.Game;
import com.tss.model.MoveStratergy;
import com.tss.model.Player;

public class BoardTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Name Of Player 1: ");
		String player_1 = scanner.nextLine();
		System.out.print("Enter Name Of Player 2: ");
		String player_2 = scanner.nextLine();
		while (true) {
			System.out.println("Want to Play (Yes/No): ");
			String choice = scanner.nextLine();

			if (choice.equalsIgnoreCase("yes")) {
				Player player1 = new Player(player_1, 'X', new MoveStratergy());
				Player player2 = new Player(player_2, 'O', new MoveStratergy());

				Game game = new Game(player1, player2);

				game.start();
			} else {
				System.out.println("Thank You For Playing !!");
				return;
			}

		}
	}

}