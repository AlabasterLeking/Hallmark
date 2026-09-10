package alabaster.hallmark;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.HashMap;
import java.util.Map;

@EventBusSubscriber(modid = Hallmark.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config {
    public static ModConfigSpec COMMON_CONFIG;
    private static final Map<String, ModConfigSpec.BooleanValue> ITEMS = new HashMap<>();

    public Config() {
    }


    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
    }

    static {
        ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();
        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}