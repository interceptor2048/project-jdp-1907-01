package com.kodilla.ecommercee.service;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTestSuite {

    @Autowired
    private UserService userService;

    @Test
    public void testSaveAndGetUser() {
        //Given

        long prevNumOfRecords = userService.getAllUsers().size();
        User user = new User("User1", "1", 1234L, "123456789", new Cart());

        //When
        userService.save(user);
        User tempUser = userService.getUser(user.getId()).get();
        long afterNumOfRecords = userService.getAllUsers().size();


        //Then
        assertNotNull(tempUser);
        assertEquals("User1", tempUser.getUsername());
        assertEquals(1, afterNumOfRecords - prevNumOfRecords);
    }

    @Test
    public void testDeleteUser() {
        //Given
        User user = new User("User1", "1", 1234L, "123456789", new Cart());

        //When
        userService.save(user);
        userService.deleteUser(user.getId());

        //Then
        assertFalse(userService.getUser(user.getId()).isPresent());
    }

    @Test
    public void testReturnUserById(){
        //Given
        User user = new User("User1", "1", 1234L,"123456789", new Cart());
        userService.save(user);
        //When
        User resultUser = userService.returnUserById(user.getId());
        //Then
        assertEquals(user.getId(),resultUser.getId());
        assertEquals(user.getStatus(),resultUser.getStatus());
        assertEquals(user.getUserKey(),resultUser.getUserKey());
    }
}