package com.project.market.persistence.mapper;

import com.project.market.domain.Category;
import com.project.market.persistence.entities.DomainCategory;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

// Integrate with spring
@Mapper(componentModel = "spring")
public interface CategoryMapper {
    // Design our mappers
    @Mappings({
            @Mapping(source = "idCategory", target = "categoryId"),
            @Mapping(source = "description", target = "category"),
            @Mapping(source = "active", target = "active")
    })
    Category toCategory(DomainCategory domainCategory);

    // Inverse situation
    // Ignoring the Products relationship
    @InheritInverseConfiguration
    @Mapping(target = "products", ignore = true)
    DomainCategory toDomainCategory(Category category);

}
