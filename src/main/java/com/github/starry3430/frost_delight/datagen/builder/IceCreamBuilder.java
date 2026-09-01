package com.github.starry3430.frost_delight.datagen.builder;

import com.github.starry3430.frost_delight.init.ModRecipes;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
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
        consumer.accept(new FinishedIceCreamRecipe(id, ingredients, containerIngredient, processingTime, result));
    }

    private static final class FinishedIceCreamRecipe implements FinishedRecipe {
        private final ResourceLocation id;
        private final List<Ingredient> ingredients;
        private final Ingredient containerIngredient;
        private final int processingTime;
        private final ItemStack result;

        private FinishedIceCreamRecipe(ResourceLocation id,
                                       List<Ingredient> ingredients,
                                       Ingredient containerIngredient,
                                       int processingTime,
                                       ItemStack result) {
            this.id = id;
            this.ingredients = ingredients;
            this.containerIngredient = containerIngredient;
            this.processingTime = processingTime;
            this.result = result;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            JsonArray array = new JsonArray();
            for (Ingredient ingredient : ingredients) {
                array.add(ingredient.toJson());
            }
            json.add("ingredients", array);
            json.add("container_ingredient", containerIngredient.toJson());
            json.addProperty("processing_time", processingTime);

            JsonObject resultJson = new JsonObject();
            resultJson.addProperty("item", ForgeRegistries.ITEMS.getKey(result.getItem()).toString());
            resultJson.addProperty("count", result.getCount());
            json.add("result", resultJson);
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ModRecipes.ICE_CREAM_SERIALIZER.get();
        }

        @Override
        public @Nullable JsonObject serializeAdvancement() {
            return null;
        }

        @Override
        public @Nullable ResourceLocation getAdvancementId() {
            return null;
        }
    }
}