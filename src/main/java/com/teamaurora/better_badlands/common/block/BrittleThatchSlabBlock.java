package com.teamaurora.better_badlands.common.block;

import com.teamabnormals.abnormals_core.common.blocks.thatch.ThatchSlabBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

@SuppressWarnings("deprecated")
public class BrittleThatchSlabBlock extends ThatchSlabBlock implements IBrittleThatch {
    public static final IntegerProperty BURN_DISTANCE = IBrittleThatch.BURN_DISTANCE;
    public static final IntegerProperty BURN_TIMER = IntegerProperty.create("burn_timer", 0, 120);
    public static final EnumProperty<SlabType> TYPE = SlabBlock.TYPE;
    public static final BooleanProperty WATERLOGGED = SlabBlock.WATERLOGGED;

    public BrittleThatchSlabBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(BURN_DISTANCE, 0).with(BURN_TIMER, 0).with(TYPE, SlabType.BOTTOM).with(WATERLOGGED, Boolean.valueOf(false)));
    }

    @Override
    public boolean ticksRandomly(BlockState state) {
        return true;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BURN_DISTANCE, BURN_TIMER, TYPE, WATERLOGGED);
    }

    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        this.onBlockAddedI(state, worldIn, pos, oldState, isMoving);
        super.onBlockAdded(state, worldIn, pos, oldState, isMoving);
    }

    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        this.neighborChangedI(state, worldIn, pos, blockIn, fromPos, isMoving);
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
    }

    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        this.tickI(state, worldIn, pos, rand);
        super.tick(state, worldIn, pos, rand);
    }

    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        return this.onBlockActivatedI(state, worldIn, pos, player, handIn, hit);
    }

}
