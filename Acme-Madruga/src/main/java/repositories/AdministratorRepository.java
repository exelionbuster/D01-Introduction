
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


	@Query("select avg(1.0*(select count(e.id) from Enrolment e where e.brotherhood=b.id AND e.position IS NOT NULL)), min(1.0*(select count(e.id) from Enrolment e where e.brotherhood=b.id AND e.position IS NOT NULL)), max(1.0*(select count(e.id) from Enrolment e where e.brotherhood=b.id AND e.position IS NOT NULL)), stddev(1.0*(select count(e.id) from Enrolment e where e.brotherhood=b.id AND e.position IS NOT NULL)) from Brotherhood b")
	Collection<Object[]> brotherhoodMembersStats();

	//	@Query("select b1 from Brotherhood b1 where b1.id = (select b.id from Enrolment e1 join e1.brotherhood b group by e1.brotherhood having count(e1) = max(1.0*(select count(e2) from Enrolment e2 where e2.brotherhood=b.id AND e2.position IS NOT NULL)))")
	//	Brotherhood largestBrotherhood();

	//	@Query("select b from Enrolment e join e.brotherhood b where e.position IS NOT NULL and max(1.0*(select count(e1) from Enrolment e1 where e1.brotherhood=b.id AND e1.position IS NOT NULL)) = (select count(e2) from Enrolment e2 where e2.brotherhood=b.id AND e2.position IS NOT NULL)")
	//	Brotherhood largestBrotherhood();

	//	@Query("select min(1.0*(select count(e.id) from Enrolment e join e.brotherhood b where e.brotherhood=b.id AND e.position IS NOT NULL)) from Enrolment e group by e.brotherhood)")
	//	Brotherhood largestBrotherhood();

	@Query("select b1 from Enrolment e join e.brotherhood b1 where b1.enrolments.size = (select max(b2.enrolments.size) from Brotherhood b2) and e.position is not null")
	Brotherhood largestBrotherhood();

	@Query("select b1 from Enrolment e join e.brotherhood b1 where b1.enrolments.size = (select min(b2.enrolments.size) from Brotherhood b2) and e.position is not null")
	Brotherhood smallestBrotherhood();

	@Query("select 1.0*(select count(r1) from Request r1 where r1.status = 'APPROVED')/count(r2), 1.0*(select count(r3) from Request r3 where r3.status = 'PENDING')/count(r2), 1.0*(select count(r4) from Request r4 where r4.status = 'REJECTED')/count(r2) from Request r2")
	Collection<Object[]> requestsRatios();

	@Query("select 1.0*(select count(r1) from Request r1 where r1.status = 'APPROVED')/count(r2) from Request r2")
	Double acceptedRequestsRatio();

	@Query("select 1.0*(select count(r1) from Request r1 where r1.status = 'PENDING')/count(r2) from Request r2")
	Double pendingRequestsRatio();

	@Query("select 1.0*(select count(r1) from Request r1 where r1.status = 'REJECTED')/count(r2) from Request r2")
	Double rejectedRequestsRatio();

	@Query("select p from Procession p where p.moment < ?1")
	Collection<Procession> next30DaysProcessions(String date);

	//TODO we should change our domain model
	@Query("select distinct(m1) from Request r1 join r1.member m1 where m1.id=r1.member and (select count(r2.id) from Request r2 join r2.member m2 where m2.id = r2.member and r2.status = 'APPROVED') >= 0.1*(select max(1.0*(select count(r3.id) from Request r3 join r3.member m3 where m3.id = r3.member and r3.status = 'APPROVED')) from Request r)")
	Collection<Member> perc10MembersWithAcceptedRequests();

	//	@Query("select m from Request r join r.member m where r.status = 'APPROVED' and m.requests.size >= 0.1*(select max(s) from Request r2 join r.member m2 where r.status)")
	//	Collection<Member> perc10MembersWithAcceptedRequests();

	//	@Query("select m from Request r join r.member m where m.id = r.member and m.requests.size > (select max(m.requests.size) from Member m)*0.1 and r.status='ACCEPTED'")
	//	Collection<Member> perc10MembersWithAcceptedRequests();

	//TODO check
	@Query("select p, count(p) from Position p where p.id = ?1")
	Integer positionsHistograms(int id);
}
