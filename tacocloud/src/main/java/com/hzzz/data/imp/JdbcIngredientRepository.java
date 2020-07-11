package com.hzzz.data.imp;

import com.hzzz.data.IngredientRepository;
import com.hzzz.dto.Ingredient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhen.huaz on 2020/7/3.
 */
@Repository("jdbcIngredientRepo")
@Data
public class JdbcIngredientRepository implements IngredientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Map<String,Ingredient> maps = new HashMap<>();

    @Override
    public Iterable<Ingredient> findAll() {
       return jdbcTemplate.query("select id, name, type from Ingredient",this::mapRowToIngredient);
    }

    @Override
    public Ingredient findOne(String id) {
        if(maps.containsKey(id)){
            return maps.get(id);
        } else {
            Ingredient ingredient = jdbcTemplate.queryForObject("select id, name, type from Ingredient where id=?",this::mapRowToIngredient,id);
            if(maps.size() > 5){
                maps.remove(0);
                maps.put(id,ingredient);
                return maps.get(id);
            }


        }

    }

    private Ingredient mapRowToIngredient(ResultSet rs, int rowNum)
            throws SQLException {
        return new Ingredient(
                rs.getString("id"),
                rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type")));
    }
    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update(
                "insert into Ingredient (id, name, type) values (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }
}
