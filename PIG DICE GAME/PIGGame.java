package com.tss.basics.assignments;

import java.util.Scanner;
import java.util.Random;

public class PIGGame {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Random random = new Random();

		System.out.println("Welcome to the PIG Game");
		want_play(sc, random);

	}

	private static void want_play(Scanner sc, Random random) {
		int turn = 1;
		int total_sum = 0;
		while (turn <= 5) {
			boolean want_role = true;
			if (total_sum >= 20) {
				System.out.println("Total Score: " + total_sum);
				System.out.println("You Won at " + turn + " attempts");
				break;
			}
			System.out.println("Turn :" + turn);
			while (want_role) {
				if (total_sum >= 20) {
					break;
				}
				System.out.println("Enter 'roll' if you want to Role and 'hold' if you want to hold:");
				String choice = sc.next();

				if (choice.equalsIgnoreCase("roll")) {
					int die_value = random.nextInt(6) + 1;
					System.out.println("Die Value: " + die_value);
					
					if (die_value == 1) {
						System.out.println("You Got 1 On die So Turn Over");
						total_sum = 0;
						want_role = false;
						turn++;
					} else {
						total_sum += die_value;
					}
					System.out.println("Total Score:- "+total_sum);

				} else if (choice.equalsIgnoreCase("hold")) {
					System.out.println("Turn Over");
					want_role = false;
					turn++;

				} else {
					System.out.println("Enter Valid !!");
				}
			}
			System.out.println("----------------------------------------------------------------");

		}
		if(turn>5)
		{
		System.out.println("You are Running Out of Turns !!");
		System.out.println("Sorry, Better Luck Next Time !!");
		}
	}
}