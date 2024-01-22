package banking2;

/*
계좌정보를 표현한 클래스로 NormalAccount, HighCreditAccount의 부모클래스가
된다.
*/

public class Account {
	String account;
	String name;
	int balance;
	
	public Account(String account, String name, int balance) {
		this.account = account;
		this.name = name;
		this.balance = balance;
	}
	
	public void showAccInfo() {
		System.out.println("-------");
		System.out.println("계좌번호: " + account);
		System.out.println("고객이름: " + name);
		System.out.println("잔고: " + balance);
	}
}

//보통예금계좌
class NormalAccount extends Account {
	double interest;
	
	public NormalAccount(String account, String name, int balance, 
			double interest) {
		super(account, name, balance);
		this.interest = interest;
	}
	
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("기본이자: " + Math.floor(interest) + "%");
		System.out.println("-------");
	}
}

class HighCreditAccount extends Account {
	double interest;
	String grade;

	public HighCreditAccount(String account, String name, int balance,
			double interest, String grade) {
		super(account, name, balance);
		this.interest = interest;
		this.grade = grade;
	}
	
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("기본이자: " + Math.floor(interest) + "%");
		System.out.println("신용등급: " + grade);
		System.out.println("-------");
	}
}