package alabaster.hallmark.data;

import alabaster.hallmark.common.registry.HallmarkModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class HallmarkBlockTags extends FabricTagProvider.BlockTagProvider {
    public HallmarkBlockTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        registerModTags();
        registerMinecraftTags();
        registerCommonTags();
        registerCompatTags();
        registerBlockMineables();
    }

    protected void registerModTags() {
    }

    protected void registerMinecraftTags() {
    }

    protected void registerCommonTags() {
    }

    protected void registerCompatTags() {
    }

    protected void registerBlockMineables() {
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).add(HallmarkModBlocks.MINTING_PRESS);
    }
}
