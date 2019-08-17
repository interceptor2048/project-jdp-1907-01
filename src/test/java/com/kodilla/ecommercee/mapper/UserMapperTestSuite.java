package com.kodilla.ecommercee.mapper;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTestSuite {

    @Autowired
    private UserMapper mapper;

    @Test
    public void mapToUser() {
        //Given
        UserDto userDto = new UserDto(1L, "User1", "1", 1234L, "123456789",3209L);

        //When
        User user = mapper.mapToUser(userDto);

        //Then
        assertNotNull(user);
        assertEquals(Long.valueOf(1), user.getId());
        assertEquals("User1", user.getUsername());
        assertEquals("1", user.getStatus());
        assertEquals(Long.valueOf(1234), user.getUserKey());
    }

    @Test
    public void mapToUserDto() {
        //Given
        User user = new User(1L, "User1", "1", 1234L, "123456789", new Cart(3209L));

        //When
        UserDto userDto = mapper.mapToUserDto(user);

        //Then
        assertNotNull(userDto);
        assertEquals(Long.valueOf(1), userDto.getId());
        assertEquals("User1", userDto.getUsername());
        assertEquals("1", userDto.getStatus());
        assertEquals(Long.valueOf(1234), userDto.getUserKey());
    }

    @Test
    public void mapToUserDtoList() {
        //Given
        User user = new User(1L, "User1", "1", 1234L, "123456789", new Cart(3209L));
        List<User> users = new ArrayList<>();
        users.add(user);

        //When
        List<UserDto> usersDtoList = mapper.mapToUserDtoList(users);

        //Then
        assertNotNull(usersDtoList);
        assertEquals(1, usersDtoList.size());
        assertEquals("User1", usersDtoList.get(0).getUsername());
    }
}