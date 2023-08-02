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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.rspct.ms.MSConstant;
import jp.co.rspct.ms.entity.MSEntity;
import jp.co.rspct.ms.repository.MSRepository;

@Controller
/**
*InputControllerクラスは、見積データと請求データを入力し、DBに保存するクラスです。
*/
public class InputController{
	@Autowired
	MSRepository repository;
	
	/**
	* writeメソッドは、見積新規入力画面を表示し、見積データを入力するメソッドです。
	* @param mv ModelAndView型の引数です。
	* @return
	*/
	@RequestMapping(path = "write",method = RequestMethod.GET)
	public ModelAndView write(ModelAndView mv){
		mv.addObject("title",MSConstant.ESTIMATE_NO_INPUT);
		mv.addObject("form",new MSEntity());
		mv.setViewName("write");
		return mv;
	}
	
	/**
	* insertEstimateメソッドは、見積新規入力画面で入力されたデータをDBに保存するメソッドです。
	* @param entity 入力されたデータを保持しています。
	* @param result エラーを返す処理です。
	* @param number 見積No.が入っています。
	* @param mv ModelAndView型の引数です。
	* @return
	*/
	@RequestMapping("/insertEstimate")
	@Transactional(readOnly = false)
	public ModelAndView insertEstimate(@ModelAttribute("form")@Validated MSEntity entity,
	BindingResult result, String number,ModelAndView mv){
		if(result.hasErrors()){
			mv.setViewName("write");
			mv.addObject("title",repository.findById(number).isPresent()?
			MSConstant.ESTIMATE_NO_ALREADY_EXISTING:MSConstant.ESTIMATE_INPUT);
			return mv;
		}
		repository.saveAndFlush(entity);
		return new ModelAndView("redirect:/estimateWriteRegistration");
	}
	
	/**
	* insertFixメソッドは、見積書更新画面で入力されたデータをDBに保存するメソッドです。
	* @param entity 入力されたデータを保持しています。
	* @param result エラーを返す処理です。
	* @param number 見積No.が入っています。
	* @param mv ModelAndView型の引数です。
	* @return
	*/
	@RequestMapping("/insertFix")
	@Transactional(readOnly = false)
	public ModelAndView insertFix(@ModelAttribute("form")@Validated MSEntity entity,
	BindingResult result,String number,ModelAndView mv){
		if(result.hasErrors()){
			mv.setViewName("write");
			mv.addObject("title",MSConstant.ESTIMATE_UPDATE);
			return mv;
		}
		repository.saveAndFlush(entity);
		return new ModelAndView("redirect:/estimateFixRegistration");
	}
	
	/**
	* insertInvoiceメソッドは、請求データ入力画面で入力されたデータをDBに保存するメソッドです。
	* @param entity 入力されたデータを保持しています。
	* @param result エラーを返す処理です。
	* @param number 見積No.が入っています。
	* @param mv ModelAndView型の引数です。
	* @return
	*/
	@RequestMapping("/insertInvoice")
	@Transactional(readOnly = false)
	public ModelAndView insertInvoice(@ModelAttribute("form")@Validated MSEntity entity,
	BindingResult result,String number,ModelAndView mv){
		if(result.hasErrors()){
			mv.setViewName("write");
			mv.addObject("title",MSConstant.BILLING_MAKE_INPUT);
			return mv;
		}
		repository.saveAndFlush(entity);
		return new ModelAndView("redirect:/invoiceDataRegistration");
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
	* updateメソッドは、入力された見積データを更新するメソッドです。
	* @param number 見積No.が入っています。
	* @param mv ModelAndView型の引数です。
	* @return
	*/
	@RequestMapping("/update/{number}")
	public ModelAndView update(@PathVariable String number,ModelAndView mv){
		var estimateDetail = repository.getById(number);
		//更新画面に入る前に月単価切り捨ては削除する
		estimateDetail.setThousandCuts(null);
		return set(mv,number,MSConstant.BILLING_UPDATE_INPUT,"write");
	}
	
	/**
	* invoicedatamakeNotWorkingDayは、請求データ入力画面を表示し、請求データを入力するメソッドです。(実働日の入力フォームなし)
	* @param number 見積No.が入っています。
	* @param mv ModelAndView型の引数です。
	* @return
	*/
	@RequestMapping("/invoicedatamakeNotWorkingDay/{number}")
	public ModelAndView invoicedatamakeNotWorkingDay(@PathVariable String number,ModelAndView mv){
		return set(mv,number,MSConstant.BILLING_MAKE_INPUT,"write");
	}
	
	/**
	* invoicedatamakeWorkingDayは、請求データ入力画面を表示し、請求データを入力するメソッドです。(実働日の入力フォームあり)
	* @param number 見積No.が入っています。
	* @param mv ModelAndView型の引数です。
	* @return
	*/
	@RequestMapping("/invoicedatamakeWorkingDay/{number}")
	public ModelAndView invoicedatamakeWorkingDay(@PathVariable String number,ModelAndView mv){
		return set(mv,number,MSConstant.BILLING_MAKE_INPUT_WORKINGDAY,"write");
	}
	
	/**
	* estimateWriteRegistrationは、見積データ登録完了画面に遷移するメソッドです。
	* @param mv ModelAndView型の引数です。
	* @return
	*/
	@RequestMapping("/estimateWriteRegistration")
	public ModelAndView estimateWriteRegistration(ModelAndView mv){
		mv.setViewName("estimateWriteRegistration");
		return mv;
	}
	
	/**
	* estimateFixRegistrationメソッドは、見積データ更新完了画面に遷移するメソッドです。
	* @param mv ModelAndView型の引数です。
	* @return
	*/
	@RequestMapping("/estimateFixRegistration")
	public ModelAndView estimateFixRegistration(ModelAndView mv){
		mv.setViewName("estimateFixRegistration");
		return mv;
	}
	
	/**
	* invoiceDataRegistrationメソッドは、請求データ登録完了画面に遷移するメソッドです。
	* @param mv ModelAndView型の引数です。
	* @return
	*/
	@RequestMapping("/invoiceDataRegistration")
	public ModelAndView invoiceDataRegistration(ModelAndView mv){
		mv.setViewName("invoiceDataRegistration");
		return mv;
	}
	
	/**
	* restoreDataメソッドは、見積データのデータベース保存(復元)を行なうメソッドです。
	* @param number 見積No.が入っています。
	* @param mv ModelAndView型の引数です。
	* @return
	*/
	@RequestMapping("/restoreData")
	@Transactional(readOnly = false)
	public ModelAndView restoreData(@ModelAttribute("form")@Validated MSEntity entity,
	BindingResult result,String number,ModelAndView mv){
		var estimateDetail = repository.getById(number);
		estimateDetail.setDel(null);
		return new ModelAndView("redirect:/deleteList");
	}
	
	/**
	* restoreメソッドは、見積書を復元するメソッドです。
	* @param number 見積No.が入っています。
	* @param mv ModelAndView型の引数です。
	* @return
	*/
	@RequestMapping("/restore/{number}")
	public ModelAndView restore(@PathVariable String number,ModelAndView mv){
		return set(mv,number,MSConstant.CONFIRM_RESTORE,"write");
	}
}