package com.github.starry3430.frost_delight.datagen.builder;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class IceCreamBuilder {
    private final List<Ingredient> ingredients = new ArrayList<>();
    private Ingredient containerIngredient = Ingredient.EMPTY;
    private int processingTime = 200;
    private ItemStack result = ItemStack.EMPTY;

    public IceCreamBuilder addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        return this;
    }

    public IceCreamBuilder setContainerIngredient(Ingredient ingredient) {
        this.containerIngredient = ingredient;
        return this;
    }

    public IceCreamBuilder setProcessingTime(int time) {
        this.processingTime = time;
        return this;
    }

    public IceCreamBuilder setResult(ItemStack result) {
        this.result = result;
        return this;
    }

    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation id) {

    }
}
