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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonRepositoryTest {

    @InjectMocks
    private PersonServices personServices;
    @Mock
    private PersonRepository personRepository;
    @Mock
    private DozerMapper dozerMapper;

    private Person entity;
    private PersonVO personVO;

    @BeforeEach
    public void setUp() {
        entity = new Person();
        entity.setId(1L);
        entity.setFirstName("Pingu");
        entity.setLastName("Pingado");
        entity.setAdress("Alasca");
        entity.setGender("Male");

        personVO = new PersonVO();
        personVO.setKey(1L);
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


        verify(personRepository, times(1)).findAll();
    }
}
