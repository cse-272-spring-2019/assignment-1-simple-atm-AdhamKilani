package testJavaFX;


public class AtmLogic {
	private long pin;
	private double balance=0;
	private byte i=0,j=0,k=0,max=0,check=0;
	
	public byte getCheck() {
		return check;
	}
	public byte getJ() {
		return j;
	}
	public byte getMax() {
		return max;
	}
	private String history[] = new String[5];
	
	public AtmLogic (short pin){
		this.pin=pin;
	}
	
	public void deposit (double amount){
			balance += amount;
	}
	public void withdraw (double amount) {
			balance -= amount;
	}
	public double balanceInquiry(){
		return balance;
	}
	public void addToHistory(String amount){
		if(i<=4){
			history[i]=amount;
			j=i;
			max=i;
			i++;
			check=1;
		} else {
			k=0;
			while(k<4){
				history[k]=history[k+1];
				k++;
			}
			history[k]=amount;
			i--;
			j=i;
		}
	}
	public String previous(){
		if (j > 0 && j <= max){
			--j;
		}
		return history[j];
	}
	public String next(){
		if (j >= 0 && j < max){
			++j;
		}
		return history[j];
	}
	public boolean validateMoney (String amount){
		if (!amount.matches("[0-9]+")){
			return false;
		}
		else {
			if (Double.parseDouble(amount) > 0){
				return true;
			}
			else 
				return false;
		}
	}
	public boolean validatePin (String password){
		if (!password.matches("[0-9]+")) {
		    return false;
		}
		else {
			if (Long.parseLong(password) == pin){
				return true;
			}else 
				return false;
		
		}
	}

	
}
