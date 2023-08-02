package jp.co.rspct.ms.controller;
import java.sql.Date;
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
*ListControllerクラスは、見積書と請求書を一覧で表示するクラスです。
*/
public class ListController{
	@Autowired
	MSRepository repository;
	
	/**
	* estimateListメソッドは、見積書データを一覧で表示するメソッドです。
	* @param mv ModelAndView型の引数です。
	* @return
	*/
	@RequestMapping("/estimateList")
	public ModelAndView estimateList(ModelAndView mv){
		List<MSEntity>list = repository.findBydelIsNull();
		mv.addObject("list",list);
		mv.setViewName("estimateList");
		return mv;
	}
	
	/**
	* sortメソッドは、登録したデータを並び替えるメソッドです。
	* @param mv ModelAndView型の引数です。
	* @return
	*/
	@RequestMapping("/sort")
	private ModelAndView searchList(@RequestParam("sort") String sort,
	@RequestParam("order")String order,ModelAndView mv){
		var list = sortKey(sort,order);
		mv.addObject("list",list);
		mv.setViewName("fix");
		return mv;
	}
	
	//並べ替え条件一覧
	private List<MSEntity>sortKey(String sort,String order){
		List<MSEntity>list = null;
		switch(sort){
			case "company":{
				list = order.equals("asc")?
				repository.findBydelIsNullOrderByCompanyAsc():repository.findBydelIsNullOrderByCompanyDesc();
				break;
			}
			case "endPeriod":{
				list = order.equals("asc")?
				repository.findBydelIsNullOrderByEndPeriodAsc():repository.findBydelIsNullOrderByEndPeriodDesc();
				break;
			}
			case "name":{
				list = order.equals("asc")?
				repository.findBydelIsNullOrderByNameAsc():repository.findBydelIsNullOrderByNameDesc();
			}
		}
		return list;
	}
	
	/**
	* invoiceDatamakeメソッドは、見積書データを一覧で表示するメソッドです。
	* @param mv ModelAndView型の引数です。
	* @return
	*/
	@RequestMapping("/invoiceDataMake")
	public ModelAndView invoiceDataMake(ModelAndView mv){
		long miliseconds = System.currentTimeMillis();
		Date nowDate = new Date(miliseconds);
		List<MSEntity>end = repository.findBydelIsNullAndEndPeriodGreaterThanEqualAndStartPeriodLessThanEqual(nowDate,nowDate);
		mv.addObject("list",end);
		mv.setViewName("invoiceDataMake");
		return mv;
	}
	
	/**
	* invoiceListメソッドは、請求書データを一覧で標示するメソッドです。
	* @param mv ModelAndView型の引数です。
	* @return
	*/
	@RequestMapping("/invoiceList")
	public ModelAndView invoiceList(ModelAndView mv){
		List<MSEntity>list = repository.findByAcceptanceDateNotNullAndDelIsNull();
		mv.addObject("list",list);
		mv.setViewName("invoiceList");
		return mv;
	}
	
	/**
	* deliteListは、見積書データを一覧で表示するメソッドです。
	* @param mv ModelAndView型の引数です。
	* @return
	*/
	@RequestMapping("/deleteList")
	public ModelAndView deliteList(ModelAndView mv){
		List<MSEntity>list = repository.findBydelIsNull();
		mv.addObject("list",list);
		mv.setViewName("deleteList");
		return mv;
	}
	
	/**
	* restoreListメソッドは、削除された見積書データを一覧で表示するメソッドです。
	* @param mv ModelAndView型の引数です。
	* @return
	*/
	@RequestMapping("/restoreList")
	public ModelAndView restoreList(ModelAndView mv){
		List<MSEntity>list = repository.findBydel("1");
		mv.addObject("list",list);
		mv.setViewName("restoreList");
		return mv;
	}
}