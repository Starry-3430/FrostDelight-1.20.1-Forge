package com.github.starry3430.frost_delight.crafting.blockentity;

import com.github.starry3430.frost_delight.crafting.container.IceCreamContainer;
import com.github.starry3430.frost_delight.crafting.recipe.IceCreamRecipe;
import com.github.starry3430.frost_delight.init.ModRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

public class IceCreamBlockEntity extends BlockEntity {
    private final ItemStackHandler inventory = new ItemStackHandler(4);
    private int progress = 0;
    private int totalTime = 0;
    private IceCreamRecipe currentRecipe = null;

    public IceCreamBlockEntity(BlockPos pos, BlockState state) {
        super(null, pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, IceCreamBlockEntity entity) {
        if (level == null || level.isClientSide) {
            return;
        }

        IceCreamContainer container = new IceCreamContainer();
        for (int i = 0; i < entity.inventory.getSlots(); i++) {
            container.setItem(i, entity.inventory.getStackInSlot(i).copy());
        }

        IceCreamRecipe recipe = entity.findMatchingRecipe(container);
        if (recipe != null) {
            if (entity.currentRecipe == null || !entity.currentRecipe.equals(recipe)) {
                entity.currentRecipe = recipe;
                entity.totalTime = recipe.getProcessingTime();
                entity.progress = 0;
            }

            entity.progress++;
            if (entity.progress >= entity.totalTime) {
                entity.finishCrafting(recipe);
                entity.progress = 0;
                entity.currentRecipe = null;
            }
            entity.setChanged();
        } else {
            entity.progress = 0;
            entity.currentRecipe = null;
        }
    }

    private IceCreamRecipe findMatchingRecipe(IceCreamContainer container) {
        if (level == null) {
            return null;
        }
        for (IceCreamRecipe recipe : level.getRecipeManager().getAllRecipesFor(ModRecipes.ICE_CREAM_RECIPE.get())) {
            if (recipe.matches(container, level)) {
                return recipe;
            }
        }
        return null;
    }

    private void finishCrafting(IceCreamRecipe recipe) {
        for (int i = 0; i < 3; i++) {
            inventory.extractItem(i, 1, false);
        }
        inventory.extractItem(3, 1, false);

        ItemStack result = recipe.getResultItem(level.registryAccess()).copy();
        if (inventory.getStackInSlot(0).isEmpty()) {
            inventory.setStackInSlot(0, result);
        } else {
            inventory.getStackInSlot(0).grow(result.getCount());
        }
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        inventory.deserializeNBT(tag.getCompound("Inventory"));
        progress = tag.getInt("Progress");
        totalTime = tag.getInt("TotalTime");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("Inventory", inventory.serializeNBT());
        tag.putInt("Progress", progress);
        tag.putInt("TotalTime", totalTime);
    }
}