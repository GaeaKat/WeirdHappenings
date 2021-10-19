package dev.katcodes.weirdhappenings.happenings;

import net.minecraft.world.entity.player.Player;

public abstract class HappeningBase {

    public abstract String getName();

    public abstract boolean runHappening(Player player);

    public boolean isClientSide() {
        return false;
    }
    public double getWeight() {
        return 1.0f;
    }
}
