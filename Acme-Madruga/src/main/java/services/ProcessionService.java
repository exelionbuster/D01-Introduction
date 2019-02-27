
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ProcessionRepository;
import domain.Brotherhood;
import domain.Procession;
import domain.Request;

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
		final String ticker = ProcessionService.generateTicker(p);
		p.setTicker(ticker);
		//TODO Ticker uniqueness
		Procession result;
		result = this.processionRepository.save(p);
		return result;
	}

	public String delete(final Procession p) {
		String res;
		Assert.notNull(p);
		final Brotherhood principal = this.brotherhoodService.findByPrincipal();
		Assert.notNull(principal);
		final Collection<Request> requests;
		requests = this.requestService.findAllByProcessionId(p.getId());
		boolean pending = true;
		for (final Request r : requests)
			if (!r.getStatus().equals("PENDING")) {
				pending = false;
				break;
			}
		if (pending == true) {
			for (final Request r : requests)
				this.requestService.delete(r);
			this.processionRepository.delete(p);
			res = "delete.success";
		} else
			res = "delete.fail";
		return res;

	}

	protected static String createTickerDate(final Procession p) {
		String result;
		Integer year, month, day;

		final Date date = p.getMoment();
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;
		day = cal.get(Calendar.DAY_OF_MONTH);

		String yearString = year.toString();
		String monthString = month.toString();
		String dayString = day.toString();

		//Add the 0 when <10 and substring the year
		yearString = yearString.substring(2, 4);
		if (month < 10)
			monthString = "0" + monthString;
		if (day < 10)
			dayString = "0" + dayString;

		result = yearString + monthString + dayString;
		return result;
	}

	/*
	 * This method generates an alphanumeric code of 6 digits
	 */
	protected static String generateAlphaNumericCode() {

		String res = "";
		final String abecedario = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final String numeros = "0123456789";
		final String alfanumerico = abecedario + numeros;

		final int top_alfanumerico = alfanumerico.length() - 1;

		char c;

		for (int i = 0; i < 6; i++) {
			final int numeroAleatorio = (int) Math.floor(Math.random() * top_alfanumerico);
			c = alfanumerico.charAt(numeroAleatorio);
			res += c;
		}
		return res;
	}

	/*
	 * This is a method to generate a unique Ticker for the system,
	 * which is necessary for several objects in the system
	 */
	protected static String generateTicker(final Procession p) {

		String res = null;
		final String fechaActual = ProcessionService.createTickerDate(p);
		final String alphanumericCode = ProcessionService.generateAlphaNumericCode();
		res = fechaActual + "-" + alphanumericCode;

		return res;
	}

}
