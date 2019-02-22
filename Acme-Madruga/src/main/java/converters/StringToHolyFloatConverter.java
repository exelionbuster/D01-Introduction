
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.FloatRepository;
import domain.HolyFloat;

@Component
@Transactional
public class StringToHolyFloatConverter implements Converter<String, HolyFloat> {

	@Autowired
	FloatRepository	holyFloatRepository;


	@Override
	public HolyFloat convert(final String text) {
		HolyFloat result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.holyFloatRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
