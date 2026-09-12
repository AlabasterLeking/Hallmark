package alabaster.hallmark.common.registry;

import alabaster.hallmark.Hallmark;
import alabaster.hallmark.common.item.component.MintedData;
import alabaster.hallmark.common.item.component.StampData;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class HallmarkModComponents {
    public static final DeferredRegister.DataComponents DATA_COMPONENTS =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Hallmark.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<StampData>> STAMP =
            DATA_COMPONENTS.registerComponentType("stamp", b -> b
                    .persistent(StampData.CODEC)
                    .networkSynchronized(StampData.STREAM_CODEC));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<MintedData>> MINTED =
            DATA_COMPONENTS.registerComponentType("minted", b -> b
                    .persistent(MintedData.CODEC)
                    .networkSynchronized(MintedData.STREAM_CODEC));
}