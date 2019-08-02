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

    private Logger logger = LoggerFactory.getLogger(UserTestSuite.class);

    @Autowired
    private UserRepository userRepository;

    private User createContent() {
        return new User("Jessie","busy",2345L);
    }

    @Test
    public void shouldDeleteFromDatabase() {
        //Given
        User user = createContent();
        long prevNumOfRecords = userRepository.count();
        userRepository.save(user);
        logger.info("Records in table: " + userRepository.count());
        //When
        userRepository.delete(user);
        //Then
        logger.info("Records in table: " + userRepository.count());
        long nextNumOfRecords = userRepository.count();
        assertEquals(0, nextNumOfRecords - prevNumOfRecords);
    }

    @Test
    public void shouldAddToDataBase(){
        //Given
        User user = createContent();
        long prevNumOfRecords = userRepository.count();
        //When
        userRepository.save(user);
        //Then
        long nextNumOfRecords = userRepository.count();
        assertEquals(1,nextNumOfRecords - prevNumOfRecords);
    }

    @Test
    public void shouldFindUserById() {
        //Given
        User user = createContent();
        userRepository.save(user);
        //When
        Optional resultUser = userRepository.findById(user.getId());
        //Then
        assertTrue(Optional.ofNullable(resultUser).isPresent());
    }

    @Test
    public void shouldUpdateUser() {
        //Given
        User user = createContent();
        userRepository.save(user);
        User updateUser = new User(user.getId(),"UpdateJessie","updateBusy",567L);
        userRepository.save(updateUser);
        //When
        List<User> resultListOfUsers = userRepository.findAll();
        User resultUser = resultListOfUsers.get(0);
        //Then
        assertEquals("UpdateJessie",resultUser.getUsername());
        assertEquals("updateBusy",resultUser.getStatus());
    }
}
