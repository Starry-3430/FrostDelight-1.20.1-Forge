package com.github.starry3430.frost_delight.datagen;

import com.github.starry3430.frost_delight.init.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTablesProvider extends BlockLootSubProvider {
    protected ModBlockLootTablesProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate(){
        dropSelf(ModBlocks.ICE_CREAM_MACHINE.get());
        dropSelf(ModBlocks.FREEZING_MACHINE.get());
        dropSelf(ModBlocks.ICE_CREAM_MIXER.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
