package com.kodilla.ecommercee.mapper;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.dto.GroupDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupMapperTestSuite {

    @Autowired
    private GroupMapper groupMapper;

    private Group createGroup() {
        return new Group(1L, "Test group");
    }

    private GroupDto createGroupDto() {
        return new GroupDto(1L, "Test group");
    }

    @Test
    public void testMapToGroup() {
        //Given
        GroupDto groupDto = createGroupDto();

        //When
        Group group = groupMapper.mapToGroup(groupDto);

        //Then
        assertNotNull(group);
        assertEquals(Long.valueOf(1), group.getId());
        assertEquals("Test group", group.getName());
    }

    @Test
    public void testMapToGroupDto() {
        //Given
        Group group = createGroup();

        //When
        GroupDto groupDto = groupMapper.mapToGroupDto(group);

        //Then
        assertNotNull(group);
        assertEquals(Long.valueOf(1), groupDto.getId());
        assertEquals("Test group", groupDto.getName());
    }

    @Test
    public void testMapToGroupDtoList() {
        //Given
        Group group1 = new Group(1L, "Test group 1");
        Group group2 = new Group(2L, "Test group 2");
        List<Group> groupList = new ArrayList<>();
        groupList.add(group1);
        groupList.add(group2);

        //When
        List<GroupDto> groupDtoList = groupMapper.mapToGroupDtoList(groupList);

        //Then
        //Then
        assertNotNull(groupDtoList);
        assertEquals(2, groupDtoList.size());
    }
}
