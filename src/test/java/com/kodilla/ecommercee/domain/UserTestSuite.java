package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTestSuite {

    private Logger LOGGER = LoggerFactory.getLogger(UserTestSuite.class);

    @Autowired
    UserRepository userRepository;

    public User createContent() {
        return new User("Jessie","busy",2345L);
    }

    @Test
    public void shouldAddToDataBase(){
        //Given
        User user = createContent();
        userRepository.save(user);
        //When
        long numberOfRecords = userRepository.count();
        //Then
        System.out.println(numberOfRecords);
        assertEquals(1,numberOfRecords);
        //Clean Up
        userRepository.deleteById(user.getId());
    }

//    @Test
//    public void shouldDeleteFromDatabase() {
//        //Given
//        User user = createContent();
//        userRepository.save(user);
//        LOGGER.info("Records in table: " + userRepository.count());
//        userRepository.deleteById(user.getId());
//        LOGGER.info("Records in table: " + userRepository.count());
//        //When
//        long numberOfRecords = userRepository.count();
//        //Then
//        System.out.println(numberOfRecords);
////        assertEquals(0,numberOfRecords);
//    }
//
//    @Test
//    public void shouldFindUserById() {
//        //Given
//        User user = createContent();
//        userRepository.save(user);
//        //When
//        Optional resultUser = userRepository.findById(user.getId());
//        //Then
//        System.out.println(resultUser);
////        assertTrue(Optional.ofNullable(resultUser).isPresent());
//        //Clean Up
//        userRepository.deleteById(user.getId());
//    }
//
//    @Test
//    public void shouldUpdateUser() {
//        //Given
//        User user = createContent();
//        userRepository.save(user);
//        User updateUser = new User(user.getId(),"UpdateJessie","updateBusy",567L);
//        userRepository.save(updateUser);
//        //When
//        List<User> resultListOfUsers = userRepository.findAll();
//        User resultUser = resultListOfUsers.get(0);
//        //Then
//        System.out.println(resultListOfUsers);
////        assertEquals(1,resultListOfUsers.size());
////        assertEquals("UpdateJessie",resultUser.getUsername());
////        assertEquals("updateBusy",resultUser.getStatus());
//        //Clean Up
//        userRepository.deleteById(user.getId());
//    }
}
