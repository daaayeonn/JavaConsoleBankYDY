package banking2;

public class HighCreditAccount extends Account {
	int interest;
	String grade;

	public HighCreditAccount(String account, String name, int balance,
			int interest, String grade) {
		super(account, name, balance);
		this.interest = interest;
		this.grade = grade;
	}
	
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("기본이자: " + interest + "%");
		System.out.println("신용등급: " + grade);
		System.out.println("--------------------------------------------------\n");
	}
	
	@Override
	public void calculate() {
		
		if(this.grade.equalsIgnoreCase("A")) {
			balance +=  (balance* (interest * 0.01)) + 
					(balance * (ICustomDefine.A * 0.01));
		}
		else if (this.grade.equalsIgnoreCase("B")) {
			balance +=  (balance* (interest * 0.01)) + 
					(balance * (ICustomDefine.B * 0.01));
		}
		else if (this.grade.equalsIgnoreCase("C")) {
			balance +=  (balance* (interest * 0.01)) + 
					(balance * (ICustomDefine.C * 0.01));
		}
	}
}
