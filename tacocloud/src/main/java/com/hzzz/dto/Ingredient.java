package com.hzzz.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Created by zhen.huaz on 2020/7/1.
 */
@Data
@RequiredArgsConstructor
public class Ingredient {
    private final String id;
    private final String name;
    private final Type type;

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

}