package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.CommunityEmailDTO;
import com.safetynet.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class CommunityEmailServiceTest {

    CommunityEmailService communityEmailService=new CommunityEmailService();
    private static List<Person>persons;
    private static Person one;
    private static Person two;

    @BeforeAll
    public static void setUp(){
        persons=new ArrayList<>();
        one=new Person();
        one.setEmail("mail@mail.com");
        two=new Person();
        two.setAddress("mail@mail.be");
        persons.add(one);
        persons.add(two);
    }
    @Test
    public void getCommunityEmailsTest(){
        CommunityEmailDTO result=communityEmailService.getCommunityEmails(persons);
        assertFalse(result.getEmails().isEmpty());
    }
}
