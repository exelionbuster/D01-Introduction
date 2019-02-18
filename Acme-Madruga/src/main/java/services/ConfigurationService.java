
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ConfigurationRepository;

@Service
@Transactional
public class ConfigurationService {

	//TODO configuration service
	@Autowired
	private ConfigurationRepository	configurationRepository;

}
