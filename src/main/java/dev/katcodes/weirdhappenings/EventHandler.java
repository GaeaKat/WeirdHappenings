package dev.katcodes.weirdhappenings;

import dev.katcodes.weirdhappenings.commands.HappeningsArgument;
import dev.katcodes.weirdhappenings.commands.HappeningsCommand;
import dev.katcodes.weirdhappenings.happenings.HappeningBase;
import dev.katcodes.weirdhappenings.happenings.HappeningManager;
import net.minecraft.commands.synchronization.ArgumentTypes;
import net.minecraft.commands.synchronization.EmptyArgumentSerializer;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;

import java.io.Console;
import java.util.Random;

public class EventHandler {
    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        double rand=Math.random() * 100f;
        if(rand > 100f - 0.2f) {
            HappeningBase base= HappeningManager.GetHappening();
            if (base != null) {
                if (base.isClientSide() && playerTickEvent.side == LogicalSide.CLIENT) {
                    base.runHappening(playerTickEvent.player);
                } else if (playerTickEvent.side == LogicalSide.SERVER) {
                    base.runHappening(playerTickEvent.player);
                }
            }
        }
    }

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
       //ArgumentTypes.register("happening", HappeningsArgument.class,new EmptyArgumentSerializer<>(HappeningsArgument::happening));
       //HappeningsCommand.init(event.getDispatcher(),event.getEnvironment());
    }
}
