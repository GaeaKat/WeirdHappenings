package dev.katcodes.weirdhappenings.happenings;

public class ModHappenings {

    public static void  register() {
        HappeningManager.register(new DoorHappening());
        HappeningManager.register(new ChestHappening());
        HappeningManager.register(new TrapdoorHappening());
        HappeningManager.register(new CandleHappening());
        HappeningManager.register(new TorchHappening());
        HappeningManager.register(new PumpkinHappening());
    }
}
