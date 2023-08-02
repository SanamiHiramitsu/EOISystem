package jp.co.rspct.ms.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rspct.ms.entity.SignupForm;

@Controller
/**
* LoginControllerクラスは、ログイン画面へ遷移の処理を行なうクラスです。
*/
@RequestMapping("/")
public class LoginController{
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@GetMapping
	public String index(@AuthenticationPrincipal UserDetailsImpl userDetails){
		System.out.println(userDetails.getUserid());  // ユーザーIDを表示
		System.out.println(userDetails.getUsername());  // ユーザー名を表示
		System.out.println(userDetails.getPassword());  // パスワードを表示
		System.out.println(userDetails.getAuthorities().toString());  // 権限情報を表示
		return "index";
	}
	
	//loginにアクセスした場合、login.htmlを表示する
	@GetMapping("/login")
	public String login(){
		return "login";
	}
	
	@GetMapping("/signup")
	public String newSignup(SignupForm signupForm){
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signup(@Validated SignupForm signupForm,BindingResult result,Model model,
	HttpServletRequest request){
		if(result.hasErrors()){
			return "signup";
		}
		
		try{
			userDetailsServiceImpl.register(signupForm.getUserid(),signupForm.getPassword(),"ROLE_USER",signupForm.getUsername());
		}catch(DataAccessException e){
			model.addAttribute("signupError","ユーザー登録に失敗しました");
			return "signup";
		}
		
		//自動的にログインさせる処理を行う前に、
		//既にログインしているユーザーをログアウトさせる処理
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		
		if(authentication instanceof AnonymousAuthenticationToken == false){
			SecurityContextHolder.clearContext();
		}
		
		//ログイン処理
		try{
			request.login(signupForm.getUserid(),signupForm.getPassword());
		}catch (ServletException e){
			e.printStackTrace();
		}
		return "redirect:/";
	}
}