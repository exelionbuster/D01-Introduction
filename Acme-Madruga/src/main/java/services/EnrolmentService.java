
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.EnrolmentRepository;

@Service
@Transactional
public class EnrolmentService {

	@Autowired
	private EnrolmentRepository	enrolmentRepository;

}
