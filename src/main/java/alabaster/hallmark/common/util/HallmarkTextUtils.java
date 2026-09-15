package alabaster.hallmark.common.util;

import alabaster.hallmark.Hallmark;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class HallmarkTextUtils {
    public static MutableComponent getTranslation(String key, Object... args) {
        return Component.translatable(Hallmark.MODID + "." + key, args);
    }
}
