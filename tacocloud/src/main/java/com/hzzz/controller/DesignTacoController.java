package com.hzzz.controller;

import com.hzzz.data.IngredientRepository;
import com.hzzz.data.TacoRepository;
import com.hzzz.dto.Ingredient;
import com.hzzz.dto.Order;
import com.hzzz.dto.Taco;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhen.huaz on 2020/7/1.
 */
@Controller
@Slf4j
@Data
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    @Resource(name="jdbcIngredientRepo")
    private IngredientRepository ingredientRepo;
    @Resource(name="jdbcTacoRepo")
    private TacoRepository tacoRepo;

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        ingredientRepo.findAll().forEach(ingredients::add);
//        List<Ingredient> ingredients = Arrays.asList(
//                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
//                new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
//                new Ingredient("GRBF", "Ground Beef",Ingredient. Type.PROTEIN),
//                new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
//                new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
//                new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
//                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
//                new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
//                new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
//                new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
//        );

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        model.addAttribute("design", new Taco());
        return "design";
    }

    // provided by 'aexiaosong'
    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }
    /*
     * @Valid 注释告诉 Spring MVC 在提交的 Taco 对象绑定到提交的表单数据之后，以及调用 processDesign() 方法之前，
     * 对提交的 Taco 对象执行验证。如果存在任何验证错误，这些错误的详细信息将在传递到 processDesign() 的错误对象中捕获。
     * processDesign() 的前几行查询 Errors 对象，询问它的 hasErrors() 方法是否存在任何验证错误。如果有，该方法结束时不处理 Taco，
     * 并返回 “design” 视图名，以便重新显示表单。
     */
    @PostMapping
    public String processDesign(@Valid Taco taco, BindingResult bindingResult,@ModelAttribute Order order ) {
        if (bindingResult.hasErrors()) {
//            return "redirect:/design";
            return "design";
        }
//        // Save the taco design...
//        // We'll do this in chapter 3
//        log.info("Processing design: " + taco);
//        return "redirect:/orders/current";
        Taco saved = tacoRepo.save(taco);
        order.addTaco(saved);
        return "redirect:/orders/current";
    }
}
