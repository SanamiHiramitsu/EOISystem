package jp.co.rspct.ms.controller;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//UserDetailsインターフェイスの実装クラス
public class UserDetailsImpl implements UserDetails{
	private static final long serialVersionUID = 1L;
	private String userid;
	private String password;
	private Collection<GrantedAuthority>authorities;
	private String username;
	
	public UserDetailsImpl(String userid,String password,Collection<GrantedAuthority>authorities, String username){
		this.userid = userid;
		this.password = password;
		this.authorities = authorities;
		this.username = username;
	}
	
	//権限リストを返す
	@Override
	public Collection<? extends GrantedAuthority>getAuthorities(){
		return authorities;
	}
	
	//パスワードを返す
	@Override
	public String getPassword(){
		return password;
	}
	
	//ユーザー名を返す
	public String getUserid(){
		return userid;
	}
	
	//ユーザー名を返す
	@Override
	public String getUsername(){
		return username;
	}
	
	//アカウントの有効期限の判定
	@Override
	public boolean isAccountNonExpired(){
		//常にtrueとする
		return true;
	}
	
	//アカウントのロック状態の判定
	@Override
	public boolean isAccountNonLocked(){
		//常にtrueとする
		return true;
	}
	
	//資格情報の有効期限の判定
	@Override
	public boolean isCredentialsNonExpired(){
		//常にtrueとする
		return true;
	}
	
	//有効なユーザーであるかの判定
	@Override
	public boolean isEnabled(){
		//常にtrueとする
		return true;
	}

}