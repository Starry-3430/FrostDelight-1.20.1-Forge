package com.github.starry3430.frost_delight.datagen;

import com.github.starry3430.frost_delight.init.ModBlocks;
import com.github.starry3430.frost_delight.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipesProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipesProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter){
        // 合成配方
        //-------------------------------------------------------------------------//
        // 糖浆
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SUGAR_SYRUP.get())
                .requires(Items.GLASS_BOTTLE, 1)
                .requires(Items.SUGAR, 1)
                .unlockedBy("has_sugar", has(Items.SUGAR))
                .save(pWriter);

        // 冰棒棍
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.POPSICLE_STICK.get(), 2)
                .requires(Items.STICK)
                .unlockedBy("has_stick", has(Items.STICK))
                .save(pWriter);

        // 冰激凌机
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ICE_CREAM_MACHINE.get())
                .define('I', Items.IRON_INGOT)
                .define('B', Blocks.ICE)
                .define('S', Items.STICK)
                .define('C', Blocks.CHEST)
                .define('P', Blocks.SMOOTH_STONE)
                .pattern("IBI")
                .pattern("ISI")
                .pattern("PCP")
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .save(pWriter);

        // 冰激凌碗
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ICE_CREAM_BOWL.get())
                .define('P', Items.PAPER)
                .pattern("P P")
                .pattern(" P ")
                .unlockedBy("has_paper", has(Items.PAPER))
                .save(pWriter);

        // 冰棒套
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.POPSICLE_SLEEVE.get())
                .define('P', Items.PAPER)
                .define('S', Items.STRING)
                .pattern(" P ")
                .pattern("SPS")
                .pattern(" P ")
                .unlockedBy("has_paper", has(Items.PAPER))
                .save(pWriter);

        // 冰棒模具
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.POPSICLE_MOLD.get())
                .requires(ModItems.POPSICLE_STICK.get())
                .requires(ModItems.POPSICLE_SLEEVE.get())
                .unlockedBy("has_popsicle_stick", has(ModItems.POPSICLE_STICK.get()))
                .save(pWriter);
    }
}
