package banking4;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountInfoHandler {
	HashSet<Account> accountSet;
	
	public AccountInfoHandler() {
		accountSet = new HashSet<Account>();
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
			
			NormalAccount normal = new NormalAccount(
					iAccount, iName, iBalance, iInterest);
			accountSet.add(normal);
		}
		// 신용신뢰계좌
		else if (choice == 2) {
			System.out.print("기본이자: "); iInterest = scan.nextInt();
			System.out.print("신용등급(A, B, C): "); iGrade = scan.next();
			
			HighCreditAccount high = new HighCreditAccount(iAccount, 
					iName, iBalance, iInterest, iGrade);
			accountSet.add(high);
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
		for (Account acc : accountSet) {
			if(iAccount.equals(acc.account)) {
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
					acc.calculate();
					acc.balance += deposit;
					
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
		for (Account acc : accountSet) {
			if (iAccount.equals(acc.account)) {
				isFind = true;
				
				try {
					System.out.print("출금액: "); withdraw = scan.nextInt();
					// 잔액부족 시
					if (acc.balance < withdraw) {
						String choice;
						
						System.out.println("\n잔액이 부족합니다. 금액 전체를 출금할까요?");
						System.out.println("\ny or n");
						System.out.print("\n메뉴를 선택하세요: ");
						choice = scan.next();
						
						if (choice.equalsIgnoreCase("y")) {
							acc.balance -= acc.balance;
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
					acc.balance -= withdraw;
					
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
		
		for (Account acc : accountSet) {
			acc.showAccInfo();
		}
		
		System.out.println("\n전체 계좌 정보 출력이 완료되었습니다.");
	}
	
	// 계좌정보삭제
	public void accDelete() {
		Scanner scan = new Scanner(System.in);
		String iAccount;
		
		System.out.println("\n삭제할 계좌번호를 입력하세요.");
		System.out.print("\n계좌번호: "); iAccount = scan.next();
		
		int deleteIndex = -1;
		for (Account acc : accountSet) {
			if(iAccount.compareTo(acc.account) == 0) {
				accountSet.remove(acc);
				deleteIndex = 1;
				break;
			}
		}
		
		if (deleteIndex == -1) {
			System.out.println("\n입력하신 계좌번호를 찾지 못했습니다.");
		}
		else {
			System.out.println("\n입력하신 정보가 삭제되었습니다.");
		}
	}
}
