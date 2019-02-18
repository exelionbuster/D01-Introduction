
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ProcessionRepository;

@Service
@Transactional
public class ProcessionService {

	//TODO procession service
	@Autowired
	private ProcessionRepository	processionRepository;

}
