package com.github.starry3430.frost_delight.datagen;

import com.github.starry3430.frost_delight.FrostDelight;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = FrostDelight.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataGenerator {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new ModRecipesProvider(output));
        generator.addProvider(event.includeServer(), ModLootTableProvider.create(output));

        ModBlockTagsProvider blockTagsProvider = generator.addProvider(event.includeServer(),
                new ModBlockTagsProvider(output, lookupProvider, existingFileHelper));

        generator.addProvider(event.includeServer(),
                new ModItemTagsProvider(output, lookupProvider, blockTagsProvider.contentsGetter(),existingFileHelper));

        //generator.addProvider(event.includeClient(), new ModBlockStatesProvider(output, existingFileHelper));
        //generator.addProvider(event.includeClient(), new ModItemModelsProvider(output, existingFileHelper));
    }
}
