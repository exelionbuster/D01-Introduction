package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.BrotherhoodRepository;
import security.UserAccountRepository;

@Service
@Transactional
public class BrotherhoodServices {

	public BrotherhoodServices() {
		super();
	}

	// Managed repository ------------------------------------------------------

	@Autowired
	private BrotherhoodRepository brotherhoodRepository;

	// Supporting services -----------------------------------------------------
	
	//CRUDs
	
	
}
