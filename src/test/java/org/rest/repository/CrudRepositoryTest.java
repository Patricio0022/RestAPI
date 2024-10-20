package org.rest.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rest.data.vo.PersonVO;
import org.rest.mapper.DozerMapper;
import org.rest.model.Person;
import org.rest.services.PersonServices;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class CrudRepositoryTest {

    @InjectMocks
    private PersonServices personServices; //real injection
    @Mock
    private PersonRepository personRepository; //behavior
    @Mock
    private DozerMapper dozerMapper; //behavior

    private Person entity, person2;
    private PersonVO personV1, personV2;

    @BeforeEach
    public void setUp() {

        entity = new Person();
        //id autoIncrement db = 1L
        entity.setFirstName("John");
        entity.setLastName("Doe");
        entity.setAdress("New York");
        entity.setGender("Male");

        person2 = new Person();
        //id autoIncrement db = 2L
        person2.setFirstName("James");
        person2.setLastName("Smith");
        person2.setAdress("Los Angeles");
        person2.setGender("Male");


        personV1 = new PersonVO();
        //id autoIncrement db = 1L
        personV1.setFirstName("John");
        personV1.setLastName("Doe");
        personV1.setAdress("New York");
        personV1.setGender("Male");


        personV2 = new PersonVO();
        //id autoIncrement db = 2L
        personV2.setFirstName("James");
        personV2.setLastName("Smith");
        personV2.setAdress("Los Angeles");
        personV2.setGender("Male");
    }

    @Test
    public void getAll() {
        List<Person> personList = Collections.singletonList(entity);
        List<PersonVO> personVOS = Collections.singletonList(personV1);

        // configurando o comportamento do mock
        when(personRepository.findAll()).thenReturn(personList);
        when(dozerMapper.parseListObjects(personList, PersonVO.class)).thenReturn(personVOS);

        List<PersonVO> result = personServices.findAll();

        // assert result
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Doe", result.get(0).getLastName());
        assertEquals("New York", result.get(0).getAdress());
        assertEquals("Male", result.get(0).getGender());

        verify(personRepository, times(1)).findAll();
    }

    @Test
    public void getById() {
        Long idToFind = 2L;

        when(personRepository.findById(idToFind)).thenReturn(Optional.of(person2));
        when(dozerMapper.parseObject(person2, PersonVO.class)).thenReturn(personV2);

        PersonVO result = personServices.findById(idToFind);

        assertNotNull(result);
        assertEquals("James", result.getFirstName());
        assertEquals("Smith", result.getLastName());
        assertEquals("Los Angeles", result.getAdress());
        assertEquals("Male", result.getGender());

        verify(personRepository, times(1)).findById(idToFind);
    }

    @Test
    public void createEntity() {

        when(dozerMapper.parseObject(any(PersonVO.class), eq(Person.class))).thenReturn(entity);

        when(personRepository.save(any(Person.class))).thenAnswer(invocation -> { //id autoIncrement db
            Person savedEntity = invocation.getArgument(0);
            return savedEntity;
        });

        when(dozerMapper.parseObject(any(Person.class), eq(PersonVO.class))).thenReturn(personV1);
        PersonVO result = personServices.create(personV1);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("New York", result.getAdress());
        assertEquals("Male", result.getGender());

        verify(personRepository, times(1)).save(any(Person.class));
        verify(dozerMapper, times(1)).parseObject(any(PersonVO.class), eq(Person.class));
    }

    @Test
    public void updateEntity(){

        Long idToFind = 2L;

        when(personRepository.findById(idToFind)).thenReturn(Optional.of(entity));
        when(dozerMapper.parseObject(entity, PersonVO.class)).thenReturn(personV1);

        PersonVO result = personServices.findById(idToFind);
        result.setFirstName("Michael");
        result.setLastName("Johnson");
        result.setAdress("Chicago");
        result.setGender("Male");

        assertNotNull(result);
        assertEquals("Michael", result.getFirstName());
        assertEquals("Johnson", result.getLastName());
        assertEquals("Chicago", result.getAdress());
        assertEquals("Male", result.getGender()); // ok
        verify(personRepository, times(1)).findById(idToFind);
    }
}
