package com.eightsidedsquare.angling.client.datagen;

import com.eightsidedsquare.angling.core.world.AnglingConfiguredFeatures;
import com.eightsidedsquare.angling.core.world.AnglingPlacedFeatures;
import com.eightsidedsquare.angling.client.datagen.provider.AnglingWorldGenProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class AnglingDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, AnglingConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, AnglingPlacedFeatures::bootstrap);
    }

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(AnglingWorldGenProvider::new);

    }
}
