package alabaster.hallmark.common.registry;

import alabaster.hallmark.Hallmark;
import alabaster.hallmark.common.item.component.MintedData;
import alabaster.hallmark.common.item.component.StampData;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;

public class HallmarkModComponents {
    public static final DataComponentType<StampData> STAMP = register("stamp",
            DataComponentType.<StampData>builder()
                    .persistent(StampData.CODEC)
                    .networkSynchronized(StampData.STREAM_CODEC)
                    .build());

    public static final DataComponentType<MintedData> MINTED = register("minted",
            DataComponentType.<MintedData>builder()
                    .persistent(MintedData.CODEC)
                    .networkSynchronized(MintedData.STREAM_CODEC)
                    .build());

    private static <T> DataComponentType<T> register(String name, DataComponentType<T> type) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, Hallmark.id(name), type);
    }

    public static void register() {
    }
}
