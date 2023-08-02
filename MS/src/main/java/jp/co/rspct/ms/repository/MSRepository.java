package jp.co.rspct.ms.repository;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.rspct.ms.entity.MSEntity;

@Repository
/**
* MSRepositoryクラスは、DBにアクセスするクラスです。
*/
public interface MSRepository
extends JpaRepository<MSEntity,String>{
	//見積書枝番
	public List<MSEntity> findBymBranchNumber(Integer mBranchNumber);
	
	//見積書No
	public List<MSEntity>findBynumber(String number);
	
	//得意先名検索
	public List<MSEntity>findBycompany(String company);
	
	//検収年月検索
	public List<MSEntity>findByacceptanceDate(String acceptanceDate);
	
	//見積書一覧画面
	//見積書削除一覧画面
	public List<MSEntity>findBydelIsNull();
	
	//削除復元画面
	public List<MSEntity>findBydel(String del);
	
	//請求書作成一覧画面
	public List<MSEntity>findBydelIsNullAndEndPeriodGreaterThanEqualAndStartPeriodLessThanEqual(Date endPeriod,Date startPeriod);
	
	//請求書一覧画面
	public List<MSEntity>findByAcceptanceDateNotNullAndDelIsNull();
	
	//見積書一覧 取引先の昇順
	public List<MSEntity>findBydelIsNullOrderByCompanyAsc();
	
	//見積書一覧 取引先の降順
	public List<MSEntity>findBydelIsNullOrderByCompanyDesc();
	
	//見積書一覧 期間終了日の昇順
	public List<MSEntity>findBydelIsNullOrderByEndPeriodAsc();
	
	//見積書一覧 期間終了日の降順
	public List<MSEntity>findBydelIsNullOrderByEndPeriodDesc();
	
	//見積書一覧 作業者名の昇順
	public List<MSEntity>findBydelIsNullOrderByNameAsc();
	
	//見積書一覧 作業者名の降順
	public List<MSEntity>findBydelIsNullOrderByNameDesc();
}