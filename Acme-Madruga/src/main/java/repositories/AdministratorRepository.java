
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;
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

	// Q1 - The average, the minimum, the maximum, and the standard deviation of the number of members per brotherhood.
	@Query("select avg(1.0*(select count(e.id) from Enrolment e where e.brotherhood=b.id AND e.position IS NOT NULL)), min(1.0*(select count(e.id) from Enrolment e where e.brotherhood=b.id AND e.position IS NOT NULL)), max(1.0*(select count(e.id) from Enrolment e where e.brotherhood=b.id AND e.position IS NOT NULL)), stddev(1.0*(select count(e.id) from Enrolment e where e.brotherhood=b.id AND e.position IS NOT NULL)) from Brotherhood b")
	Collection<Object[]> brotherhoodMembersStats();

	// Q2 - The largest brotherhoods.
	@Query("select distinct(b), count(b) as s from Brotherhood b join b.enrolments e where e.position is not null group by b order by s DESC")
	Collection<Object[]> largestBrotherhood();

	// Q3 - The smallest brotherhoods.
	@Query("select distinct(b), count(b) as s from Brotherhood b join b.enrolments e where e.position is not null group by b order by s ASC")
	Collection<Object[]> smallestBrotherhood();

	// Q4 - The ratio of requests to march in a procession, grouped by their status.
	@Query("select p2, 1.0*(select count(r1) from Request r1 where r2.procession = r1.procession and r1.status = 'APPROVED')/count(r2), 1.0*(select count(r3) from Request r3 where r2.procession = r3.procession and r3.status = 'PENDING')/count(r2), 1.0*(select count(r4) from Request r4 where r2.procession = r4.procession and r4.status = 'REJECTED')/count(r2) from Request r2 join r2.procession p2 group by p2")
	Collection<Object[]> requestsRatiosPerProcession();

	// Q5 - The processions that are going to be organised in 30 days or less.
	@Query("select p from Procession p where p.draft = false and p.moment between CURRENT_DATE and CURRENT_DATE + 30")
	Collection<Procession> next30DaysProcessions();

	// Q6 - The ratio of requests to march grouped by status.
	@Query("select 1.0*(select count(r1) from Request r1 where r1.status = 'APPROVED')/count(r2), 1.0*(select count(r3) from Request r3 where r3.status = 'PENDING')/count(r2), 1.0*(select count(r4) from Request r4 where r4.status = 'REJECTED')/count(r2) from Request r2")
	Collection<Object[]> requestsRatios();

	// Q6.1 - The ratio of approved requests.
	@Query("select 1.0*(select count(r1) from Request r1 where r1.status = 'APPROVED')/count(r2) from Request r2")
	Double approvedRequestsRatio();

	// Q6.2 - The ratio of pending requests.
	@Query("select 1.0*(select count(r1) from Request r1 where r1.status = 'PENDING')/count(r2) from Request r2")
	Double pendingRequestsRatio();

	// Q6.3 - The ratio of rejected requests.
	@Query("select 1.0*(select count(r1) from Request r1 where r1.status = 'REJECTED')/count(r2) from Request r2")
	Double rejectedRequestsRatio();

	// Q7 - The listing of members who have got at least 10% the maximum number of request to march accepted.
	@Query("select distinct(m) from Member m join m.requests r where (select count(m1) from Request r join r.member m1 where m.id = m1.id and r.status = 'APPROVED' group by m) > 0.1*(select count(m1) from Request r join r.member m1 where m.id = m1.id group by m)")
	Collection<Member> perc10MembersWithAcceptedRequests();

	// Q8 - A histogram of positions.
	@Query("select p, p.enrolments.size from Position p")
	Collection<Object[]> positionsHistograms();
}
