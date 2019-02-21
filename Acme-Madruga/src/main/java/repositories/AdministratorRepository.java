
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

	//FindAdministratorByID
	@Query("select a from Administrator a where a.id = ?1")
	Administrator findById(String id);

	@Query("select b from Administrator a where a.userAccount.id = ?1")
	Administrator finByUserAccountId(int id);

}
