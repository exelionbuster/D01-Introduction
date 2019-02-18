
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.RequestRepository;

@Service
@Transactional
public class RequestService {

	//TODO request service
	@Autowired
	private RequestRepository	requestRepository;

}
