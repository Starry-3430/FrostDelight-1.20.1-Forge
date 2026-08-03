package com.github.starry3430.frost_delight.datagen;

import com.github.starry3430.frost_delight.FrostDelight;
import com.github.starry3430.frost_delight.init.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, FrostDelight.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
    tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(
                    ModBlocks.ICE_CREAM_MACHINE.get(),
                    ModBlocks.FREEZING_MACHINE.get(),
                    ModBlocks.ICE_CREAM_MIXER.get()
            );
    }
}