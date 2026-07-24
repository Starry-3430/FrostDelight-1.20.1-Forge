package com.starry3430.frost_delight.item;

import com.starry3430.frost_delight.FrostDelight;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public  static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FrostDelight.MOD_ID);
//---------------------------------这里注册物品---------------------------------//
    public  static final RegistryObject<Item> SUGER_SYRUP =
            ITEMS.register("suger_syrup", () -> new Item(new Item.Properties()));
    public  static final RegistryObject<Item> ITEM_NAME01 =
            ITEMS.register("suger_syrup", () -> new Item(new Item.Properties()));
    public  static final RegistryObject<Item> ITEM_NAME02 =
            ITEMS.register("suger_syrup", () -> new Item(new Item.Properties()));
    public  static final RegistryObject<Item> ITEM_NAME03 =
            ITEMS.register("suger_syrup", () -> new Item(new Item.Properties()));
    public  static final RegistryObject<Item> ITEM_NAME04 =
            ITEMS.register("suger_syrup", () -> new Item(new Item.Properties()));
    public  static final RegistryObject<Item> ITEM_NAME05 =
            ITEMS.register("suger_syrup", () -> new Item(new Item.Properties()));
    public  static final RegistryObject<Item> ITEM_NAME06 =
            ITEMS.register("suger_syrup", () -> new Item(new Item.Properties()));
    public  static final RegistryObject<Item> ITEM_NAME07 =
            ITEMS.register("suger_syrup", () -> new Item(new Item.Properties()));

//---------------------------------这里注册事件---------------------------------//
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


}
