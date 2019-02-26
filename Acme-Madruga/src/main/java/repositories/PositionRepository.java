
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Position;
import domain.Procession;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {

}
