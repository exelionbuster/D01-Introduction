
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface FloatRepository extends JpaRepository<domain.Float, Integer> {
	
	@Query("select f from domain.Float f join f.brotherhood b where b.id = ?1")
	Collection<domain.Float> findAllFloatsByBrotherhood(int id);
	
}


