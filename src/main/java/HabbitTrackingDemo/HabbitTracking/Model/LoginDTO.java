package HabbitTrackingDemo.HabbitTracking.Model;

public class LoginDTO {
private String email;
public LoginDTO(String email, String number, String password) {
	super();
	this.email = email;
	this.number = number;
	this.password = password;
}
private String number;
private String password;
public LoginDTO() {
	super();

}
@Override
public String toString() {
	return "LoginDTO [email=" + email + ", number=" + number + ", password=" + password + "]";
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getNumber() {
	return number;
}
public void setNumber(String number) {
	this.number = number;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}


}
