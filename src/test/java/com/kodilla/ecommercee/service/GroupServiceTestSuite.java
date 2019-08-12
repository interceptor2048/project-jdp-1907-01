package com.kodilla.ecommercee.service;
import com.kodilla.ecommercee.controller.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.repository.GroupRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GroupServiceTestSuite {

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupRepository repo;

    Logger LOGGER = LoggerFactory.getLogger(GroupServiceTestSuite.class);

    @Test
    public void testSaveOrGetGroup() {
        //Given
        long prevNumOfRecords =  groupService.getAllGroups().size();
        List<Group> groups = new ArrayList<>();
        Group tempGroup = null;
        Group group = new Group("Group");
        //When
        groupService.saveGroup(group);
        try {
            tempGroup = groupService.getGroup(group.getId()).orElseThrow(GroupNotFoundException::new);
            groups = groupService.getAllGroups();
        } catch (GroupNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        long afterNumOfRecords =  groupService.getAllGroups().size();
        //Then
        assertEquals(group.getId(), tempGroup.getId());
        assertEquals(group.getName(), tempGroup.getName());
        assertEquals(1, afterNumOfRecords - prevNumOfRecords);
        //Clean up
        groupService.deleteGroup(group.getId());
    }

    @Test
    public void testUpdateGroup() {
        //Given
        Group tempGroup = null;
        Group group = new Group("Group");
        groupService.saveGroup(group);

        Group updatedGroup = new Group(group.getId(), "Updated Group");
        groupService.saveGroup(updatedGroup);
        //When
        try {
            tempGroup = groupService.getGroup(group.getId()).orElseThrow(GroupNotFoundException::new);
        } catch (GroupNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        //Then
        assertEquals("Updated Group", tempGroup.getName());
        //CleanUp and test delete
        groupService.deleteGroup(group.getId());
        assertFalse(groupService.getGroup(group.getId()).isPresent());
    }
}