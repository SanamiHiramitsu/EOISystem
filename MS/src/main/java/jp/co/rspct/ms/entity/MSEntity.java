package jp.co.rspct.ms.entity;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "MS")

//注文書追加時にフェーズ4のテーブル設計書に合わせて修正予定
public class MSEntity{
	/**
	* MSEntityクラスは、リレーショナルデータベースのテーブルを表現するためのクラス
	*/
	//見積書No
	@Id 
	@Column(length = 7,nullable = false)//フィールドに割り当てられる列名の指定
	@Size(min = 7,max = 7,message = "7文字で入力してください")
	private String number;
	
	//枝番（連番）
	@Column(length = 3,nullable = true)
	private Integer mBranchNumber = 1;
	
	//枝番（連番）
	@Column(length = 3,nullable = true)
	@Pattern(regexp = "[0-9]*",message = "正の整数を入力してください")
	private String sBranchNumber = "01";
	
	//見積書作成者
	@Column(length = 20,nullable = true)
	@Size(max = 20,message = "20文字以内で入力してください")
	private String mauthor;
	
	//請求書作成者
	@Column(length = 20,nullable = true)
	@Size(max = 20,message = "20文字以内で入力してください")
	private String sauthor;
	
	//見積書の作成日
	@Column(length = 10,nullable = true)
	@NotBlank(message = "")
	private String mDate;
	
	//見積書の作成日(年のみ)
	@Column(insertable = false,updatable = false)
	private String mDateYear;
	
	//見積書の作成日(月のみ)
	@Column(insertable = false,updatable = false)
	private String mDateMonth;
	
	//見積書の作成日の(月のみ/先頭の0を削除)
	@Column(insertable = false,updatable = false)
	private String correctmDateMonth;
	
	//見積書の作成日(日のみ)
	@Column(insertable = false,updatable = false)
	private String mDateDay;
	
	//見積書の作成日(日のみ/先頭の0を削除）
	@Column(insertable = false,updatable = false)
	private String correctmDateDay;
	
	//元受け会社名
	@Column(length = 25,nullable = false)
	@NotBlank(message = "")
	@Size(max = 25,message = "25文字以内で入力してください")
	private String company;
	
	//プロジェクト名
	@Column(length = 25,nullable = false)
	@NotBlank(message = "")
	@Size(max = 25, message = "25文字以内で入力してください")
	private String project;
	
	//作業者名
	@Column(length = 20,nullable = false)
	@NotBlank(message = "")
	@Size(max = 20,message = "20文字以内で入力してください")
	private String name;
	
	//期間開始日の列
	@Column(length = 10,nullable = false)
	private Date startPeriod;
	
	//期間開始日(年のみ)
	@Column(insertable = false,updatable = false)
	private String startPeriodYear;
	
	//期間開始日(月のみ)
	@Column(insertable = false,updatable = false)
	private String startPeriodMonth;
	
	//期間開始日(月のみ/先頭の0を削除）
	@Column(insertable = false,updatable = false)
	private String correctSPM;
	
	//期間開始日(日のみ)
	@Column(insertable = false,updatable = false)
	private String startPeriodDay;
	
	//期間開始日(日のみ/先頭の0を削除）
	@Column(insertable = false,updatable = false)
	private String correctSPD;
	
	//期間開始日(年月のみ)
	@Column(insertable = false,updatable = false)
	private String startPeriodNullDay;
	
	//期間開始日(yyyy-mm-01)
	@Column(insertable = false,updatable = false)
	private String startPeriodDay01;
	
	//期間開始月の最終日
	@Column(insertable = false,updatable = false)
	private String startPeriodLastday;
	
	//期間終了日
	@Column(length = 10,nullable = false)
	private Date endPeriod;
	
	//期間終了日(年のみ)
	@Column(insertable = false,updatable = false)
	private String endPeriodYear;
	
	//期間終了日(月のみ)
	@Column(insertable = false,updatable = false)
	private String endPeriodMonth;
	
	//期間終了日(月のみ/先頭の0を削除）
	@Column(insertable = false,updatable = false)
	private String correctEPM;
	
	//期間終了日(日のみ)
	@Column(insertable = false,updatable = false)
	private String endPeriodDay;
	
	//期間終了日(日のみ/先頭の0を削除）
	@Column(insertable = false,updatable = false)
	private String correctEPD;
	
	//期間終了日(年月のみ)
	@Column(insertable = false,updatable = false)
	private String endPeriodNullDay;
	
	//単価
	@Column(length = 10,nullable = false)
	@Pattern(regexp = "[0-9,]*",message = "正の整数で入力してください")
	@Size(max = 10,message = "99,999,999以下で入力してください")
	private String value;
	
	//単価(カンマなし)
	@Column(insertable = false,updatable = false)
	private Integer intValue;
	
	//数量
	@Column(insertable = false,updatable = false)
	private long countMonth;
	
	//検収年月
	@Column(nullable = true)
	private String acceptanceDate;
	
	//検収年月(年のみ)
	@Column(insertable = false,updatable = false)
	private String acceptanceDateYear;
	
	//検収年月(月のみ)
	@Column(insertable = false,updatable = false)
	private String acceptanceDateMonth;
	
	//検収年月(月のみ/先頭の0を削除）
	@Column(insertable = false,updatable = false)
	private String correctADM;
	
	//検収年月(yyyy-mm-01)
	@Column(insertable = false,updatable = false)
	private String acceptanceDate01;
	
	//検収年月の最終日
	@Column(insertable = false,updatable = false)
	private String acceptanceDateLastday;
	
	//お支払日(翌月末)
	@Column(insertable = false,updatable = false)
	private String endOfMonth;
	
	//お支払日(翌々月末)
	@Column(insertable = false,updatable = false)
	private String endOfNextMonth;
	
	//お支払日(年のみ/翌月末)
	@Column(insertable = false,updatable = false)
	private String endOfMonthYear;
	
	//お支払日(年のみ/翌々月)
	@Column(insertable = false,updatable = false)
	private String endOfNextMonthYear;
	
	//お支払日(月のみ/翌月末月)
	@Column(insertable = false,updatable = false)
	private String endOfMonthMonth;
	
	//お支払日(月のみ/先頭の0を削除）
	@Column(insertable = false,updatable = false)
	private String correctEMM;
	
	//お支払日(月のみ/翌々月)
	@Column(insertable = false,updatable = false)
	private String endOfNextMonthMonth;
	
	//お支払日の次月(月のみ/先頭の0を削除）
	@Column(insertable = false,updatable = false)
	private String correctENMM;
	
	//お支払日の(日のみ/翌月末日)
	@Column(insertable = false,updatable = false)
	private String endOfMonthDay;
	
	//お支払日(日のみ/翌々月 5日)
	@Column(insertable = false,updatable = false)
	private String endOfNextMonthDay5;
	
	//お支払日(日のみ/翌々月 10日)
	@Column(insertable = false,updatable = false)
	private String endOfNextMonthDay10;
	
	//お支払日(日のみ/翌々月 15日)
	@Column(insertable = false,updatable = false)
	private String endOfNextMonthDay15;
	
	//実働日数
	@Column(nullable = false)
	private Integer workingDay;
	
	//稼働日数
	@Column(nullable = true)
	private Integer businessDay;
	
	//お支払い条件
	@Column(nullable = true)
	private String payment = "月末締め翌月末日";
	
	//検収年月の最終日(日)
	@Column(insertable = false,updatable = false)
	private String acceptanceDateLastdayDay;
	
	//基準時間
	@Column(nullable = true)
	private String referenceTime;
	
	//月基準時間(上限)
	@Column(nullable = true)
	private Integer upperLimitTime;
	
	//月基準時間(下限)
	@Column(nullable = true)
	private Integer lowerLimitTime;
	
	//中割
	@Column(nullable = true)
	private String middleDiscount;
	
	//実働時間
	@Column(nullable = true)
	private Double actualWorkingHours;
	
	//お支払い条件(翌月or翌々月)
	@Column(nullable = true)
	private String paymentMonth;
	
	//お支払い条件(その他の日)
	@Column(nullable = true)
	private String paymentDay;
	
	//お支払日(日のみ/翌々月末)
	@Column(insertable = false,updatable = false)
	private String endOfNextMonthDay;
	
	//削除フラグ
	@Column(nullable = true)
	private String del;
	
	//切り捨て単位
	@Column(nullable = true)
	private String unit;
	
	//単価1000円未満切り捨て
	@Column(nullable = true)
	private String thousandCuts;
	
	//各列のgetterとsetter
	public String getNumber(){
		return number;
	}
	public void setNumber(String number){
		this.number = number;
	}
	
	public Integer getmBranchNumber(){
		return mBranchNumber;
	}
	public void setmBranchNumber(Integer mBranchNumber){
		this.mBranchNumber = mBranchNumber;
	}
	
	public String getsBranchNumber(){
		return sBranchNumber;
	}
	public void setsBranchNumber(String sBranchNumber){
		this.sBranchNumber = sBranchNumber;
	}
	
	public String getCompany(){
		return company;
	}
	public void setCompany(String company){
		this.company = company;
	}
	
	public String getProject(){
		return project;
	}
	public void setProject(String project){
		this.project = project;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public Date getStartPeriod(){
		return startPeriod;
	}
	public void setStartPeriod(Date startPeriod){
		this.startPeriod = startPeriod;
	}
	
	public String getStartPeriodYear(){
		return startPeriodYear;
	}
	public void setStartPeriodYear(String startPeriodYear){
		this.startPeriodYear = startPeriodYear;
	}
	
	public String getStartPeriodMonth(){
		return startPeriodMonth;
	}
	public void setStartPeriodMonth(String startPeriodMonth){
		this.startPeriodMonth = startPeriodMonth;
	}
	
	public String getCorrectSPM(){
		return correctSPM;
	}
	public void setCorrectSPM(String correctSPM){
		this.correctSPM = correctSPM;
	}
	
	public String getStartPeriodDay(){
		return startPeriodDay;
	}
	public void setStartPeriodDay(String startPeriodDay){
		this.startPeriodDay = startPeriodDay;
	}
	
	public String getCorrectSPD(){
		return correctSPD;
	}
	public void setCorrectSPD(String correctSPD){
		this.correctSPD = correctSPD;
	}
	
	public String getStartPeriodNullDay(){
		return startPeriodNullDay;
	}
	public void setStartPeriodNullDay(String startPeriodNullDay){
		this.startPeriodNullDay = startPeriodNullDay;
	}
	
	public String getStartPeriodDay01(){
		return startPeriodDay01;
	}
	public void setStartPeriodDay01(String startPeriodDay01){
		this.startPeriodDay01 = startPeriodDay01;
	}
	
	public String getStartPeriodLastday(){
		return startPeriodLastday;
	}
	public void setStartPeriodLastday(String startPeriodLastday){
		this.startPeriodLastday = startPeriodLastday;
	}
	
	public Date getEndPeriod(){
		return endPeriod;
	}
	public void setEndPeriod(Date endPeriod){
		this.endPeriod = endPeriod;
	}
	
	public String getEndPeriodYear(){
		return endPeriodYear;
	}
	public void setEndPeriodYear(String endPeriodYear){
		this.endPeriodYear = endPeriodYear;
	}
	
	public String getEndPeriodMonth(){
		return endPeriodMonth;
	}
	public void setEndPeriodMonth(String endPeriodMonth){
		this.endPeriodMonth = endPeriodMonth;
	}
	
	public String getCorrectEPM(){
		return correctEPM;
	}
	public void setCorrectEPM(String correctEPM){
		this.correctEPM = correctEPM;
	}
	
	public String getEndPeriodDay(){
		return endPeriodDay;
	}
	public void setEndPeriodDay(String endPeriodDay){
		this.endPeriodDay = endPeriodDay;
	}
	
	public String getCorrectEPD(){
		return correctEPD;
	}
	public void setCorrectEPD(String correctEPD){
		this.correctEPD = correctEPD;
	}
	
	public String getEndPeriodNullDay(){
		return endPeriodNullDay;
	}
	public void setEndPeriodNullDay(String endPeriodNullDay){
		this.endPeriodNullDay = endPeriodNullDay;
	}
	
	public String getValue(){
		return value;
	}
	public void setValue(String value){
		this.value = value;
	}
	
	public Integer getIntValue(){
		return intValue;
	}
	public void setIntValue(Integer intValue){
		this.intValue = intValue;
	}
	
	public long getCountMonth(){
		return countMonth;
	}
	public void setCountMonth(long countMonth){
		this.countMonth = countMonth;
	}
	
	public String getAcceptanceDate(){
		return acceptanceDate;
	}
	public void setAcceptanceDate(String acceptanceDate){
		this.acceptanceDate = acceptanceDate;
	}
	
	public String getAcceptanceDateYear(){
		return acceptanceDateYear;
	}
	public void setAcceptanceDateYear(String acceptanceDateYear){
		this.acceptanceDateYear = acceptanceDateYear;
	}
	
	public String getAcceptanceDateMonth(){
		return acceptanceDateMonth;
	}
	public void setAcceptanceDateMonth(String acceptanceDateMonth){
		this.acceptanceDateMonth = acceptanceDateMonth;
	}
	
	public String getCorrectADM(){
		return correctADM;
	}
	public void setCorrectADM(String correctADM){
		this.correctADM = correctADM;
	}
	
	public String getAcceptanceDate01(){
		return acceptanceDate01;
	}
	public void setAcceptanceDate01(String acceptanceDate01){
		this.acceptanceDate01 = acceptanceDate01;
	}
	
	public String getAcceptanceDateLastday(){
		return acceptanceDateLastday;
	}
	public void setAcceptanceDateLastday(String acceptanceDateLastday){
		this.acceptanceDateLastday = acceptanceDateLastday;
	}
	
	public String getEndOfMonth(){
		return endOfMonth;
	}
	public void setEndOfMonth(String endOfMonth){
		this.endOfMonth = endOfMonth;
	}
	
	public String getEndOfNextMonth(){
		return endOfNextMonth;
	}
	public void setEndOfNextMonth(String endOfNextMonth){
		this.endOfNextMonth = endOfNextMonth;
	}
	
	public String getEndOfMonthYear(){
		return endOfMonthYear;
	}
	public void setEndOfMonthYear(String endOfMonthYear){
		this.endOfMonthYear = endOfMonthYear;
	}
	
	public String getEndOfNextMonthYear(){
		return endOfNextMonthYear;
	}
	public void setEndOfNextMonthYear(String endOfNextMonthYear){
		this.endOfNextMonthYear = endOfNextMonthYear;
	}
	
	public String getEndOfMonthMonth(){
		return endOfMonthMonth;
	}
	public void setEndOfMonthMonth(String endOfMonthMonth){
		this.endOfMonthMonth = endOfMonthMonth;
	}
	
	public String getCorrectEMM(){
		return correctEMM;
	}
	public void setCorrectEMM(String correctEMM){
		this.correctEMM = correctEMM;
	}
	
	public String getEndOfNextMonthMonth(){
		return endOfNextMonthMonth;
	}
	public void setEndOfNextMonthMonth(String endOfNextMonthMonth){
		this.endOfNextMonthMonth = endOfNextMonthMonth;
	}
	
	public String getCorrectENMM(){
		return correctENMM;
	}
	public void setCorrectENMM(String correctENMM){
		this.correctENMM = correctENMM;
	}
	
	public String getEndOfMonthDay(){
		return endOfMonthDay;
	}
	public void setEndOfMonthDay(String endOfMonthDay){
		this.endOfMonthDay = endOfMonthDay;
	}
	
	public String getEndOfNextMonthDay5(){
		return endOfNextMonthDay5;
	}
	public void setEndOfNextMonthDay5(String endOfNextMonthDay5){
		this.endOfNextMonthDay5 = endOfNextMonthDay5;
	}
	
	public String getEndOfNextMonthDay10(){
		return endOfNextMonthDay10;
	}
	public void setEndOfNextMonthDay10(String endOfNextMonthDay10){
		this.endOfNextMonthDay10 = endOfNextMonthDay10;
	}
	
	public String getEndOfNextMonthDay15(){
		return endOfNextMonthDay15;
	}
	public void setEndOfNextMonthDay15(String endOfNextMonthDay15){
		this.endOfNextMonthDay15 = endOfNextMonthDay15;
	}
	
	public Integer getWorkingDay(){
		return workingDay;
	}
	public void setWorkingDay(Integer workingDay){
		this.workingDay = workingDay;
	}
	
	public Integer getBusinessDay(){
		return businessDay;
	}
	public void setBusinessDay(Integer businessDay){
		this.businessDay = businessDay;
	}
	
	public String getPayment(){
		return payment;
	}
	public void setPayment(String payment){
		this.payment = payment;
	}
	
	public String getmDate(){
		return mDate;
	}
	public void setmDate(String mDate){
		this.mDate = mDate;
	}
	
	public String getmDateYear(){
		return mDateYear;
	}
	public void setmDateYear(String mDateYear){
		this.mDateYear = mDateYear;
	}
	
	public String getmDateMonth(){
		return mDateMonth;
	}
	public void setmDateMonth(String mDateMonth){
		this.mDateMonth = mDateMonth;
	}
	
	public String getmDateDay(){
		return mDateDay;
	}
	public void setmDateDay(String mDateDay){
		this.mDateDay = mDateDay;
	}
	
	public String getAcceptanceDateLastdayDay(){
		return acceptanceDateLastdayDay;
	}
	public void setAcceptanceDateLastdayDay(String acceptanceDateLastdayDay){
		this.acceptanceDateLastdayDay = acceptanceDateLastdayDay;
	}
	
	public String getCorrectmDateMonth(){
		return correctmDateMonth;
	}
	public void setCorrectmDateMonth(String correctmDateMonth){
		this.correctmDateMonth = correctmDateMonth;
	}
	
	public String getCorrectmDateDay(){
		return correctmDateDay;
	}
	public void setCorrectmDateDay(String correctmDateDay){
		this.correctmDateDay = correctmDateDay;
	}
	
	public String getReferenceTime(){
		return referenceTime;
	}
	public void setReferenceTime(String referenceTime){
		this.referenceTime = referenceTime;
	}
	
	public Integer getUpperLimitTime(){
		return upperLimitTime;
	}
	public void setUpperLimitTime(Integer upperLimitTime){
		this.upperLimitTime = upperLimitTime;
	}
	
	public Integer getLowerLimitTime(){
		return lowerLimitTime;
	}
	public void setLowerLimitTime(Integer lowerLimitTime){
		this.lowerLimitTime = lowerLimitTime;
	}
	
	public String getMiddleDiscount(){
		return middleDiscount;
	}
	public void setMiddleDiscount(String middleDiscount){
		this.middleDiscount = middleDiscount;
	}
	
	public Double getActualWorkingHours(){
		return actualWorkingHours;
	}
	public void setActualWorkingHours(Double actualWorkingHours){
		this.actualWorkingHours = actualWorkingHours;
	}
	
	public String getPaymentMonth(){
		return paymentMonth;
	}
	public void setPaymentMonth(String paymentMonth){
		this.paymentMonth = paymentMonth;
	}
	
	public String getPaymentDay(){
		return paymentDay;
	}
	public void setPaymentDay(String paymentDay){
		this.paymentDay = paymentDay;
	}
	
	public String getMauthor(){
		return mauthor;
	}
	public void setMauthor(String mauthor){
		this.mauthor = mauthor;
	}
	
	public String getSauthor(){
		return sauthor;
	}
	public void setSauthor(String sauthor){
		this.sauthor = sauthor;
	}
	
	public String getEndOfNextMonthDay(){
		return endOfNextMonthDay;
	}
	public void setEndOfNextMonthDay(String endOfNextMonthDay){
		this.endOfNextMonthDay = endOfNextMonthDay;
	}
	
	public String getDel(){
		return del;
	}
	public void setDel(String del){
		this.del = del;
	}
	
	public String getUnit(){
		return unit;
	}
	public void setUnit(String unit){
		this.unit = unit;
	}
	
	public String getThousandCuts(){
		return thousandCuts;
	}
	public void setThousandCuts(String thousandCuts){
		this.thousandCuts = thousandCuts;
	}
}