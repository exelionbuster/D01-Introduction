
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.HolyFloat;

@Component
@Transactional
public class HolyFloatToStringConverter implements Converter<HolyFloat, String> {

	@Override
	public String convert(final HolyFloat holyFloat) {
		String result;

		if (holyFloat == null)
			result = null;
		else
			result = String.valueOf(holyFloat.getId());
		return result;
	}

}
