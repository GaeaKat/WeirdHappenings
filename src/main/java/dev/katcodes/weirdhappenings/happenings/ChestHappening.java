package dev.katcodes.weirdhappenings.happenings;

import dev.katcodes.weirdhappenings.HappeningConfig;
import dev.katcodes.weirdhappenings.utils.BlockUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.ChestBlockEntity;

public class ChestHappening extends HappeningBase {
    @Override
    public String getName() {
        return "chest";
    }

    @Override
    public boolean runHappening(Player player) {
        BlockPos chestPos= BlockUtils.FindBlock(player,10, ChestBlock.class,false);
        if(chestPos!=null) {

            ChestBlock chestBlock= (ChestBlock) player.level.getBlockState(chestPos).getBlock();
            if(player.level.getBlockEntity(chestPos) instanceof ChestBlockEntity) {
                ChestBlockEntity entity = (ChestBlockEntity) player.level.getBlockEntity(chestPos);
                entity.startOpen(player);
            }
        }
        return false;
    }

    @Override
    public boolean isClientSide() {
        return true;
    }

    @Override
    public double getWeight() {
        return HappeningConfig.COMMON.chestsWeight.get();
    }
}
