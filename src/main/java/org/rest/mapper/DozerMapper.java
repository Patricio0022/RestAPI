package org.rest.mapper;

import java.util.ArrayList;
import java.util.List;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerMapper {
	
	//conversao de entity for VO
	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	public  <O, D> D parseObject(O origin, Class<D> destination) {

		return mapper.map(origin, destination);
	}
	
	public  <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
		List<D> destinationObjects = new ArrayList<D>();
		for (O o : origin) {
			destinationObjects.add(mapper.map(o, destination));
		}
		return destinationObjects;
	}
	

}
