
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Float;

@Repository
public interface HolyFloatRepository extends JpaRepository<Float, Integer> {

}
