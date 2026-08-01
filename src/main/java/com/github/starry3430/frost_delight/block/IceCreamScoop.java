package com.github.starry3430.frost_delight.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class IceCreamScoop extends Block {

    public static final int SCOOP_MAX_BITES = 3;
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SCOOP_BITES);
    }
    public static final IntegerProperty SCOOP_BITES = IntegerProperty.create("scoop_bites", 0, SCOOP_MAX_BITES);

    public IceCreamScoop() {
        super(Properties.of()
                .mapColor(MapColor.STONE)
                .sound(SoundType.SNOW)
                .strength(0.5F, 1.0F)
                .noOcclusion()
        );
        // 最初的bites为0
        this.registerDefaultState(this.stateDefinition.any().setValue(SCOOP_BITES, 0));
    }

    //---------------------------------------------------------------------------------------------//
    // 碰撞箱，吃掉最后一口之后模拟冰激凌球消失，只留下一个碗
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_BITE[state.getValue(SCOOP_BITES)];
    }

    private static final VoxelShape[] SHAPE_BY_BITE = new VoxelShape[]{
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 10.0D, 14.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 10.0D, 14.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 10.0D, 14.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 5.0D, 14.0D),
    };

    //---------------------------------------------------------------------------------------------//
    // 进食
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
                                 InteractionHand hand, BlockHitResult hit) {
        return eat(level, pos, state, player);
    }

    protected static InteractionResult eat(LevelAccessor level, BlockPos pos, BlockState state, Player player) {

        // 播放吃音效和粒子效果
        level.gameEvent(player, GameEvent.EAT, pos);
        // 吃冰激凌的次数
        player.awardStat(Stats.EAT_CAKE_SLICE);

        // 恢复饥饿值
        if(state.getValue(SCOOP_BITES) < SCOOP_MAX_BITES) {
            player.getFoodData().eat(3, 0.2F);
        }

        // 获取当前剩余口数
        int currentBites = state.getValue(SCOOP_BITES);

        if (currentBites < SCOOP_MAX_BITES) {
            // 如果不是最后一口，将 BITES + 1，更新方块状态
            level.setBlock(pos, state.setValue(SCOOP_BITES, currentBites + 1), 3);
        } else {
            // 如果是最后一口，吃完整个方块，移除它并掉落木碗
            if (level instanceof ServerLevel serverLevel) {
                // 创建一个木碗物品实体
                ItemEntity bowlEntity = new ItemEntity(
                        serverLevel,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        new ItemStack(Items.BOWL)
                );
                serverLevel.addFreshEntity(bowlEntity);
            }
            level.removeBlock(pos, false);
            level.gameEvent(player, GameEvent.BLOCK_DESTROY, pos);
        }

        return InteractionResult.SUCCESS;
    }

    //---------------------------------------------------------------------------------------------//
    // 处理掉落机制
    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state,
                              @Nullable BlockEntity blockEntity, ItemStack tool) {
        // 创造模式不掉落任何物品
        if (!player.isCreative()) {
            List<ItemStack> drops = new ArrayList<>();

            if (state.getValue(SCOOP_BITES) == 0) {
                // 没有吃过则掉落冰激凌球自身
                popResource(level, pos, new ItemStack(this.asItem()));
            } else {
                // 被咬过则只掉落木碗
                popResource(level, pos, new ItemStack(Items.BOWL));
            }
        }

    }

    //---------------------------------------------------------------------------------------------//
    // 冰激凌球下面必须有方块
    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).isSolid();
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock,
                                BlockPos fromPos, boolean isMoving) {

        if (!level.isClientSide) {
            if (!state.canSurvive(level, pos)) {
                int bites = state.getValue(SCOOP_BITES);
                ItemStack dropItem;
                if (bites == 0) {
                    dropItem = new ItemStack(this.asItem());
                } else {
                    dropItem = new ItemStack(Items.BOWL);
                }
                // 在方块位置生成掉落物
                popResource(level, pos, dropItem);
                // 移除方块
                level.removeBlock(pos, false);
            }
        }
        super.neighborChanged(state, level, pos, neighborBlock, fromPos, isMoving);
    }
    //---------------------------------------------------------------------------------------------//
}
