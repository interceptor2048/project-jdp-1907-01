package com.kodilla.ecommercee.service;
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
        User user = new User("User1", "1", 1234L);
        //When
        userService.save(user);
        User tempUser = userService.getUser(user.getId()).get();
        List<User> tempUsers = userService.getAllUsers();
        //Then
        assertNotNull(tempUser);
        assertNotNull(tempUsers);
        assertEquals("User1", tempUser.getUsername());
        assertEquals(1, tempUsers.size());
    }

    @Test
    public void testDeleteUser() {
        //Given
        User user = new User("User1", "1", 1234L);
        //When
        userService.save(user);
        userService.deleteUser(user.getId());
        //Then
        assertFalse(userService.getUser(user.getId()).isPresent());
    }
}