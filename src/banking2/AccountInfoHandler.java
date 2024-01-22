package banking2;

import java.util.Scanner;

public class AccountInfoHandler {
	private Account[] accountLists;
	private NormalAccount[] normalAccountLists;
	private int numOfAccount;
	
	public AccountInfoHandler() {
		accountLists = new Account[50];
		numOfAccount = 0;
	}
	
	// 계좌 개설
	public void makeAccount(int choice) {
		Scanner scan = new Scanner(System.in);
		String iAccount, iName, iGrade;
		int iBalance;
		double iInterest;
		
		System.out.println("***신규 계좌개설***");
		System.out.println("-----계좌선택-----");
		System.out.println("1. 보통계좌");
		System.out.println("2. 신용신뢰계좌");
		System.out.print("메뉴를 선택해주세요: ");
			
		choice = scan.nextInt();
		
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

		System.out.println("계좌개설이 완료되었습니다.");	
		
	} // makeAccount end
	
	// 입금
	public void depositMoney() {
		Scanner scan = new Scanner(System.in);
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		String iAccount;
		int defosit;
		boolean isFind = false;
		
		System.out.print("계좌번호: "); iAccount = scan.nextLine();
		for (int i = 0; i < numOfAccount; i++) {
			if(iAccount.equals(accountLists[i].account)) {
				isFind = true;
				
				System.out.print("입금액: "); defosit = scan.nextInt();
				accountLists[i].balance += defosit;
				
				System.out.println("입금이 완료되었습니다.");
			}
			
			if (isFind == false) {
				System.out.println("***없는 계좌번호 입니다.***");
			}
		} // for문 끝
	} // defositMoney 끝
	
	// 출금
	public void withdrawMoney() {
		Scanner scan = new Scanner(System.in);
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		String iAccount;
		int withdraw;
		boolean isFind = false;
		
		System.out.print("계좌번호: "); iAccount = scan.nextLine();
		for (int i = 0; i < numOfAccount; i++) {
			if (iAccount.equals(accountLists[i].account)) {
				isFind = true;
				
				System.out.print("출금액: "); withdraw = scan.nextInt();
				accountLists[i].balance -= withdraw;
				
				System.out.println("출금이 완료되었습니다.");
			}
			
			if (isFind == false) {
				System.out.println("***없는 계좌번호 입니다.***");
			}
		} // for문 끝
	} // withdrawMoney 끝
	
	// 계좌 전체 정보 출력
	public void showAccInfo() {
		System.out.println("***계좌정보출력***");
		
		for (int i = 0; i < numOfAccount; i++) {
			accountLists[i].showAccInfo();
		}
		
		System.out.println("전체 계좌 정보 출력이 완료되었습니다.");
	}
}
