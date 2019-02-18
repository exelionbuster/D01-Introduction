
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.SpotRepository;

@Service
@Transactional
public class SpotService {

	//TODO spot service
	@Autowired
	private SpotRepository	spotRepository;

}
