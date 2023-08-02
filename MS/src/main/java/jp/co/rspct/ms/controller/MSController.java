package jp.co.rspct.ms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
/**
* MSControllerクラスは、トップ画面と見積入力・更新・削除選択画面に遷移するクラスです。
*/
public class MSController{
	/**
	* indexメソッドは、トップ画面に遷移するメソッドです。
	* @param mv ModelAndView型の引数です。
	* @return
	*/
	@RequestMapping("/index")
	public ModelAndView index(ModelAndView mv){
		mv.setViewName("index");
		return mv;
	}
	
	/**
	* selectメソッドは、見積入力・更新・削除選択画面に遷移するメソッドです。
	* @param mv ModelAndView型の引数です。
	* @return
	*/
	@RequestMapping("/select")
	public ModelAndView select(ModelAndView mv){
		mv.setViewName("select");
		return mv;
	}
}