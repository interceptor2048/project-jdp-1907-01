package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
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
        groupRepository.save(group);
        //When
        Optional<Group> shoesGroup = groupRepository.findGroupByName("Shoes");
        //Then
        assertEquals("Shoes", shoesGroup.get().getName());
        //Clean Up
        groupRepository.deleteById(group.getId());
    }

    @Test
    public void shouldFindAllGroup(){
        //Given
        long prevNumOfRecords =  groupRepository.findAll().size();
        Group group = new Group("Shoes");
        Group group2 = new Group("Accessories");
        groupRepository.save(group);
        groupRepository.save(group2);
        //When
        long nextNumOfRecords = groupRepository.findAll().size();
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
        //When
        Group groupWithMoreSpecificName= new Group(group.getId(),"Sneaker");
        groupRepository.save(groupWithMoreSpecificName);
        //Then
        long nextNumOfRecords = groupRepository.count();
        Optional<Group> sneakerGroup = groupRepository.findGroupByName("Sneaker");
        assertEquals(1,nextNumOfRecords - prevNumOfRecords);
        assertEquals("Sneaker",sneakerGroup.get().getName());
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
