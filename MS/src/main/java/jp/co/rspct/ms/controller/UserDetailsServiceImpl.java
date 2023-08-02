package jp.co.rspct.ms.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException{
		try{
			String sql = "SELECT * FROM user WHERE userid = ?";
			Map<String,Object> map = jdbcTemplate.queryForMap(sql,userid);
			String password = (String)map.get("password");
			String username = (String)map.get("username");
			Collection<GrantedAuthority>authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority((String)map.get("authority")));
			return new UserDetailsImpl(userid,password,authorities,username);
		}catch (Exception e){
			throw new UsernameNotFoundException("user not found.",e);
		}
	}
	
	@Transactional
	//ユーザー情報をデータベースに登録するregisterメソッド
	public void register(String userid,String password,String authority,String username){
		String sql = "INSERT INTO user(userid,password,authority,username)VALUES(?,?,?,?)";
		jdbcTemplate.update(sql,userid,passwordEncoder.encode(password),authority,username);
	}
	
	public boolean isExistUser(String userid){
		String sql = "SELECT COUNT(*) FROM user WHERE name = ?";
		int count = jdbcTemplate.queryForObject(sql,Integer.class,new Object[]{userid});
		if(count == 0){
			return false;
		}
		return true;
	}
}