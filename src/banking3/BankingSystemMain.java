package banking3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystemMain {
	
	public static void showMenu() {
		System.out.println("\n---------------------- Menu ----------------------");
		System.out.print("1. 계좌개설  ");
		System.out.print("2. 입금  ");
		System.out.print("3. 출금  ");
		System.out.print("4. 계좌정보출력  ");
		System.out.println("5. 프로그램종료");
		System.out.println("--------------------------------------------------");
		System.out.print("메뉴를 선택하세요: ");
	}

	public static void main(String[] args) throws MenuSelectException {
		Scanner scan = new Scanner(System.in);
		AccountInfoHandler handler = new AccountInfoHandler();

		try {
			while(true) {
				showMenu();
				
					int choice = scan.nextInt();
					if (choice > 5) {
					
					switch(choice) {
					case ICustomDefine.MAKE:
						handler.makeAccount(choice);
						break;
					case ICustomDefine.DEPOSIT:
						handler.depositMoney();
						break;
					case ICustomDefine.WITHDRAW:
						handler.withdrawMoney();
						break;
					case ICustomDefine.INQUIRE:
						handler.showAccInfo();
						break;
					case ICustomDefine.EXIT:
						System.out.println("\n             ◇◇◇ 프로그램이 종료되었습니다. ◇◇◇");
						return;
					} // switch 끝
					
					MenuSelectException ex = new MenuSelectException();
					throw ex;
				} // if 끝
			} // while 끝
		} // try 끝
		catch (InputMismatchException e) {
			System.err.println("\n※※ 잘못 입력하셨습니다. ※※");
		}
	} // main 끝

}
