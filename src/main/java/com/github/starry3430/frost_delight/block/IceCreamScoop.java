package com.github.starry3430.frost_delight.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

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
        player.getFoodData().eat(3, 0.2F);

        // 获取当前剩余口数
        int currentBites = state.getValue(SCOOP_BITES);

        if (currentBites < SCOOP_MAX_BITES) {
            // 如果不是最后一口，将 BITES + 1，更新方块状态
            level.setBlock(pos, state.setValue(SCOOP_BITES, currentBites + 1), 3);
        } else {
            // 如果是最后一口，吃完整个方块，移除它并掉落少量经验或物品
            level.removeBlock(pos, false);
            level.gameEvent(player, GameEvent.BLOCK_DESTROY, pos);
        }

        return InteractionResult.SUCCESS;
    }

    //---------------------------------------------------------------------------------------------//
    // 冰激凌球下面必须有方块
    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).isSolid();
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState,
                                  LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if (direction == Direction.DOWN && !state.canSurvive(level, currentPos)) {
            return Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
    }

    //---------------------------------------------------------------------------------------------//
}
