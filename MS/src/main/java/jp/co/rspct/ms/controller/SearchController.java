package jp.co.rspct.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.co.rspct.ms.entity.MSEntity;
import jp.co.rspct.ms.repository.MSRepository;

@Controller
/**
* SearchControllerクラスは、登録したデータを得意先名と検収年月で検索するクラスです。
*/
public class SearchController{
	@Autowired
	MSRepository repository;
	
	/**
	* searchEstimateメソッドは、一覧から得意先名で検索をし、データを抽出するメソッドです。
	* @param company 得意先名が入っています。
	* @param mv ModelAndView型の引数です。
	* @return
	*/
	@RequestMapping("/esearch")
	public ModelAndView searchEstimate(@RequestParam("company")String company,ModelAndView mv){
		mv.setViewName("estimateList");
		List<MSEntity>list = repository.findBycompany(company);
		mv.addObject("list",list);
		return mv;
	}
	
	/**
	* searchFixメソッドは、一覧から得意先名で検索をし、データを抽出するメソッドです。
	* @param mv ModelAndView型の引数です。
	* @return
	*/
	@RequestMapping("/fix")
	public ModelAndView searchFix(ModelAndView mv){
		List<MSEntity>list = repository.findBydelIsNull();
		mv.addObject("list",list);
		mv.setViewName("fix");
		return mv;
	}
	
	/**
	* searchメソッドは、一覧から得意先名で検索をし、データを抽出するメソッドです。
	* @param company 得意先名が入っています。
	* @param mv ModelAndView型の引数です。
	* @return
	*/
	@RequestMapping("/search")
	public ModelAndView search(@RequestParam("company") String company, ModelAndView mv){
		mv.setViewName("fix");
		List<MSEntity>list = repository.findBycompany(company);
		mv.addObject("list",list);
		return mv;
	}
	
	/**
	* searchInvoiceメソッドは、一覧から得意先名で検索をし、データを抽出するメソッドです。
	* @param company 得意先名が入っています。
	* @param mv ModelAndView型の引数です。
	* @return
	*/
	@RequestMapping("/sdatasearch")
	public ModelAndView searchInvoice(@RequestParam("company")String company,ModelAndView mv){
		mv.setViewName("invoiceDataMake");
		List<MSEntity>list = repository.findBycompany(company);
		mv.addObject("list",list);
		return mv;
	}
	
	/**
	* searchAcceptanceMonthメソッドは、一覧から検収年月で検索をし、データを抽出するメソッドです。
	* @param acceptanceDate
	* @param mv
	* @return
	*/
	@RequestMapping("/ssearch")
	public ModelAndView searchAcceptanceMonth(@RequestParam("acceptanceDate")String acceptanceDate,ModelAndView mv){
		mv.setViewName("slist");
		List<MSEntity>list = repository.findByacceptanceDate(acceptanceDate);
		mv.addObject("list",list);
		return mv;
	}
}