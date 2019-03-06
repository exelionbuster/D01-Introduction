package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.PositionRepository;

import domain.Position;



@Component
@Transactional
public class StringToPositionConverter  implements Converter<String, Position> {

	@Autowired
	PositionRepository positionRepository;

	@Override
	public Position convert(String text) {
		Position res;
		int id;
		try {
			if (StringUtils.isEmpty(text))
				res = null;
			else {
				id = Integer.valueOf(text);
				res = positionRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return res;
	}

}

