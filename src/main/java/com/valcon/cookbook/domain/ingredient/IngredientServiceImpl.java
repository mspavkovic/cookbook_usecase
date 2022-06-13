package com.valcon.cookbook.domain.ingredient;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valcon.cookbook.web.dto.IngredientDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    @Override
    @Transactional
    public Ingredient save(final IngredientDto ingredientDto) {
        return ingredientRepository.save(ingredientMapper.map(ingredientDto));
    }

    @Override
    public List<IngredientDto> findAll() {
        return ingredientRepository.findAll()
                                    .stream()
                                    .map(ingredientMapper::map)
                                    .collect(Collectors.toList());
    }
}
