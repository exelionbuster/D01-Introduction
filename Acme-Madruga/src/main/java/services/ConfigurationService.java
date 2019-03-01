
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ConfigurationRepository;
import domain.Configuration;

@Service
@Transactional
public class ConfigurationService {

	//TODO configuration service
	@Autowired
	private ConfigurationRepository	configurationRepository;


	// Constructor
	public ConfigurationService() {
		super();
	}

	public Collection<Configuration> findAll() {

		Collection<Configuration> res = null;
		res = this.configurationRepository.findAll();

		return res;
	}

}
