package dev.katcodes.weirdhappenings.happenings;

import dev.katcodes.weirdhappenings.utils.BlockUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraftforge.common.util.Constants;

import java.util.Random;

public class TorchHappening extends HappeningBase {
    @Override
    public String getName() {
        return "torch";
    }

    @Override
    public boolean runHappening(Player player) {
        BlockPos torch= BlockUtils.FindBlock(player,10, TorchBlock.class,false);
        if(torch!=null) {
            int attempts=0;
            while(attempts < 50) {
                attempts++;
                for (BlockPos newPos : BlockPos.randomInCube(new Random(), 1, torch, 3)) {
                    if(player.level.getBlockState(newPos).isAir() && Blocks.TORCH.canSurvive(player.level.getBlockState(torch),player.level,newPos)) {
                        player.level.setBlock(newPos, Blocks.TORCH.defaultBlockState(), Constants.BlockFlags.DEFAULT_AND_RERENDER);
                        player.level.setBlock(torch, Blocks.AIR.defaultBlockState(), Constants.BlockFlags.DEFAULT_AND_RERENDER);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
