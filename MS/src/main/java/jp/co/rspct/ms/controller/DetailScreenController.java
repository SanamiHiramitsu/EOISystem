package jp.co.rspct.ms.controller;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.rspct.ms.entity.MSEntity;
import jp.co.rspct.ms.repository.MSRepository;

@Controller
/**
* DetailScreenControllerクラスは、登録したデータを元に、見積書詳細画面と請求書出力画面に遷移させるクラスです。
* @author 阿部陽平
*/
public class DetailScreenController{
	@Autowired
	MSRepository repository;
	
	/**
	* estimateDetailメソッドは、見積書詳細画面に遷移するメソッドです。
	* @param number 見積No.が入っています。
	* @param mv ModelAndView型の引数です。
	* @return
	*/
	@RequestMapping("/estimateDetail/{number}")
	public ModelAndView estimateDetail(@PathVariable String number,ModelAndView mv){
		return set(mv,number,"御見積書","estimateDetail");
	}
	
	/**
	* invoiceDetailメソッドは、請求書詳細画面に遷移するメソッドです。
	* @param number 見積No.が入っています。
	* @param mv ModelAndView型の引数です。
	* @return
	*/
	@RequestMapping("/invoiceDetail/{number}")
	public ModelAndView invoiceDetail(@PathVariable String number,ModelAndView mv){
		return set(mv,number,"invoiceDetail");
	}
	
	/**
	* setメソッドは、DBから見積Noを検索し、タイトルとテンプレートをModelAndViewにセットするメソッドです。(見積書詳細画面)
	* @param mv ModelAndView型の引数です。
	* @param number 見積No.が入っています。
	* @param title タイトルが入っています。
	* @param template データを保持するテンプレートです。
	* @return
	*/
	private ModelAndView set(ModelAndView mv,String number,String title,String template){
		Optional<MSEntity>estimateDetail = repository.findById(number);
		if(!estimateDetail.isPresent()){
			return new ModelAndView("redirect:/index");
		}
		mv.addObject("form",estimateDetail.get());
		mv.addObject("title",title);
		mv.setViewName(template);
		return mv;
	}
	
	/**
	* setメソッドは、DBから見積Noを検索し、タイトルとテンプレートをModelAndViewにセットするメソッドです。(請求書詳細画面)
	* @param mv ModelAndView型の引数です。
	* @param number 見積No.が入っています。
	* @param template データを保持するテンプレートです。
	* @return
	*/
	private ModelAndView set(ModelAndView mv,String number,String template){
		Optional<MSEntity>invoiceDetail = repository.findById(number);
		if(!invoiceDetail.isPresent()){
			return new ModelAndView("redirect:/index");
		}
		mv.addObject("form",invoiceDetail.get());
		mv.setViewName(template);
		return mv;
	}
}