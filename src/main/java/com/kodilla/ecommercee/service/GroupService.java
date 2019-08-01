package com.kodilla.ecommercee.service;

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

    public Optional<Group> getGroupById(final Long groupId) {
        return groupRepository.findById(groupId);
    }

    public Optional<Group> getGroupByName(final String name) {
        return groupRepository.findGroupByName(name);
    }

    public void deleteGroupAndMoveProductToUnassignedGroup(long id) {
        Optional<Group> unassignedGroup = getGroupByName("Unassigned product.");
        Optional<Group> optionalGroup = getGroupById(id);
        if(!unassignedGroup.isPresent()) {
            Group newUnassignedGroup = new Group("Unassigned product.");
            saveGroup(newUnassignedGroup);
            optionalGroup.ifPresent(group -> group.getProducts().forEach(p -> p.setGroup(newUnassignedGroup)));
            optionalGroup.ifPresent(group -> group.getProducts().forEach(p-> newUnassignedGroup.getProducts().add(p)));
            optionalGroup.ifPresent(group -> group.getProducts().clear());
            saveGroup(newUnassignedGroup);
        } else {
            optionalGroup.ifPresent(group -> group.getProducts().forEach(p -> p.setGroup(unassignedGroup.get())));
            optionalGroup.ifPresent(group -> group.getProducts().forEach(p-> unassignedGroup.get().getProducts().add(p)));
            optionalGroup.ifPresent(group -> group.getProducts().clear());
            saveGroup(unassignedGroup.get());
        }
        deleteGroup(id);
    }
}
