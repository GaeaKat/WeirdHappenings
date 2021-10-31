package dev.katcodes.weirdhappenings.happenings;

import dev.katcodes.weirdhappenings.HappeningsMod;
import dev.katcodes.weirdhappenings.utils.BlockUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;

import javax.annotation.Nullable;

import static net.minecraft.world.level.block.TrapDoorBlock.OPEN;
import static net.minecraft.world.level.block.TrapDoorBlock.WATERLOGGED;


public class TrapdoorHappening extends HappeningBase {
    @Override
    public String getName() {
        return "trapdoors";
    }

    @Override
    public boolean runHappening(Player player) {
        BlockPos door=BlockUtils.FindBlock(player,10, TrapDoorBlock.class,false);
        if(door!=null) {
            //HappeningsMod.LOGGER.info("Found TrapDoor at "+door);
            BlockState doorState=player.level.getBlockState(door);
            TrapDoorBlock doorBlock= (TrapDoorBlock) doorState.getBlock();
            doorState = doorState.cycle(OPEN);
            player.level.setBlock(door, doorState, 2);
            if (doorState.getValue(WATERLOGGED)) {
                player.level.getLiquidTicks().scheduleTick(door, Fluids.WATER, Fluids.WATER.getTickDelay(player.level));
            }

            this.playSound(player, player.level, door, doorState.getValue(OPEN));
        }
        return true;
    }

    protected void playSound(@Nullable Player p_57528_, Level p_57529_, BlockPos p_57530_, boolean p_57531_) {
        if (p_57531_) {
            int i =  1007;
            p_57529_.levelEvent(p_57528_, i, p_57530_, 0);
        } else {
            int j = 1013;
            p_57529_.levelEvent(p_57528_, j, p_57530_, 0);
        }

        p_57529_.gameEvent(p_57528_, p_57531_ ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, p_57530_);
    }
    private int getCloseSound() {
        return 1012;
    }

    private int getOpenSound() {
        return  1006;
    }

}
