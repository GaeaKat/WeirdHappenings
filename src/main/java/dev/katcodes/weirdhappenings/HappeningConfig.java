package dev.katcodes.weirdhappenings;


import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class HappeningConfig {
    public static class Common
    {
        private static final float percentPerTickDefault = 0.01f;

        public final ForgeConfigSpec.ConfigValue<Float> percentPerTick;
        public final ForgeConfigSpec.ConfigValue<Double> candlesWeight;
        public final ForgeConfigSpec.ConfigValue<Double> chestsWeight;
        public final ForgeConfigSpec.ConfigValue<Double> doorsWeight;
        public final ForgeConfigSpec.ConfigValue<Double> torchesWeight;
        public final ForgeConfigSpec.ConfigValue<Double> trapdoorsWeight;


        public Common(ForgeConfigSpec.Builder builder)
        {
            builder.push("Settings");
            this.percentPerTick = builder.comment("What percent per tick should the spooky event happen. Warning, best not put this above 1..")
                    .worldRestart().define("percentChance",percentPerTickDefault);
            this.candlesWeight = builder.comment("Candle flicker weight")
                    .worldRestart().define("candlesWeight",1.0);
            this.chestsWeight = builder.comment("Chest open chance")
                    .worldRestart().define("chestsWeight",1.0);
            this.doorsWeight = builder.comment("Doors open chance")
                    .worldRestart().define("doorsWeight",1.0);
            this.torchesWeight = builder.comment("Torch move chance")
                    .worldRestart().define("torchesWeight",1.0);
            this.trapdoorsWeight = builder.comment("Trapdoor open chance")
                    .worldRestart().define("trapdoorsWeight",1.0);

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
