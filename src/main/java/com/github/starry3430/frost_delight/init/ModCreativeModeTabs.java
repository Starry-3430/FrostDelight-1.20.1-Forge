package com.github.starry3430.frost_delight.init;

import com.github.starry3430.frost_delight.FrostDelight;
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

    // 设置物品
    public static final RegistryObject<CreativeModeTab> FROST_DELIGHT_TAB =
            CREATIVE_MOD_TABS.register("frost_delight_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.frost_delight_tab"))
                    .icon(() -> new ItemStack(ModItems.VANILLA_ICE_CREAM.get()))
                    .displayItems((pParameters, output) -> {
                        // 物品
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

                        // 方块
                        output.accept(ModItems.ICE_CREAM_MACHINE.get());
                        output.accept(ModItems.FREEZING_MACHINE.get());
                        output.accept(ModItems.ICE_CREAM_MIXER.get());
                        output.accept(ModItems.ICE_CREAM_SCOOP.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MOD_TABS.register(eventBus);
    }
}
