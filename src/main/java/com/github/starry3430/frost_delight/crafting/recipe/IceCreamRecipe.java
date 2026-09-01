package com.github.starry3430.frost_delight.crafting.recipe;

import com.github.starry3430.frost_delight.crafting.container.IceCreamContainer;
import com.github.starry3430.frost_delight.init.ModRecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.List;

public class IceCreamRecipe implements Recipe<IceCreamContainer> {
    private final ResourceLocation id;
    private final List<Ingredient> ingredients;
    private final Ingredient containerIngredient;
    private final int processingTime;
    private final ItemStack result;

    public IceCreamRecipe(ResourceLocation id, List<Ingredient> ingredients, Ingredient containerIngredient, int processingTime, ItemStack result) {
        this.id = id;
        this.ingredients = ingredients;
        this.containerIngredient = containerIngredient;
        this.processingTime = processingTime;
        this.result = result;
    }

    public List<Ingredient> getIngredientsList() {
        return ingredients;
    }

    public Ingredient getContainerIngredient() {
        return containerIngredient;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    @Override
    public boolean matches(IceCreamContainer container, Level level) {
        if (ingredients.size() != 3) {
            return false;
        }
        for (int i = 0; i < 3; i++) {
            if (!ingredients.get(i).test(container.getItem(i))) {
                return false;
            }
        }
        return containerIngredient.test(container.getItem(3));
    }

    @Override
    public ItemStack assemble(IceCreamContainer container, RegistryAccess access) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 3;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess access) {
        return result.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.ICE_CREAM_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.ICE_CREAM_RECIPE.get();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.addAll(ingredients);
        return list;
    }
}