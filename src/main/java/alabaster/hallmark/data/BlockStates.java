package alabaster.hallmark.data;

import alabaster.hallmark.Hallmark;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BlockStates extends BlockStateProvider
{
    private static final int DEFAULT_ANGLE_OFFSET = 180;

    public BlockStates(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Hallmark.MODID, existingFileHelper);
    }

    private String blockName(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    public ResourceLocation resourceBlock(String path) {
        return ResourceLocation.fromNamespaceAndPath(Hallmark.MODID, "block/" + path);
    }

    public ModelFile existingModel(String path) {
        return new ModelFile.ExistingModelFile(resourceBlock(path), models().existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

    }
    public void crateBlock(Block block, String cropName) {
        this.simpleBlock(block,
                models().cubeBottomTop(blockName(block),
                        resourceBlock(cropName + "_crate_side"),
                        resourceBlock("crate_bottom"),
                        resourceBlock(cropName + "_crate_top")));
    }
}