package alabaster.hallmark.common.registry;

import alabaster.hallmark.Hallmark;
import alabaster.hallmark.common.block.entity.MintingPressBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class HallmarkModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Hallmark.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MintingPressBlockEntity>> MINTING_PRESS =
            BLOCK_ENTITIES.register("minting_press", () -> BlockEntityType.Builder
                    .of(MintingPressBlockEntity::new, HallmarkModBlocks.MINTING_PRESS.get())
                    .build(null));

    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }
}