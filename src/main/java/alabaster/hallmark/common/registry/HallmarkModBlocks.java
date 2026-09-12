package alabaster.hallmark.common.registry;

import alabaster.hallmark.Hallmark;
import alabaster.hallmark.common.block.MintingPressBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class HallmarkModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Hallmark.MODID);

    public static final DeferredBlock<MintingPressBlock> MINTING_PRESS = BLOCKS.register("minting_press",
            () -> new MintingPressBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)));
}