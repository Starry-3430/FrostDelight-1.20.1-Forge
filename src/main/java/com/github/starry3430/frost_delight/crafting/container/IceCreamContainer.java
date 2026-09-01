package com.github.starry3430.frost_delight.crafting.container;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class IceCreamContainer extends SimpleContainer {
    public static final int SLOT_COUNT = 4;

    public IceCreamContainer() {
        super(SLOT_COUNT);
    }

    public ItemStack getIngredient(int index) {
        return getItem(index);
    }

    public ItemStack getContainerItem() {
        return getItem(3);
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        if (slot == 3) {
            return stack.is(Items.BOWL);
        }
        return true;
    }
}