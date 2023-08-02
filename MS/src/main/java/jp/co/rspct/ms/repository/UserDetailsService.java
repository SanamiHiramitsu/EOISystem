package jp.co.rspct.ms.repository;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService{
	//ユーザーIDを元にユーザー情報を取得
	UserDetails loadUserByUsername(String userid)throws UsernameNotFoundException;
}