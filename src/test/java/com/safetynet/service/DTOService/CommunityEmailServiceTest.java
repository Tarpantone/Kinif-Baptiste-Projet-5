package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.CommunityEmailDTO;
import com.safetynet.model.Person;
import com.safetynet.service.PersonService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommunityEmailServiceTest {

    @Mock
    PersonService personService;
    @Autowired@InjectMocks
    CommunityEmailService communityEmailService=new CommunityEmailService();
    private static List<Person>persons;
    private static Person one;
    private static Person two;

    @BeforeAll
    public static void setUp(){
        persons=new ArrayList<>();
        one=new Person();
        one.setEmail("mail@mail.com");
        one.setCity("city");
        two=new Person();
        two.setAddress("mail@mail.be");
        two.setCity("city");
        persons.add(one);
        persons.add(two);
    }
    @Test
    public void getCommunityEmailsTest(){
        when(personService.getPersons()).thenReturn(persons);
        CommunityEmailDTO result=communityEmailService.getCommunityEmails("city");
        assertFalse(result.getEmails().isEmpty());
    }
}
