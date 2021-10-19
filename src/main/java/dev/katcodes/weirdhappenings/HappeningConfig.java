package dev.katcodes.weirdhappenings;


import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class HappeningConfig {
    public static class Common
    {
        private static final float percentPerTickDefault = 0.01f;

        public final ForgeConfigSpec.ConfigValue<Float> percentPerTick;


        public Common(ForgeConfigSpec.Builder builder)
        {
            builder.push("Settings");
            this.percentPerTick = builder.comment("What percent per tick should the spooky event happen. Warning, best not put this above 1..")
                    .worldRestart().define("percentChance",percentPerTickDefault);

            builder.pop();
        }
    }
    public static final Common COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;

    static //constructor
    {
        Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON = commonSpecPair.getLeft();
        COMMON_SPEC = commonSpecPair.getRight();
    }

}
