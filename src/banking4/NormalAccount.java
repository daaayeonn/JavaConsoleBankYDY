package banking4;

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
		System.out.println("기본이자: " + (int)(interest) + "%");
		System.out.println("--------------------------------------------------\n");
	}
	
	@Override
	public void calculate() {
		balance += balance* (interest * 0.01);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		NormalAccount normalObj = (NormalAccount) obj;
		if (normalObj.account.equals(super.account)) {
			return true;
		}
		else {
			return false;
		}
	}
}
