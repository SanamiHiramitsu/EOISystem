package jp.co.rspct.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
//＠EnableWebSecurityを指定することでSpring Securityの機能が有効化される
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	//パスワードエンコーダーの設定
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Override
	//CSSフォルダにあるファイルに対して、Spring Securityの処理を適用しないようにする
	public void configure(WebSecurity web)throws Exception{
		web.ignoring().antMatchers("/css/**");
	}
	
	@Override
	//認証に使用するユーザー情報をuserDetailsServiceを介してデータベースから受け取る
	protected void configure(AuthenticationManagerBuilder auth)throws Exception{
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
	//ログイン画面の表示
	protected void configure(HttpSecurity http)throws Exception{
		http.authorizeRequests()
			.antMatchers("/signup").permitAll()
			.anyRequest().authenticated().and()
			.formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll().and()
			.logout().permitAll();
	}
}