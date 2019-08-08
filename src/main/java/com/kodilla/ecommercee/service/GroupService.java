package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
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
    private ProductService productService;

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Optional<Group> getGroup(final Long id) {
        return groupRepository.findById(id);
    }

    public Group saveGroup(final Group group) {
        return groupRepository.save(group);
    }

    public void deleteGroup(final Long id) {
        groupRepository.deleteById(id);
    }

    public Optional<Group> getGroupByName(final String name) {
        return groupRepository.findGroupByName(name);
    }

    public void deleteGroupAndMoveProductToUnassignedGroup(long id) {
        Optional<Group> unassignedGroup = Optional.of(getGroupByName("Unassigned product.").orElse(new Group("Unassigned product.")));
        groupRepository.save(unassignedGroup.get());
        Optional<Group> groupToRemove = getGroup(id);
        groupToRemove.ifPresent(group -> group.getProducts().forEach(p -> p.setGroup(unassignedGroup.get())));
        groupToRemove.ifPresent(group -> group.getProducts().forEach(p -> productService.saveProduct(p)));
        groupToRemove.ifPresent(group -> group.getProducts().forEach(p-> unassignedGroup.get().getProducts().add(p)));
        groupToRemove.ifPresent(group -> group.getProducts().clear());
        saveGroup(unassignedGroup.get());
        deleteGroup(id);
    }
}


