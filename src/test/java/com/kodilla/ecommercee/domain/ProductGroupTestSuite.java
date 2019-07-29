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
public class ProductGroupTestSuite {

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
        long numberOfRecords = groupRepository.count();
        //Then
        assertEquals(1,numberOfRecords);
        //Clean Up
        groupRepository.deleteById(group.getId());
    }

    @Test
    public void shouldFindAllGroup(){
        //Given
        Group group = new Group("Shoes");
        Group group2 = new Group("Accessories");
        groupRepository.save(group);
        groupRepository.save(group2);
        //When
        List<Group> groupList = groupRepository.findAll();
        //Then
        assertEquals(2,groupList.size());
        //Clean Up
        groupRepository.deleteById(group.getId());
        groupRepository.deleteById(group2.getId());
    }
    @Test
    public void shouldUpdateGroupName(){
        //Given
        Group group = new Group("shoes");
        groupRepository.save(group);
        Group groupWithMoreSpecificName= new Group(group.getId(),"Sneaker");
        groupRepository.save(groupWithMoreSpecificName);
        //When
        List<Group> groupList = groupRepository.findAll();
        int listSize = groupRepository.findAll().size();
        //Then
        assertEquals(1,listSize);
        assertEquals("Sneaker",groupList.get(0).getName());
        //Clean Up
        groupRepository.deleteById(group.getId());
    }

    @Test
    public void shouldDeleteFromDatabase() {
        //Given
        Group group = new Group("shoes");
        groupRepository.save(group);
        long numberOfRecords = groupRepository.count();
        groupRepository.deleteById(group.getId());
        //When
        long numberOfRecordsAfterDelete = groupRepository.count();
        //Then
        assertEquals(1,numberOfRecords);
        assertEquals(0,numberOfRecordsAfterDelete);
    }

}
