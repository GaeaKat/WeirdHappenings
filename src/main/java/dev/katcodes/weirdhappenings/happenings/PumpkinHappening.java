package dev.katcodes.weirdhappenings.happenings;

import dev.katcodes.weirdhappenings.HappeningsMod;
import dev.katcodes.weirdhappenings.utils.BlockUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.PumpkinBlock;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class PumpkinHappening extends HappeningBase {
    @Override
    public String getName() {
        return "pumpkin";
    }

    @Override
    public boolean runHappening(Player player) {
        for(int i=0;i<10;i++) {
            BlockPos pumpkin = BlockUtils.FindBlock(player, 10, CarvedPumpkinBlock.class, false, false);
            if (pumpkin != null) {
                BlockPos playerPos = player.getOnPos().above();
                BlockPos difference = playerPos.subtract(pumpkin);
                HappeningsMod.LOGGER.info("Difference is :"+difference);
                Direction dirFace=null;
                int absX=Math.abs(difference.getX());
                int absZ=Math.abs(difference.getZ());
                if(absX>absZ) {
                    if (difference.getX() < 0) {
                        dirFace = Direction.WEST;
                    } else {
                        if(difference.getX() > 0) {
                            dirFace = Direction.EAST;
                        }
                    }
                }
                else if(absX < absZ) {
                    if (difference.getZ() < 0) {
                        dirFace = Direction.NORTH;
                    } else {
                        if(difference.getZ() > 0) {
                            dirFace = Direction.SOUTH;
                        }
                    }
                }
                else {
                    if(difference.getX() < 0)
                        dirFace=Direction.SOUTH;
                    else if(difference.getX() > 0)
                        dirFace = Direction.NORTH;
                }
                if(dirFace!=null) {
                    BlockState pumpkinState = player.level.getBlockState(pumpkin);
                    pumpkinState = pumpkinState.setValue(CarvedPumpkinBlock.FACING,dirFace);
                    player.level.setBlock(pumpkin, pumpkinState, 10);
                }
                return true;
            }
        }
    return false;
    }
}
