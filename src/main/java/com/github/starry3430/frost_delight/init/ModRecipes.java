package com.github.starry3430.frost_delight.init;

import com.github.starry3430.frost_delight.FrostDelight;
import com.github.starry3430.frost_delight.crafting.recipe.IceCreamRecipe;
import com.github.starry3430.frost_delight.crafting.serializer.IceCreamRecipeSerializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, FrostDelight.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, FrostDelight.MOD_ID);

    public static final RegistryObject<IceCreamRecipeSerializer> ICE_CREAM_SERIALIZER =
            RECIPE_SERIALIZERS.register("ice_cream", IceCreamRecipeSerializer::new);

    public static final RegistryObject<RecipeType<IceCreamRecipe>> ICE_CREAM_RECIPE =
            RECIPE_TYPES.register("ice_cream", () -> RecipeType.simple(new ResourceLocation(FrostDelight.MOD_ID, "ice_cream")));

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
        RECIPE_TYPES.register(eventBus);
    }
}
