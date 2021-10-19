package dev.katcodes.weirdhappenings;

import dev.katcodes.weirdhappenings.happenings.ModHappenings;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fmlserverevents.FMLServerStartingEvent;

public class Proxy {

    public void init(FMLCommonSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(EventHandler.class);
        ModHappenings.register();
    }
}
