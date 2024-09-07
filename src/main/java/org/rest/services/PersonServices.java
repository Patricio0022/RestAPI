package org.rest.services;

import java.util.List;

import org.rest.data.vo.PersonVO;
import org.rest.exceptions.ResourceNotFoundException;
import org.rest.mapper.DozerMapp;
import org.rest.model.Person;
import org.rest.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServices {

	@Autowired
	PersonRepository repository;
	
    private static Logger logger = LoggerFactory.getLogger(PersonServices.class);
	
	
    public List<PersonVO> findAll() { //get reading n
		
		System.out.println("find all people");
		
		return DozerMapp.parseListObjects(repository.findAll(), PersonVO.class);
	}
    
	
	public PersonVO findById(Long id) { //get by Id
		
		var entity = repository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("not found Id") );
		
		return DozerMapp.parseObject(entity, PersonVO.class);
	}
	
	public PersonVO create(PersonVO person) { //create
			
		System.out.println("Done created new person");
		
				var entity = DozerMapp.parseObject(person, Person.class);
				var vo = DozerMapp.parseObject(repository.save(entity), PersonVO.class);
				return vo;
	}
	
	 public PersonVO update(PersonVO person) { //put
	        logger.info("Updating person with ID: " + person.getId());
	        
	        var entity = repository.findById(person.getId())
	            .orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));
	         
	        entity.setFirstName(person.getFirstName());
	        entity.setLastName(person.getLastName());
	        entity.setAdress(person.getAdress());
	        entity.setGender(person.getGender());
	        
	        var vo = DozerMapp.parseObject(repository.save(entity), PersonVO.class);
	       return vo;
	    }
	 
	 public void delete(Long id) { //delete
		    logger.info("Deleting person with ID: " + id);
		    
		    var entity = repository.findById(id)
		            .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		    repository.delete(entity);
		}


}
