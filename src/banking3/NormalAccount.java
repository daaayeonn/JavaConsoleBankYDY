package banking3;

public class NormalAccount  extends Account {
	int interest;
	
	public NormalAccount(String account, String name, int balance, 
			int interest) {
		super(account, name, balance);
		this.interest = interest;
	}
	
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("기본이자: " + Math.floor(interest) + "%");
		System.out.println("--------------------------------------------------\n");
	}
	
	@Override
	public void calculate() {
		balance += balance* (interest * 0.01);
	}
}
