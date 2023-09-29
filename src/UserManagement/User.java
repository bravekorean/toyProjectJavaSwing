package UserManagement;

public class User {

	public int getUsercode() {
		return usercode;
	}
	public void setUsercode(int usercode) {
		this.usercode = usercode;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpass() {
		return userpass;
	}
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpart() {
		return userpart;
	}
	public void setUserpart(String userpart) {
		this.userpart = userpart;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public User( String userid, String userpass, String username, String userpart, String role) {
		this.userid = userid;
		this.userpass = userpass;
		this.username = username;
		this.userpart = userpart;
		this.role = role;
	}
	public User( int usercode,String userid, String userpass, String username, String userpart, String role) {
		this.usercode = usercode;
		this.userid = userid;
		this.userpass = userpass;
		this.username = username;
		this.userpart = userpart;
		this.role = role;
	}
	
	private int usercode;
	private String userid;
	private String userpass;
	private String username;
	private String userpart;
	private String role;
	
	
}
