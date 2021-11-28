package com.stal111.valhelsia_structures.mixin;

import com.stal111.valhelsia_structures.block.DousedWallTorchBlock;
import com.stal111.valhelsia_structures.config.BlockConfig;
import com.stal111.valhelsia_structures.utils.TorchTransformationHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.TorchBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

/**
 * Bucket Item Mixin
 * Valhelsia Structures - com.stal111.valhelsia_structures.mixin.BucketItemMixin
 *
 * @author Valhelsia Team
 * @version 0.1.3
 * @since 2021-01-09
 */
@Mixin(BucketItem.class)
public abstract class BucketItemMixin {

    @Shadow
    @Final
    private Fluid containedBlock;

    @Shadow
    protected abstract void playEmptySound(@Nullable PlayerEntity player, IWorld worldIn, BlockPos pos);

    @Inject(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/material/Material;isLiquid()Z",
                    shift = At.Shift.BEFORE), method = "tryPlaceContainedLiquid",
            cancellable = true
    )
    private void valhelsia_placeDousedTorch(PlayerEntity player, World world, BlockPos pos, BlockRayTraceResult rayTrace, CallbackInfoReturnable<Boolean> cir) {
        BlockState state = world.getBlockState(pos);

        if (this.containedBlock == Fluids.WATER && !BlockConfig.DISABLE_DOUSED_TORCH.get()) {
            if (TorchTransformationHandler.hasDousedVersion(state.getBlock())) {
                BlockState newState = TorchTransformationHandler.getDousedTorchFor(state.getBlock()).getDefaultState().with(BlockStateProperties.WATERLOGGED, true);

                if (newState.getBlock() instanceof DousedWallTorchBlock) {
                    newState = newState.with(HorizontalBlock.HORIZONTAL_FACING, state.get(HorizontalBlock.HORIZONTAL_FACING));
                }
                if (!world.isRemote()) {
                    world.setBlockState(pos, newState.with(BlockStateProperties.WATERLOGGED, true), 3);
                    world.getPendingFluidTicks().scheduleTick(pos, this.containedBlock, this.containedBlock.getTickRate(world));
                }
                playEmptySound(player, world, pos);

                cir.setReturnValue(true);
            }
        }
    }

    @Inject(at = @At(value = "HEAD"), method = "canBlockContainFluid", cancellable = true, remap = false)
    private void valhelsia_canBlockContainFluid(World world, BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        Block block = state.getBlock();
        if (block instanceof TorchBlock && containedBlock == Fluids.WATER && !BlockConfig.DISABLE_DOUSED_TORCH.get()) {
            cir.setReturnValue(true);
        }
    }
}
