package banking3;

/*
계좌정보를 표현한 클래스로 NormalAccount, HighCreditAccount의 부모클래스가
된다.
*/

abstract public class Account {
	String account;
	String name;
	int balance;
	
	public Account(String account, String name, int balance) {
		this.account = account;
		this.name = name;
		this.balance = balance;
	}
	
	public void showAccInfo() {
		System.out.println("--------------------------------------------------");
		System.out.println("계좌번호: " + account);
		System.out.println("고객이름: " + name);
		System.out.println("잔고: " + balance + "원");
	}
	
	// 이자 계산
	public void calculate() {}
}