package com.valcon.cookbook.domain.ingredient;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.valcon.cookbook.domain.AbstractEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Table(name = "ingredients")
public class Ingredient extends AbstractEntity {

    @NotBlank(message = "ingredient name must be specified")
    @EqualsAndHashCode.Include
    private String name;

    @NotBlank(message = "ingredient quantity must be specified")
    @EqualsAndHashCode.Include
    private String quantity;
}
