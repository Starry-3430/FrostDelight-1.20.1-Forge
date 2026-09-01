package com.github.starry3430.frost_delight.crafting.serializer;

import com.github.starry3430.frost_delight.crafting.recipe.IceCreamRecipe;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.crafting.CraftingHelper;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class IceCreamRecipeSerializer implements RecipeSerializer<IceCreamRecipe> {
    @Override
    public IceCreamRecipe fromJson(ResourceLocation id, JsonObject json) {
        JsonArray ingArray = GsonHelper.getAsJsonArray(json, "ingredients");
        List<Ingredient> ingredients = new ArrayList<>();
        for (JsonElement element : ingArray) {
            ingredients.add(Ingredient.fromJson(element));
        }
        Ingredient containerIngredient = Ingredient.fromJson(json.get("container_ingredient"));
        int processingTime = GsonHelper.getAsInt(json, "processing_time", 200);
        ItemStack result = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result"), true);
        return new IceCreamRecipe(id, ingredients, containerIngredient, processingTime, result);
    }

    @Override
    public @Nullable IceCreamRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
        int count = buf.readVarInt();
        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ingredients.add(Ingredient.fromNetwork(buf));
        }
        Ingredient containerIngredient = Ingredient.fromNetwork(buf);
        int processingTime = buf.readVarInt();
        ItemStack result = ItemStack.of(buf.readNbt());
        return new IceCreamRecipe(id, ingredients, containerIngredient, processingTime, result);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf, IceCreamRecipe recipe) {
        buf.writeVarInt(recipe.getIngredientsList().size());
        for (Ingredient ingredient : recipe.getIngredientsList()) {
            ingredient.toNetwork(buf);
        }
        recipe.getContainerIngredient().toNetwork(buf);
        buf.writeVarInt(recipe.getProcessingTime());
        CompoundTag tag = new CompoundTag();
        recipe.getResultItem(null).save(tag);
        buf.writeNbt(tag);
    }
}