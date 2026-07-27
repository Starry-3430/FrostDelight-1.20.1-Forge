package com.starry3430.frost_delight.item;

import com.starry3430.frost_delight.FrostDelight;
import com.starry3430.frost_delight.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MOD_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FrostDelight.MOD_ID);

    public static final RegistryObject<CreativeModeTab> FROST_DELIGHT_TAB =
            CREATIVE_MOD_TABS.register("frost_delight_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.frost_delight_tab"))
                    .icon(() -> new ItemStack(ModItems.SUGAR_SYRUP.get()))
                    .displayItems((pParameters, output) -> {
                        //物品
                        output.accept(ModItems.VANILLA_ICE_CREAM.get());
                        output.accept(ModItems.CHOCOLATE_ICE_CREAM.get());
                        output.accept(ModItems.SWEET_BERRY_ICE_CREAM.get());
                        output.accept(ModItems.WATERMELON_ICE_CREAM.get());
                        output.accept(ModItems.HONEY_ICE_CREAM.get());

                        output.accept(ModItems.MILK_POPSICLE.get());
                        output.accept(ModItems.APPLE_POPSICLE.get());
                        output.accept(ModItems.WATERMELON_POPSICLE.get());
                        output.accept(ModItems.SWEET_BERRY_POPSICLE.get());
                        output.accept(ModItems.HONEY_POPSICLE.get());

                        output.accept(ModItems.SUGAR_SYRUP.get());
                        output.accept(ModItems.CREAM.get());
                        output.accept(ModItems.BUTTER.get());
                        output.accept(ModItems.VANILLA_FLAVORING.get());
                        output.accept(ModItems.ICE_SHARD.get());
                        output.accept(ModItems.ICE_CREAM_BOWL.get());
                        output.accept(ModItems.POPSICLE_STICK.get());
                        output.accept(ModItems.POPSICLE_MOLD.get());
                        output.accept(ModItems.POPSICLE_SLEEVE.get());

                        //方块
                        output.accept(ModBlocks.ICE_CREAM_MACHINE.get());
                        output.accept(ModBlocks.FREEZING_MACHINE.get());
                        output.accept(ModBlocks.MIXER.get());
                        output.accept(ModBlocks.ICE_CREAM_SCOOP.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MOD_TABS.register(eventBus);
    }
}
