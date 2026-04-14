package com.eightsidedsquare.angling.core.world;

import com.eightsidedsquare.angling.core.tags.AnglingBiomeTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;

import static com.eightsidedsquare.angling.core.AnglingMod.MOD_ID;

public class AnglingPlacedFeatures {

    public static final RegistryKey<PlacedFeature> OYSTER_REEF = of("oyster_reef");

    public static final RegistryKey<PlacedFeature> CLAMS = of("clams");

    public static final RegistryKey<PlacedFeature> WORMY_BLOCK = of("wormy_block");

    public static final RegistryKey<PlacedFeature> PATCH_SARGASSUM = of("patch_sargassum");

    public static final RegistryKey<PlacedFeature> PATCH_DUCKWEED = of("patch_duckweed");

    public static final RegistryKey<PlacedFeature> PATCH_PAPYRUS = of("patch_papyrus");

    private static void addFeature(RegistryKey<PlacedFeature> key, GenerationStep.Feature step, TagKey<Biome> tag) {
        BiomeModifications.addFeature(ctx -> ctx.getBiomeRegistryEntry().isIn(tag), step, key);
    }

    public static void bootstrap(Registerable<PlacedFeature> featureRegisterable) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        PlacedFeatures.register(featureRegisterable, OYSTER_REEF,
                registryEntryLookup.getOrThrow(AnglingConfiguredFeatures.OYSTER_REEF),
                RarityFilterPlacementModifier.of(14),
                SquarePlacementModifier.of(),
                PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                BiomePlacementModifier.of());

        PlacedFeatures.register(featureRegisterable, CLAMS,
                registryEntryLookup.getOrThrow(AnglingConfiguredFeatures.CLAMS),
                RarityFilterPlacementModifier.of(12),
                SquarePlacementModifier.of(),
                PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                BiomePlacementModifier.of());

        PlacedFeatures.register(featureRegisterable, WORMY_BLOCK,
                registryEntryLookup.getOrThrow(AnglingConfiguredFeatures.WORMY_BLOCK),
                CountPlacementModifier.of(2),
                SquarePlacementModifier.of(),
                PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                BiomePlacementModifier.of());

        PlacedFeatures.register(featureRegisterable, PATCH_SARGASSUM,
                registryEntryLookup.getOrThrow(AnglingConfiguredFeatures.PATCH_SARGASSUM),
                RarityFilterPlacementModifier.of(70),
                SquarePlacementModifier.of(),
                PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                BiomePlacementModifier.of());

        PlacedFeatures.register(featureRegisterable, PATCH_DUCKWEED,
                registryEntryLookup.getOrThrow(AnglingConfiguredFeatures.PATCH_DUCKWEED),
                RarityFilterPlacementModifier.of(3),
                SquarePlacementModifier.of(),
                PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                BiomePlacementModifier.of());

        PlacedFeatures.register(featureRegisterable, PATCH_PAPYRUS,
                registryEntryLookup.getOrThrow(AnglingConfiguredFeatures.PATCH_PAPYRUS),
                CountPlacementModifier.of(2),
                SquarePlacementModifier.of(),
                PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                BiomePlacementModifier.of());
    }

    public static void init() {
        addFeature(OYSTER_REEF, GenerationStep.Feature.VEGETAL_DECORATION, AnglingBiomeTags.OYSTER_REEF_BIOMES);
        addFeature(CLAMS, GenerationStep.Feature.VEGETAL_DECORATION, AnglingBiomeTags.CLAMS_BIOMES);
        addFeature(PATCH_DUCKWEED, GenerationStep.Feature.VEGETAL_DECORATION, AnglingBiomeTags.DUCKWEED_BIOMES);
        addFeature(PATCH_SARGASSUM, GenerationStep.Feature.VEGETAL_DECORATION, AnglingBiomeTags.SARGASSUM_BIOMES);
        addFeature(PATCH_PAPYRUS, GenerationStep.Feature.VEGETAL_DECORATION, AnglingBiomeTags.PAPYRUS_BIOMES);
        addFeature(WORMY_BLOCK, GenerationStep.Feature.UNDERGROUND_ORES, BiomeTags.IS_OVERWORLD);
    }

    public static RegistryKey<PlacedFeature> of(String id) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(MOD_ID,id));
    }
}
