package banking4;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
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
		
		/* 보통계좌, 신용신뢰계좌 공통적으로 입력바는 내용 */
		System.out.print("계좌번호: "); iAccount = scan.next();
		System.out.print("고객이름: "); iName = scan.next();
		System.out.print("잔고: "); iBalance = scan.nextInt();
		
		// 보통계좌
		/* 보통계좌 선택 시 이자를 추가적으로 입력받는다. */
		if (choice == 1) {
			System.out.print("기본이자: "); iInterest = scan.nextInt();
			
			NormalAccount normal = new NormalAccount(
					iAccount, iName, iBalance, iInterest);
			// 입력한 정보 저장
			accountSet.add(normal);
		}
		// 신용신뢰계좌
		/* 신용신뢰계좌 선택 시 이자와 신용등급을 추가적으로 입력받는다. */
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
					/* 음수 또는 0원 입력 시 금액을 정확히 적어 달라는 메세지를 띄워준다 */
					if (deposit <= 0) {
						System.out.println("\n금액을 정확히 입력해주세요.");
						return;
					}
					// 500원 단위로만 입금 가능
					else if (deposit%500 != 0) {
						System.out.println("\n500원 단위로만 입금이 가능합니다.");
						return;
					}
					// 이자게산
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
						
						/* y 입력 시 - 전체 출금 */
						if (choice.equalsIgnoreCase("y")) {
							acc.balance -= acc.balance;
							System.out.println("\n출금이 완료되었습니다.");
							return;
						}
						/* n 입력 시 - 출금 취소 */
						else if (choice.equalsIgnoreCase("n")) {
							System.out.println("\n출금이 취소되었습니다.");
							return;
						}
					} // if 끝
					
					// 음수 출금 X
					if (withdraw <= 0) {
						System.out.println("\n금액을 정확히 입력해주세요.");
						return;
					}
					// 1000원 단위로만 출금
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
		
		System.out.println("전체 계좌 정보 출력이 완료되었습니다.");
	} // showAccInfo 끝
	
	// 계좌정보삭제
	public void accDelete() {
		Scanner scan = new Scanner(System.in);
		String iAccount;
		boolean isFind = false;
		
		System.out.println("\n삭제할 계좌번호를 입력하세요.");
		System.out.print("\n계좌번호: "); iAccount = scan.next();
		
		Iterator<Account> itr = accountSet.iterator();
		
		// 저장된 인스턴스의 갯수(컬렉션 크기)만큼 반복
		while(itr.hasNext()) {
			Account acc = itr.next();
			// 인스턴스의 이름과 삭제할 이름을 비교
			if (iAccount.equals(acc.account)) {
				// 일치하면 삭제
				accountSet.remove(acc);
				isFind = true;
				
				System.out.println("\n입력하신 계좌번호가 삭제되었습니다.");
				
				break;
			} // if 끝
			
		} // while 끝
		
		if (isFind == false) {
			System.out.println("\n입력하신 계좌번호를 찾지 못했습니다.");
		}
	} // accDelete 끝
}
