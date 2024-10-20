package org.rest.unittests.mocks;

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
import org.rest.repository.PersonRepository;
import org.rest.services.PersonServices;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class ReqResRepository {


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
        entity.setFirstName("Pingu");
        entity.setLastName("Pingado");
        entity.setAdress("Alasca");
        entity.setGender("Male");


        personV1 = new PersonVO();
        personV1.setKey(1L);
        personV1.setFirstName("Pingu");
        personV1.setLastName("Pingado");
        personV1.setAdress("Alasca");
        personV1.setGender("Male");
    }


        @Test
        public void create() {
            //  -->        DTO frontend convertido em Person para o repository
            when(dozerMapper.parseObject(any(PersonVO.class), eq(Person.class))).thenReturn(entity);
            //  <--        Person convertida em DTO para a response client
            when(dozerMapper.parseObject(entity, PersonVO.class)).thenReturn(personV1);
            // save entity
            when(personRepository.save(any(Person.class))).thenAnswer(invocation -> {
                        Person savedEntity = invocation.getArgument(0);
                        return savedEntity;
            });

            PersonVO result = personServices.create(personV1);

            assertNotNull(result);

            verify(personRepository, times(1)).save(any(Person.class));
            verify(dozerMapper, times(1)).parseObject(any(PersonVO.class), eq(Person.class));
            verify(dozerMapper, times(1)).parseObject(entity, PersonVO.class);
        }


    }

