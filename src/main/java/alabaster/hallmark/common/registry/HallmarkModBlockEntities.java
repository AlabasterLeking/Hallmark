package alabaster.hallmark.common.registry;

import alabaster.hallmark.Hallmark;
import alabaster.hallmark.common.block.entity.MintingPressBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class HallmarkModBlockEntities {
    public static final BlockEntityType<MintingPressBlockEntity> MINTING_PRESS = Registry.register(
            BuiltInRegistries.BLOCK_ENTITY_TYPE,
            Hallmark.id("minting_press"),
            BlockEntityType.Builder.of(MintingPressBlockEntity::new, HallmarkModBlocks.MINTING_PRESS).build(null));

    public static void register() {
    }
}
