package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.dto.ProductGroupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupMapper {

    public Group mapToGroup(final ProductGroupDto productGroupDto) {
        return new Group(
                productGroupDto.getId(),
                productGroupDto.getName()

                );
    }

    public ProductGroupDto mapToProductGroupDto(final Group group) {
        return new ProductGroupDto(
                group.getId(),
                group.getName()
        );
    }

    public List<ProductGroupDto> mapToroductGroupDtoList(final List<Group> groupList) {
        return groupList.stream()
                //.map(g -> new ProductGroupDto(g.getId(), g.getName()))
                .map(this::mapToProductGroupDto)
                .collect(Collectors.toList()
                );
    }
}
