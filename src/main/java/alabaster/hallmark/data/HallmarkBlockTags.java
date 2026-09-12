package alabaster.hallmark.data;

import alabaster.hallmark.Hallmark;
import alabaster.hallmark.common.registry.HallmarkModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class HallmarkBlockTags extends BlockTagsProvider {
    public HallmarkBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Hallmark.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.registerModTags();
        this.registerMinecraftTags();
        this.registerCommonTags();
        this.registerCompatTags();
        this.registerBlockMineables();
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
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(HallmarkModBlocks.MINTING_PRESS.get());
    }
}
