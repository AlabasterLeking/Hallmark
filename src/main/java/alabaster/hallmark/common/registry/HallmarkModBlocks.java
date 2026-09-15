package alabaster.hallmark.common.registry;

import alabaster.hallmark.Hallmark;
import alabaster.hallmark.common.block.MintingPressBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class HallmarkModBlocks {
    public static final MintingPressBlock MINTING_PRESS = register("minting_press",
            new MintingPressBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)));

    private static <T extends net.minecraft.world.level.block.Block> T register(String name, T block) {
        return Registry.register(BuiltInRegistries.BLOCK, Hallmark.id(name), block);
    }

    public static void register() {
    }
}
