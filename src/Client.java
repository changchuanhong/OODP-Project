import java.io.Serializable;

public class Client extends User implements Serializable {
	private String phoneNum;
	private String email;
<<<<<<< HEAD

	
	public Client(String username, String password, String phoneNum, String email, String name){
		super(username, password, name);
		this.setPhoneNum(phoneNum);
		this.setEmail(email);
		this.setType("client");
=======
	private int age;
	
	public Client(String username, String password, String phoneNum, String email, String firstName,String lastName, int age){
		super(username, password, firstName);
		this.setPhoneNum(phoneNum);
		this.setEmail(email);
		this.setAge(age);
		super.setType("client");
>>>>>>> 0f4b0763f46af422e2a2a70f7890be3391ead580
	}

	//<editor-fold desc="Getters">
	public String getPhoneNum() {
		return phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setPhoneNum(String phoneNum) {
		if (phoneNum.isEmpty())
			throw new IllegalArgumentException("Phonenumber cannot be empty");

        this.phoneNum = phoneNum;
	}

	public void setEmail(String email) {
		if (email.isEmpty())
			throw new IllegalArgumentException("Email cannot be empty");

        this.email = email;
	}
}
