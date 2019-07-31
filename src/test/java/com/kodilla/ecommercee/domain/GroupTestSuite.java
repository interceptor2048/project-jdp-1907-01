package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
@SpringBootTest
@RunWith(SpringRunner.class)
public class GroupTestSuite {

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    ProductRepository productRepository;

    @Test
    public void shouldAddToDataBase(){
        //Given
        Group group = new Group("Shoes");
        long prevNumOfRecords = groupRepository.count();
        //When
        groupRepository.save(group);
        //Then
        long nextNumOfRecords = groupRepository.count();
        assertEquals(1,nextNumOfRecords - prevNumOfRecords);
        //Clean Up
        groupRepository.deleteById(group.getId());
    }

    @Test
    public void shouldFindAllGroup(){
        //Given
        long prevNumOfRecords = groupRepository.count();
        Group group = new Group("Shoes");
        Group group2 = new Group("Accessories");
        groupRepository.save(group);
        groupRepository.save(group2);
        //When
        long nextNumOfRecords = groupRepository.count();
        //Then
        assertEquals(2,nextNumOfRecords- prevNumOfRecords);
        //Clean Up
        groupRepository.deleteById(group.getId());
        groupRepository.deleteById(group2.getId());
    }

    @Test
    public void shouldUpdateGroupName(){
        //Given
        long prevNumOfRecords = groupRepository.count();
        Group group = new Group("shoes");
        groupRepository.save(group);
        Group groupWithMoreSpecificName= new Group(group.getId(),"Sneaker");
        groupRepository.save(groupWithMoreSpecificName);
        //When
        List<Group> groupList = groupRepository.findAll();
        long nextNumOfRecords = groupRepository.count();
        //Then
        assertEquals(1,nextNumOfRecords - prevNumOfRecords);
        assertEquals("Sneaker",groupList.get(0).getName());
        assertEquals(1,groupList.size());
        //Clean Up
        groupRepository.deleteById(group.getId());
    }

    @Test
    public void shouldDeleteFromDatabase() {
        //Given
        long prevNumOfRecords = groupRepository.count();
        Group group = new Group("shoes");
        groupRepository.save(group);
        //When
        groupRepository.deleteById(group.getId());
        //Then
        long nextNumOfRecords = groupRepository.count();
        assertEquals(0,nextNumOfRecords - prevNumOfRecords);
    }
}
