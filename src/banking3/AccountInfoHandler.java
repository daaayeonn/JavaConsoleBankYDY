package banking3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountInfoHandler {
	private Account[] accountLists;
	private int numOfAccount;
	
	public AccountInfoHandler() {
		accountLists = new Account[50];
		numOfAccount = 0;
	}
	
	// 계좌 개설
	public void makeAccount(int choice) {
		Scanner scan = new Scanner(System.in);
		String iAccount, iName, iGrade;
		int iBalance, iInterest;
		
		System.out.println("\n                 ◇◇◇ 신규 계좌개설 ◇◇◇");
		System.out.println();
		System.out.println("---------------------- 계좌선택 ---------------------");
		System.out.print("1. 보통계좌  ");
		System.out.println("2. 신용신뢰계좌");
		System.out.println("--------------------------------------------------");
		System.out.print("메뉴를 선택해주세요: "); choice = scan.nextInt();
		System.out.println();
		
		System.out.print("계좌번호: "); iAccount = scan.next();
		System.out.print("고객이름: "); iName = scan.next();
		System.out.print("잔고: "); iBalance = scan.nextInt();
		
		// 보통계좌
		if (choice == 1) {
			System.out.print("기본이자: "); iInterest = scan.nextInt();
			
			accountLists[numOfAccount++] = new NormalAccount(
					iAccount, iName, iBalance, iInterest);
		}
		// 신용신뢰계좌
		else if (choice == 2) {
			System.out.print("기본이자: "); iInterest = scan.nextInt();
			System.out.print("신용등급(A, B, C): "); iGrade = scan.next();
			
			accountLists[numOfAccount++] = new HighCreditAccount(iAccount, 
					iName, iBalance, iInterest, iGrade);
		}
		
		System.out.println("\n계좌개설이 완료되었습니다.");	
		
	} // makeAccount end
	
	// 입금
	public void depositMoney() {
		Scanner scan = new Scanner(System.in);
		System.out.println("\n계좌번호와 입금할 금액을 입력하세요.");
		System.out.println();
		String iAccount;
		int deposit;
		boolean isFind = false;
		
		System.out.print("계좌번호: "); iAccount = scan.nextLine();
		for (int i = 0; i < numOfAccount; i++) {
			if(iAccount.equals(accountLists[i].account)) {
				isFind = true;
				// 문자로 입력 금지
				try {
					System.out.print("입금액: "); deposit = scan.nextInt();
					// 음수 금액은 입금 X
					if (deposit <= 0) {
						System.out.println("\n금액을 정확히 입력해주세요.");
						return;
					}
					// 500원 단위로만 입금 가능
					else if (deposit%500 != 0) {
						System.out.println("\n500원 단위로만 입금이 가능합니다.");
						return;
					}
					accountLists[i].calculate();
					accountLists[i].balance += deposit;
					
					System.out.println("\n입금이 완료되었습니다.");
				} // try 끝
				catch (InputMismatchException e) {
					System.out.println("\n잘못 입력하셨습니다. 숫자로 입력해주세요.");
				} // catch 끝
			}
		} // for문 끝
		
		if (isFind == false) {
			System.out.println("\n없는 계좌번호입니다.");
		}
	} // depositMoney 끝
	
	// 출금
	public void withdrawMoney() {
		Scanner scan = new Scanner(System.in);
		System.out.println("\n계좌번호와 출금할 금액을 입력하세요.");
		String iAccount;
		int withdraw;
		boolean isFind = false;
		
		System.out.print("\n계좌번호: "); iAccount = scan.nextLine();
		for (int i = 0; i < numOfAccount; i++) {
			if (iAccount.equals(accountLists[i].account)) {
				isFind = true;
				
				try {
					System.out.print("출금액: "); withdraw = scan.nextInt();
					// 잔액부족 시
					if (accountLists[i].balance < withdraw) {
						String choice;
						
						System.out.println("\n잔액이 부족합니다. 금액 전체를 출금할까요?");
						System.out.println("\ny or n");
						System.out.print("\n메뉴를 선택하세요: ");
						choice = scan.next();
						
						if (choice.equalsIgnoreCase("y")) {
							accountLists[i].balance -= accountLists[i].balance;
							System.out.println("\n출금이 완료되었습니다.");
							return;
						}
						else if (choice.equalsIgnoreCase("n")) {
							System.out.println("\n출금이 취소되었습니다.");
							return;
						}
					}
					
					// 음수 출금 X
					if (withdraw <= 0) {
						System.out.println("\n금액을 정확히 입력해주세요.");
						return;
					}
					else if (withdraw % 1000 != 0) {
						System.out.println("1000원 단위로만 출금이 가능합니다.");
						return;
					}
					accountLists[i].balance -= withdraw;
					
					System.out.println("\n출금이 완료되었습니다.");
					
				} // try 끝
				catch (InputMismatchException e) {
					System.out.println("\n잘못 입력하셨습니다. 숫자로 입력해주세요.");
				} // catch 끝
			}
		} // for문 끝
		
		if (isFind == false) {
			System.out.println("\n없는 계좌번호입니다.");
		}
	} // withdrawMoney 끝
	
	// 계좌 전체 정보 출력
	public void showAccInfo() {
		System.out.println("\n계좌 정보 출력");
		
		for (int i = 0; i < numOfAccount; i++) {
			accountLists[i].showAccInfo();
		}
		
		System.out.println("\n전체 계좌 정보 출력이 완료되었습니다.");
	}
}
