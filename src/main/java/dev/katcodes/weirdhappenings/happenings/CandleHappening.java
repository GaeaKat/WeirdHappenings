package dev.katcodes.weirdhappenings.happenings;

import dev.katcodes.weirdhappenings.HappeningConfig;
import dev.katcodes.weirdhappenings.HappeningsMod;
import dev.katcodes.weirdhappenings.utils.BlockUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import static net.minecraft.world.level.block.AbstractCandleBlock.LIT;
import static net.minecraft.world.level.block.AbstractCandleBlock.extinguish;


public class CandleHappening extends HappeningBase{
    @Override
    public String getName() {
        return "candles";
    }

    @Override
    public boolean runHappening(Player player) {
        BlockPos candlePos= BlockUtils.FindBlock(player,10, AbstractCandleBlock.class,true);
        if(candlePos!=null) {
            HappeningsMod.LOGGER.info("Found Candle at "+candlePos);
            BlockState candleState=player.level.getBlockState(candlePos);
            CandleBlock candleBlock= (CandleBlock) candleState.getBlock();
            if(candleState.getValue(LIT)) {
                extinguish(player,candleState,player.level,candlePos);
            } else {
                player.level.setBlock(candlePos, candleState.setValue(LIT, Boolean.TRUE), 11);
                player.level.gameEvent(player, GameEvent.BLOCK_CHANGE, candlePos);
            }
            return true;
        }
        return false;
    }

    @Override
    public double getWeight() {
        return HappeningConfig.COMMON.candlesWeight.get();
    }
}
