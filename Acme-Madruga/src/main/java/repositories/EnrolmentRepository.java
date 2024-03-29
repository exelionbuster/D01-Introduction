
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Enrolment;


@Repository
public interface EnrolmentRepository extends JpaRepository<Enrolment, Integer> {
	
	@Query("select e from Enrolment e join e.brotherhood b where b.id = ?1")
	Collection<Enrolment> findAllEnrolmentsByBrotherhood(int id);

}
