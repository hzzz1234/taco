package com.hzzz.controller.converter;

import com.hzzz.data.IngredientRepository;
import com.hzzz.dto.Ingredient;
import lombok.Data;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by zhen.huaz on 2020/7/9.
 * 转换器用于类型转换
 */

@Component
@Data
public class IngredientConverter implements Converter<String,Ingredient> {
    @Resource(name="jdbcIngredientRepo")
    private IngredientRepository ingredientRepo;

    @Override
    public Ingredient convert(String id) {
        return ingredientRepo.findOne(id);
    }
}
