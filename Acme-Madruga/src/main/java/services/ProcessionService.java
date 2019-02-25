
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ProcessionRepository;
import domain.Brotherhood;
import domain.Procession;

@Service
@Transactional
public class ProcessionService {

	//TODO procession service
	@Autowired
	private ProcessionRepository	processionRepository;

	@Autowired
	private BrotherhoodService		brotherhoodService;

	@Autowired
	private RequestService			requestService;


	public Procession create() {
		Procession result;
		result = new Procession();
		final Collection<domain.Float> floats = new ArrayList<domain.Float>();
		result.setFloats(floats);
		result.setDraft(true); // we suppose a procession is a draft when it is created
		// the moment is selected by the brotherhood
		Brotherhood principal;
		principal = this.brotherhoodService.findByPrincipal();
		Assert.notNull(principal);
		result.setBrotherhood(principal);
		return result;
	}

	public Procession findById(final int id) {
		Procession result;
		result = this.processionRepository.findOne(id);
		return result;
	}

	public Collection<Procession> findAll() {
		Collection<Procession> result;
		result = this.processionRepository.findAll();
		return result;
	}

	public Collection<Procession> findAllNotDraft() {
		Collection<Procession> result;
		result = this.processionRepository.findAllNotDraft();
		return result;
	}

	public Collection<Procession> findAllByBrotherhoodId(final int id) {
		Collection<Procession> result;
		result = this.processionRepository.findAllByBrotherhoodId(id);
		Assert.notNull(result);
		return result;
	}

	public Procession save(final Procession p) {
		Assert.notNull(p);
		final Brotherhood principal = this.brotherhoodService.findByPrincipal();
		Assert.notNull(principal);
		String ticker = this.createTicker(p);
		final Collection<Procession> all = this.findAll();
		//TODO Ticker uniqueness
		for (final Procession proc : all)
			if (proc.getTicker().equals(ticker))
				ticker = this.createTicker(p);
		Procession result;
		result = this.processionRepository.save(p);
		return result;
	}

	//TODO method findAllByProcessionId in requestService
	public void delete(final Procession p) {
		Assert.notNull(p);
		final Brotherhood principal = this.brotherhoodService.findByPrincipal();
		Assert.notNull(principal);
		//		final Collection<Request> requests;
		//		requests = this.requestService.findAllByProcessionId(p.getId());
		//		for (final Request r : requests)
		//			if (!r.getStatus().equals("APPROVED"))
		//				this.requestService.delete(r);
		this.processionRepository.delete(p);
	}

	private String createTicker(final Procession p) {
		String result;
		Integer anyo, mes, dia;
		int min, max;
		char c;
		final Date date = p.getMoment();
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		anyo = cal.get(Calendar.YEAR);
		mes = cal.get(Calendar.MONTH) + 1;
		dia = cal.get(Calendar.DAY_OF_MONTH);

		String anyoString = anyo.toString();
		String mesString = mes.toString();
		String diaString = dia.toString();

		//Add the 0 when >10 or <10
		anyoString = anyoString.substring(2, 4);
		if (mes < 10)
			mesString = "0" + mesString;
		if (dia < 10)
			diaString = "0" + diaString;

		result = anyoString + mesString + diaString + "-";

		min = 65;
		max = 90;

		for (int i = 0; i < 5; i++) {
			final int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
			c = (char) randomNum;
			result = result + c;
		}
		return result;
	}

}
