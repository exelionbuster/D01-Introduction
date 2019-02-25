
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Float;

@Component
@Transactional
public class HolyFloatToStringConverter implements Converter<Float, String> {

	@Override
	public String convert(final Float holyFloat) {
		String result;

		if (holyFloat == null)
			result = null;
		else
			result = String.valueOf(holyFloat.getId());
		return result;
	}

}
