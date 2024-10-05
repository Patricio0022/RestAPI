package org.rest.repositories;

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
class PersonRepositoryTest {

    @InjectMocks
    private PersonServices personServices; //real injection
    @Mock
    private PersonRepository personRepository; //behavior
    @Mock
    private DozerMapper dozerMapper; //behavior

    private Person entity, person2;
    private PersonVO personVO, personVO1;

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

        personVO1 = new PersonVO();
        personVO1.setKey(1L);
        personVO1.setFirstName("Mario");
        personVO1.setLastName("Kart");
        personVO1.setAdress("Cogumelo");
        personVO1.setGender("Male");

        personVO = new PersonVO();
        personVO.setKey(2L);
        personVO.setFirstName("Pingu");
        personVO.setLastName("Pingado");
        personVO.setAdress("Alasca");
        personVO.setGender("Male");
    }


    @Test
    public void getAll() {
        List<Person> personList = Arrays.asList(entity);
        List<PersonVO> personVOS = Arrays.asList(personVO);

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
        when(dozerMapper.parseObject(person2, PersonVO.class)).thenReturn(personVO1);

        PersonVO result = personServices.findById(idToFind);

        assertNotNull(result);
        assertEquals("Mario", result.getFirstName());
        assertEquals("Kart", result.getLastName());
        assertEquals("Cogumelo", result.getAdress());
        assertEquals("Male", result.getGender());

        verify(personRepository, times(1)).findById(idToFind);
    }

}
