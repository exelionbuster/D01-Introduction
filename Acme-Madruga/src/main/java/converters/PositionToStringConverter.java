package converters;

<<<<<<< HEAD


=======
>>>>>>> develop
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Position;

@Component
@Transactional
public class PositionToStringConverter implements Converter<Position, String> {

	@Override
<<<<<<< HEAD
	public String convert(Position position) {
		String result;

		if (position == null)
			result = null;
		else
			result = String.valueOf(position.getId());
		return result;
	}

}
=======
	public String convert(Position pos) {
		String res;

		if (pos == null)
			res = null;
		else
			res = String.valueOf(pos.getId());
		return res;
	}

}
>>>>>>> develop
