package com.github.starry3430.frost_delight.init;

import com.github.starry3430.frost_delight.FrostDelight;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FrostDelight.MOD_ID);

    //food 食物
    public static final RegistryObject<Item> VANILLA_ICE_CREAM = register("food/vanilla_ice_cream");
    public static final RegistryObject<Item> CHOCOLATE_ICE_CREAM = register("food/chocolate_ice_cream");
    public static final RegistryObject<Item> SWEET_BERRY_ICE_CREAM = register("food/sweet_berry_ice_cream");
    public static final RegistryObject<Item> WATERMELON_ICE_CREAM = register("food/watermelon_ice_cream");
    public static final RegistryObject<Item> HONEY_ICE_CREAM = register("food/honey_ice_cream");

    public static final RegistryObject<Item> MILK_POPSICLE = register("food/milk_popsicle");
    public static final RegistryObject<Item> APPLE_POPSICLE = register("food/apple_popsicle");
    public static final RegistryObject<Item> WATERMELON_POPSICLE = register("food/watermelon_popsicle");
    public static final RegistryObject<Item> SWEET_BERRY_POPSICLE = register("food/sweet_berry_popsicle");
    public static final RegistryObject<Item> HONEY_POPSICLE = register("food/honey_popsicle");

    //material 材料
    public static final RegistryObject<Item> SUGAR_SYRUP = register("material/sugar_syrup");
    public static final RegistryObject<Item> CREAM = register("material/cream");
    public static final RegistryObject<Item> BUTTER = register("material/butter");
    public static final RegistryObject<Item> VANILLA_FLAVORING = register("material/vanilla_flavoring");
    public static final RegistryObject<Item> ICE_SHARD = register("material/ice_shard");

    //tool 工具
    public static final RegistryObject<Item> ICE_CREAM_BOWL = register("tool/ice_cream_bowl");
    public static final RegistryObject<Item> POPSICLE_STICK = register("tool/popsicle_stick");
    public static final RegistryObject<Item> POPSICLE_MOLD = register("tool/popsicle_mold");
    public static final RegistryObject<Item> POPSICLE_SLEEVE = register("tool/popsicle_sleeve");

    //方块
    public static RegistryObject<Item> ICE_CREAM_MACHINE = ITEMS.register("ice_cream_machine", () -> new BlockItem(ModBlocks.ICE_CREAM_MACHINE.get(), new Item.Properties()));
    public static RegistryObject<Item> FREEZING_MACHINE = ITEMS.register("freezing_machine", () -> new BlockItem(ModBlocks.FREEZING_MACHINE.get(), new Item.Properties()));
    public static RegistryObject<Item> MIXER = ITEMS.register("mixer", () -> new BlockItem(ModBlocks.MIXER.get(), new Item.Properties()));
    public static RegistryObject<Item> ICE_CREAM_SCOOP = ITEMS.register("ice_cream_scoop", () -> new BlockItem(ModBlocks.ICE_CREAM_SCOOP.get(), new Item.Properties()));

    private static RegistryObject<Item> register(String name) {
        return ITEMS.register(name, () -> new Item(new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
