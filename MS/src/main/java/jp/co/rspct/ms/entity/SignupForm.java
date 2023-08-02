package jp.co.rspct.ms.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//入力値を保持するためのフォームクラス
public class SignupForm{
	@NotBlank
	@Size(min = 1,max = 50)
	private String userid;
	
	@NotBlank
	@Size(min = 6,max = 20)
	private String password;
	
	@NotBlank
	@Size(min = 1,max = 50)
	private String username;
	
	public String getUserid(){
		return userid;
	}
	
	public void setUserid(String userid){
		this.userid = userid;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}

	public String getUsername(){
		return username;
	}

	public void setUsername(String username){
		this.username = username;
	}
}