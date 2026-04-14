package com.eightsidedsquare.angling.core.world;

import com.eightsidedsquare.angling.common.feature.NoisePatchFeatureConfig;
import com.eightsidedsquare.angling.core.AnglingBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

import static com.eightsidedsquare.angling.core.AnglingMod.MOD_ID;

public class  AnglingConfiguredFeatures {

    private static final WeightedBlockStateProvider PAPYRUS_BLOCK_STATE_PROVIDER = new WeightedBlockStateProvider(
            DataPool.<BlockState>builder()
                    .add(AnglingBlocks.PAPYRUS.getDefaultState().with(Properties.AGE_2, 0), 1)
                    .add(AnglingBlocks.PAPYRUS.getDefaultState().with(Properties.AGE_2, 1), 2)
                    .add(AnglingBlocks.PAPYRUS.getDefaultState().with(Properties.AGE_2, 2), 3).build()
    );

    public static final RegistryKey<ConfiguredFeature<?, ?>> OYSTER_REEF = of("oyster_reef");

    public static final RegistryKey<ConfiguredFeature<?, ?>> CLAMS = of("clams");

    public static final RegistryKey<ConfiguredFeature<?, ?>> WORMY_BLOCK = of("wormy_block");

    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_SARGASSUM = of("patch_sargassum");

    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_DUCKWEED = of("patch_duckweed");

    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_PAPYRUS = of("patch_papyrus");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> featureRegisterable) {

        ConfiguredFeatures.register(featureRegisterable, OYSTER_REEF, AnglingFeatures.WATERLOGGABLE_PATCH,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(AnglingBlocks.OYSTERS)));

        ConfiguredFeatures.register(featureRegisterable, CLAMS, AnglingFeatures.WATERLOGGABLE_PATCH,
                new SimpleBlockFeatureConfig(
                        new WeightedBlockStateProvider(DataPool.<BlockState>builder()
                                .add(AnglingBlocks.CLAM.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH), 1)
                                .add(AnglingBlocks.CLAM.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.EAST), 1)
                                .add(AnglingBlocks.CLAM.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.SOUTH), 1)
                                .add(AnglingBlocks.CLAM.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.WEST), 1)
                                .build())));

        ConfiguredFeatures.register(featureRegisterable, WORMY_BLOCK, AnglingFeatures.WORMY_BLOCK, new DefaultFeatureConfig());

        ConfiguredFeatures.register(featureRegisterable, PATCH_SARGASSUM, AnglingFeatures.NOISE_PATCH, new NoisePatchFeatureConfig(
                BlockStateProvider.of(AnglingBlocks.SARGASSUM),
                -3,
                2d,
                0.25d,
                UniformIntProvider.create(8, 16)
        ));

        ConfiguredFeatures.register(featureRegisterable, PATCH_DUCKWEED, AnglingFeatures.NOISE_PATCH, new NoisePatchFeatureConfig(
                BlockStateProvider.of(AnglingBlocks.DUCKWEED),
                -2,
                2d,
                0.35d,
                UniformIntProvider.create(6, 12)
        ));

        ConfiguredFeatures.register(featureRegisterable, PATCH_PAPYRUS, AnglingFeatures.WATER_ADJACENT_PATCH, new RandomPatchFeatureConfig(
                64,
                6,
                2,
                PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(PAPYRUS_BLOCK_STATE_PROVIDER))
        ));
    }

    public static void init() {
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> of(String id) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(MOD_ID,id));
    }
}
