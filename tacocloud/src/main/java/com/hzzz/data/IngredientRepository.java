package com.hzzz.data;

import com.hzzz.dto.Ingredient;

/**
 * Created by zhen.huaz on 2020/7/3.
 */
public interface IngredientRepository {
    Iterable<Ingredient> findAll();

    Ingredient findOne(String id);

    Ingredient save(Ingredient ingredient);
}
