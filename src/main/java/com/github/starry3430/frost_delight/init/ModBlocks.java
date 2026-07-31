
package com.github.starry3430.frost_delight.init;

import com.github.starry3430.frost_delight.FrostDelight;
import com.github.starry3430.frost_delight.block.IceCreamMachine;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, FrostDelight.MOD_ID);
    //注册方块
    public static final RegistryObject<Block> ICE_CREAM_MACHINE = BLOCKS.register("machine/ice_cream_machine", IceCreamMachine::new);
    public static final RegistryObject<Block> FREEZING_MACHINE = registerBlock("machine/freezing_machine",
            () -> new Block(BlockBehaviour.Properties.of().strength(2.5F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MIXER = registerBlock("machine/mixer",
            () -> new Block(BlockBehaviour.Properties.of().strength(2.5F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ICE_CREAM_SCOOP = registerBlock("food/ice_cream_scoop",
            () -> new Block(BlockBehaviour.Properties.of().strength(1.0F).requiresCorrectToolForDrops()));

    //---------------------------------------------------------------------------------------------------------//

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> registeredBlock = BLOCKS.register(name, block);
        registerBlockItem(name, registeredBlock);
        return registeredBlock;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
