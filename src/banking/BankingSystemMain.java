package banking;

import java.util.Scanner;

public class BankingSystemMain {
	
	public static void showMenu() {
		System.out.println("-----Menu-----");
		System.out.println("1. 계 좌 개 설");
		System.out.println("2. 입	  금");
		System.out.println("3. 출	  금");
		System.out.println("4. 계좌정보출력");
		System.out.println("5. 프로그램종료");
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		while (true) {
			showMenu();
		}
	}

}
