package com.eightsidedsquare.angling.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.block.FluidRenderer;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockRenderView;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FluidRenderer.class)
public abstract class FluidRendererMixin {

    @WrapMethod(method = "shouldRenderSide")
    private static boolean shouldRenderSide(BlockRenderView world, BlockPos pos, FluidState fluidState, BlockState blockState, Direction direction, FluidState neighborFluidState, Operation<Boolean> original) {
        if(fluidState.isIn(FluidTags.WATER))
            if(world.getBlockState(pos.offset(direction)).isIn(ConventionalBlockTags.GLASS_BLOCKS))
                return false;
        return original.call(world, pos, fluidState, blockState, direction, neighborFluidState);
    }

}
