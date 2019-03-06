package converters;

<<<<<<< HEAD


=======
>>>>>>> develop
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.PositionRepository;
<<<<<<< HEAD
import domain.Position;

@Component
@Transactional
public class StringToPositionConverter implements Converter<String, Position> {
=======

import domain.Position;



@Component
@Transactional
public class StringToPositionConverter  implements Converter<String, Position> {
>>>>>>> develop

	@Autowired
	PositionRepository positionRepository;

	@Override
	public Position convert(String text) {
<<<<<<< HEAD
		Position result;
		int id;
		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = positionRepository.findOne(id);
=======
		Position res;
		int id;
		try {
			if (StringUtils.isEmpty(text))
				res = null;
			else {
				id = Integer.valueOf(text);
				res = positionRepository.findOne(id);
>>>>>>> develop
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
<<<<<<< HEAD
		return result;
	}

}
=======
		return res;
	}

}

>>>>>>> develop
