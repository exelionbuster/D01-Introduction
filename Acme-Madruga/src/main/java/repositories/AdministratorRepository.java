
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;
import domain.Brotherhood;
import domain.Member;
import domain.Procession;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

	//FindAdministratorByID
	@Query("select a from Administrator a where a.id = ?1")
	Administrator findById(String id);

	// Find Administrator by UserAccountId
	@Query("select a from Administrator a where a.userAccount.id = ?1")
	Administrator findByUserAccountId(int id);

	//Q1
	@Query("select avg(1.0*(select count(e.id) from Enrolment e where e.brotherhood=b.id AND e.position IS NOT NULL)), min(1.0*(select count(e.id) from Enrolment e where e.brotherhood=b.id AND e.position IS NOT NULL)), max(1.0*(select count(e.id) from Enrolment e where e.brotherhood=b.id AND e.position IS NOT NULL)), stddev(1.0*(select count(e.id) from Enrolment e where e.brotherhood=b.id AND e.position IS NOT NULL)) from Brotherhood b")
	Collection<Object[]> brotherhoodMembersStats();
	
	//Q2
	@Query("select b1 from Enrolment e join e.brotherhood b1 where b1.enrolments.size = (select max(b2.enrolments.size) from Brotherhood b2) and e.position is not null")
	Brotherhood largestBrotherhood();
	
	//Q3
	@Query("select b1 from Enrolment e join e.brotherhood b1 where b1.enrolments.size = (select min(b2.enrolments.size) from Brotherhood b2) and e.position is not null")
	Brotherhood smallestBrotherhood();
	
	//Q4
	@Query("select 1.0*(select count(r1) from Request r1 where r1.status = 'APPROVED')/count(r2), 1.0*(select count(r3) from Request r3 where r3.status = 'PENDING')/count(r2), 1.0*(select count(r4) from Request r4 where r4.status = 'REJECTED')/count(r2) from Request r2")
	Collection<Object[]> requestsRatios();
	
	//Q5.1
	@Query("select 1.0*(select count(r1) from Request r1 where r1.status = 'APPROVED')/count(r2) from Request r2")
	Double acceptedRequestsRatio();
	
	//Q5.2
	@Query("select 1.0*(select count(r1) from Request r1 where r1.status = 'PENDING')/count(r2) from Request r2")
	Double pendingRequestsRatio();
	
	//Q5.3
	@Query("select 1.0*(select count(r1) from Request r1 where r1.status = 'REJECTED')/count(r2) from Request r2")
	Double rejectedRequestsRatio();
	
	//Q6
	@Query("select p from Procession p where p.moment < ?1")
	Collection<Procession> next30DaysProcessions(String date);
	
	//Q7
	//TODO we should change our domain model
	@Query("select distinct(m1) from Request r1 join r1.member m1 where m1.id=r1.member and (select count(r2.id) from Request r2 join r2.member m2 where m2.id = r2.member and r2.status = 'APPROVED') >= 0.1*(select max(1.0*(select count(r3.id) from Request r3 join r3.member m3 where m3.id = r3.member and r3.status = 'APPROVED')) from Request r)")
	Collection<Member> perc10MembersWithAcceptedRequests();
	
	//Q8
	//TODO check
	@Query("select p, count(p) from Position p where p.id = ?1")
	Integer positionsHistograms(int id);
}
