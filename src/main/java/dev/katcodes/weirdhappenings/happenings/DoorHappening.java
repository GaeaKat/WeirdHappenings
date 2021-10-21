package dev.katcodes.weirdhappenings.happenings;

import static net.minecraft.world.level.block.DoorBlock.OPEN;

import dev.katcodes.weirdhappenings.HappeningConfig;
import dev.katcodes.weirdhappenings.HappeningsMod;
import dev.katcodes.weirdhappenings.utils.BlockUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class DoorHappening extends HappeningBase {
  @Override
  public String getName() {
    return "doors";
  }

  @Override
  public boolean runHappening(Player player) {
    BlockPos door = BlockUtils.FindBlock(player, 10, DoorBlock.class, false);
    if (door != null) {
      HappeningsMod.LOGGER.info("Found Door at " + door);
      BlockState doorState = player.level.getBlockState(door);
      DoorBlock doorBlock = (DoorBlock)doorState.getBlock();
      doorState = doorState.cycle(OPEN);
      player.level.setBlock(door, doorState, 10);
      player.level.levelEvent(player,
                              doorState.getValue(OPEN) ? this.getOpenSound()
                                                       : this.getCloseSound(),
                              door, 0);
      player.level.gameEvent(player,
                             doorBlock.isOpen(doorState)
                                 ? GameEvent.BLOCK_OPEN
                                 : GameEvent.BLOCK_CLOSE,
                             door);
    }
    return true;
  }
  private int getCloseSound() { return 1012; }

  private int getOpenSound() { return 1006; }

  @Override
  public double getWeight() {
    return HappeningConfig.COMMON.doorsWeight.get();
  }
}
