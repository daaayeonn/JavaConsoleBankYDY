package banking;

import java.util.Scanner;

public class BankingSystemMain {
	
	public static void showMenu() {
		System.out.println("-----Menu-----");
		System.out.println("1. 계좌개설");
		System.out.println("2. 입금");
		System.out.println("3. 출금");
		System.out.println("4. 계좌정보출력");
		System.out.println("5. 프로그램종료");
		System.out.print("메뉴를 선택하세요: ");
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		AccountInfoHandler handler = new AccountInfoHandler();

		while(true) {
			showMenu();
			
			int choice = scan.nextInt();
			
			switch(choice) {
			case ICustomDefine.MAKE:
				handler.makeAccount();
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
				 System.out.println("프로그램 종료");
				return;
			} // switch 끝
		} // while 끝
	} // main 끝

}
