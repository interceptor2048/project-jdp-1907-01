package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.GroupNotFoundException;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ProductMapper productMapper;

    public List<Group> getAllGroup() {
        return groupRepository.findAll();
    }

    public Group saveGroup(final Group group) {
        return groupRepository.save(group);
    }

    public void deleteGroup(final Long id) {
        groupRepository.deleteById(id);
    }

    public Group getGroupById(final Long groupId) {         // used id productMapper
        return groupRepository.findById(groupId).orElse(null);
    }

    public Optional<Group> getGroup(final long groupId) {   // used id groupController
        return groupRepository.findById(groupId);
    }
}
