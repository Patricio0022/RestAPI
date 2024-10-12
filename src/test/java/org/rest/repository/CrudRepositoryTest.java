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
        entity.setId(1L);
        entity.setFirstName("Pingu");
        entity.setLastName("Pingado");
        entity.setAdress("Alasca");
        entity.setGender("Male");

        person2 = new Person();
        person2.setId(2L);
        person2.setFirstName("Mario");
        person2.setLastName("Kart");
        person2.setAdress("Cogumelo");
        person2.setGender("Male");


        personV1 = new PersonVO();
        personV1.setKey(1L);
        personV1.setFirstName("Pingu");
        personV1.setLastName("Pingado");
        personV1.setAdress("Alasca");
        personV1.setGender("Male");

        personV2 = new PersonVO();
        personV2.setKey(2L);
        personV2.setFirstName("Mario");
        personV2.setLastName("Kart");
        personV2.setAdress("Cogumelo");
        personV2.setGender("Male");
    }


    @Test
    public void getAll() {
        List<Person> personList = Arrays.asList(entity);
        List<PersonVO> personVOS = Arrays.asList(personV1);

        // configurando o comportamento do mock
        when(personRepository.findAll()).thenReturn(personList);
        when(dozerMapper.parseListObjects(personList, PersonVO.class)).thenReturn(personVOS);


        List<PersonVO> result = personServices.findAll();

        // assert result
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Pingu", result.get(0).getFirstName());
        assertEquals("Pingado", result.get(0).getLastName());
        assertEquals("Alasca", result.get(0).getAdress());
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
        assertEquals("Mario", result.getFirstName());
        assertEquals("Kart", result.getLastName());
        assertEquals("Cogumelo", result.getAdress());
        assertEquals("Male", result.getGender());

        verify(personRepository, times(1)).findById(idToFind);
    }



    @Test
    public void createEntity() {

        when(dozerMapper.parseObject(any(PersonVO.class), eq(Person.class))).thenReturn(entity);

        when(personRepository.save(any(Person.class))).thenAnswer(invocation -> { //id autoIncrement db
            Person savedEntity = invocation.getArgument(0);
            savedEntity.setId(1L);
            return savedEntity;
        });

        when(dozerMapper.parseObject(any(Person.class), eq(PersonVO.class))).thenReturn(personV1);
        PersonVO result = personServices.create(personV1);


        assertNotNull(result);
        assertEquals("Pingu", result.getFirstName());
        assertEquals("Pingado", result.getLastName());
        assertEquals("Alasca", result.getAdress());
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
        result.setFirstName("Marcos");
        result.setLastName("Vinicius");
        result.setAdress("Sao Paulo");
        result.setGender("Male");

        assertNotNull(result);
        assertEquals("Marcos", result.getFirstName());
        assertEquals("Vinicius", result.getLastName());
        assertEquals("Sao Paulo", result.getAdress());
        assertEquals("Male", result.getGender()); // ok Atualizado

        verify(personRepository, times(1)).findById(idToFind);
    }






}
