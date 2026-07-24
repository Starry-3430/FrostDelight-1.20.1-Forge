package com.starry3430.frost_delight.item;

import com.starry3430.frost_delight.FrostDelight;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FrostDelight.MOD_ID);

    //material 材料
    public static final RegistryObject<Item> SUGAR_SYRUP = register("sugar_syrup");
    public static final RegistryObject<Item> CREAM = register("cream");
    public static final RegistryObject<Item> BUTTER = register("butter");
    public static final RegistryObject<Item> VANILLA = register("vanilla");
    public static final RegistryObject<Item> ICE_SHARD = register("ice_shard");
    public static final RegistryObject<Item> FRUIT_PUREE = register("fruit_puree");

    //tool 工具
    public static final RegistryObject<Item> ICE_CREAM_BOWL = register("ice_cream_bowl");
    public static final RegistryObject<Item> POPSICLE_STICK = register("popsicle_stick");
    public static final RegistryObject<Item> POPSICLE_MOLD = register("popsicle_mold");

    //food 食物
    public static final RegistryObject<Item> MILK_POPSICLE = register("milk_popsicle");
    public static final RegistryObject<Item> APPLE_POPSICLE = register("apple_popsicle");
    public static final RegistryObject<Item> WATERMELON_POPSICLE = register("watermelon_popsicle");
    public static final RegistryObject<Item> SWEET_BERRY_POPSICLE = register("sweet_berry_popsicle");
    public static final RegistryObject<Item> HONEY_POPSICLE = register("honey_popsicle");
    public static final RegistryObject<Item> VANILLA_ICE_CREAM = register("vanilla_ice_cream");
    public static final RegistryObject<Item> CHOCOLATE_ICE_CREAM = register("chocolate_ice_cream");
    public static final RegistryObject<Item> SWEET_BERRY_ICE_CREAM = register("sweet_berry_ice_cream");
    public static final RegistryObject<Item> WATERMELON_ICE_CREAM = register("watermelon_ice_cream");
    public static final RegistryObject<Item> HONEY_ICE_CREAM = register("honey_ice_cream");

    private static RegistryObject<Item> register(String name) {
        return ITEMS.register(name, () -> new Item(new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
