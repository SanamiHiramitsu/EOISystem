package jp.co.rspct.ms.controller;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.co.rspct.ms.MSConstant;
import jp.co.rspct.ms.entity.MSEntity;
import jp.co.rspct.ms.repository.MSRepository;

@Controller
/**
* DeleteControllerクラスは、登録した見積・請求データを削除するクラスです。
* @author 阿部陽平
*/
public class DeleteController{
	@Autowired
	MSRepository repository;
	
	/**
	* setメソッドは、DBから見積Noを検索し、タイトルとテンプレートをModelAndViewにセットするメソッドです。
	* @param mv ModelAndView型の引数です。
	* @param number 見積No.が入っています。
	* @param title タイトルが入っています。
	* @param template データを保持するテンプレートです。
	* @return
	*/
	private ModelAndView set(ModelAndView mv,String number,String title,String template){
		Optional<MSEntity>estimateDetail = repository.findById(number);
		if(!estimateDetail.isPresent()){
			return new ModelAndView("redirect:/deleteList");
		}
		mv.addObject("form",estimateDetail.get());
		mv.addObject("title",title);
		mv.setViewName(template);
		return mv;
	}
	
	/**
	* delete/{number}メソッドは、削除確認画面に遷移するメソッドです。
	* @param number 見積No.が入っています。
	* @param mv ModelAndView型の引数です。
	* @return
	*/
	@RequestMapping("/delete/{number}")
	public ModelAndView delete(@PathVariable String number,ModelAndView mv){
		return set(mv,number,MSConstant.CONFIRM_DELETE,"write");
	}
	
	/**
	* completeDelete/{number}メソッドは、完全削除画面に遷移するメソッドです。
	* @param number 見積No.が入っています。
	* @param mv ModelAndView型の引数です。
	* @return
	*/
	@RequestMapping("/completeDelete/{number}")
	public ModelAndView completeDelete(@PathVariable String number,ModelAndView mv){
		return set(mv,number,MSConstant.CONFIRM_COMPLETEDELETE,"write");
	}
	
	/**
	* deleteDataメソッドは、削除確認画面で論理削除を可能とするメソッドです。
	* @param entity 入力されたデータを保持しています。
	* @param result エラーを返す処理です。
	* @param number 見積No.が入っています。
	* @param mv ModelAndView型の引数です。
	* @return
	*/
	@RequestMapping("/deleteData")
	@Transactional(readOnly = false)
	public ModelAndView deleteData(@ModelAttribute("form")
		@Validated MSEntity entity,BindingResult result,String number,ModelAndView mv){
			var estimateDetail = repository.getById(number);
			estimateDetail.setDel("1");
			return new ModelAndView("redirect:/deleteList");
	}
	
	/**
	* completeDeleteメソッドは、登録した見積・請求データを完全削除するメソッドです。
	* @param number 見積No.が入っています。
	* @return
	*/
	@RequestMapping("/completeDelete")
	@Transactional(readOnly = false)
	public ModelAndView remove(@RequestParam("number")String number){
		repository.deleteById(number);
		return new ModelAndView("redirect:/deleteList");
	}
}