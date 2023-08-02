package jp.co.rspct.ms;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.rspct.ms.entitｙ.MSEntity;

@Repository

/**
 * MSRepositoryクラスは、DBにアクセスするクラスです。
 */
public interface MSRepository extends JpaRepository<MSEntity, String> {
	public List<MSEntity> findBynumber(String number);
	public List<MSEntity> findBycompany(String company);
	public List<MSEntity> findByacceptanceDate(String acceptanceDate);
}