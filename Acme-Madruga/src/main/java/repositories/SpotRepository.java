
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Spot;

@Repository
public interface SpotRepository extends JpaRepository<Spot, Integer> {

}
