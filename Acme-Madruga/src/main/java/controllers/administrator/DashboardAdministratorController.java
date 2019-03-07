
package controllers.administrator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import controllers.AbstractController;
import domain.Brotherhood;
import domain.Member;
import domain.Position;
import domain.Procession;

@Controller
@RequestMapping("/administrator")
public class DashboardAdministratorController extends AbstractController {

	// Services
	@Autowired
	private AdministratorService	administratorService;


	public DashboardAdministratorController() {
		super();
	}

	@RequestMapping("/dashboard")
	public ModelAndView dashboard() {
		final ModelAndView result;

		// Q1
		final List<Object[]> brotherhoodMemberStats = new ArrayList<Object[]>(this.administratorService.brotherhoodMembersStats());
		final Double avgMembers = (Double) brotherhoodMemberStats.get(0)[0];
		final Integer minMembers = (Integer) brotherhoodMemberStats.get(0)[1];
		final Integer maxMembers = (Integer) brotherhoodMemberStats.get(0)[2];
		final Double stddevMembers = (Double) brotherhoodMemberStats.get(0)[3];

		//Q2
		final List<Object[]> largestBrotherhoodsAux = new ArrayList<Object[]>(this.administratorService.largestBrotherhood());
		final List<Brotherhood> largestBrotherhoods = new ArrayList<>();
		final List<Integer> largestBrotherhoodsMembers = new ArrayList<>();
		for (int i = 0; i < largestBrotherhoodsAux.size(); i++) {
			largestBrotherhoods.add(i, (Brotherhood) largestBrotherhoodsAux.get(i)[0]);
			largestBrotherhoodsMembers.add(i, (Integer) largestBrotherhoodsAux.get(i)[1]);
		}

		//Q3
		final List<Object[]> smallestBrotherhoodsAux = new ArrayList<Object[]>(this.administratorService.smallestBrotherhood());
		final List<Brotherhood> smallestBrotherhoods = new ArrayList<>();
		final List<Integer> smallestBrotherhoodsMembers = new ArrayList<>();
		for (int i = 0; i < smallestBrotherhoodsAux.size(); i++) {
			smallestBrotherhoods.add(i, (Brotherhood) smallestBrotherhoodsAux.get(i)[0]);
			smallestBrotherhoodsMembers.add(i, (Integer) smallestBrotherhoodsAux.get(i)[1]);
		}

		//Q4
		final List<Object[]> requestsPerProcessionAux = new ArrayList<Object[]>(this.administratorService.requestsRatiosPerProcession());
		final List<Procession> requestedPerProcession = new ArrayList<>();
		final List<Double> approvedRequestsPerProcession = new ArrayList<>();
		final List<Double> pendingRequestsPerProcession = new ArrayList<>();
		final List<Double> rejectedRequestsPerProcession = new ArrayList<>();
		for (int i = 0; i < requestsPerProcessionAux.size(); i++) {
			requestedPerProcession.add(i, (Procession) requestsPerProcessionAux.get(i)[0]);
			approvedRequestsPerProcession.add(i, (Double) requestsPerProcessionAux.get(i)[1]);
			pendingRequestsPerProcession.add(i, (Double) requestsPerProcessionAux.get(i)[2]);
			rejectedRequestsPerProcession.add(i, (Double) requestsPerProcessionAux.get(i)[3]);
		}

		//Q5
		final List<Procession> processionsNext30Days = new ArrayList<Procession>(this.administratorService.next30DaysProcessions());

		//Q6
		final List<Object[]> requestRatios = new ArrayList<Object[]>(this.administratorService.requestRatios());
		final Double acceptedRequests = (Double) requestRatios.get(0)[0];
		final Double pendingRequests = (Double) requestRatios.get(0)[1];
		final Double rejectedRequests = (Double) requestRatios.get(0)[2];

		//Q7
		final List<Member> perc10MembersWithAcceptedRequests = new ArrayList<>(this.administratorService.perc10MembersWithAcceptedRequests());

		//Q8
		final List<Object[]> histogramAux = new ArrayList<Object[]>(this.administratorService.positionsHistograms());
		final List<Position> positions = new ArrayList<Position>();
		final List<Integer> positionEnrolments = new ArrayList<Integer>();
		for (int i = 0; i < histogramAux.size(); i++) {
			positions.add(i, (Position) histogramAux.get(i)[0]);
			positionEnrolments.add(i, (Integer) histogramAux.get(i)[1]);
		}

		//Q1
		result = new ModelAndView("administrator/dashboard");

		//Q2
		result.addObject("avgMembers", avgMembers);
		result.addObject("minMembers", minMembers);
		result.addObject("maxMembers", maxMembers);
		result.addObject("stddevMembers", stddevMembers);

		//Q3
		result.addObject("largestBrotherhoods", largestBrotherhoods);
		result.addObject("largestBrotherhoodsMembers", largestBrotherhoodsMembers);

		//Q4
		result.addObject("smallestBrotherhoods", smallestBrotherhoods);
		result.addObject("smallestBrotherhoodsMembers", smallestBrotherhoodsMembers);

		//Q5
		result.addObject("processionRatiosPerProcession", requestedPerProcession);
		result.addObject("approvedPerProcession", approvedRequestsPerProcession);
		result.addObject("pendingPerProcession", pendingRequestsPerProcession);
		result.addObject("rejectedPerProcession", rejectedRequestsPerProcession);

		//Q6
		result.addObject("processionsNext30Days", processionsNext30Days);

		//Q7
		result.addObject("acceptedRequestsRatio", acceptedRequests);
		result.addObject("pendingRequestsRatio", pendingRequests);
		result.addObject("rejectedRequestsRatio", rejectedRequests);

		//Q8
		result.addObject("membersAcceptedRequests", perc10MembersWithAcceptedRequests);

		return result;
	}
}
