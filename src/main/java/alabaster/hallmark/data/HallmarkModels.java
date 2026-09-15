package alabaster.hallmark.data;

import alabaster.hallmark.common.registry.HallmarkModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelLocationUtils;

public class HallmarkModels extends FabricModelProvider {
    public HallmarkModels(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generators) {
        generators.delegateItemModel(HallmarkModBlocks.MINTING_PRESS,
                ModelLocationUtils.getModelLocation(HallmarkModBlocks.MINTING_PRESS));
    }

    @Override
    public void generateItemModels(ItemModelGenerators generators) {
    }
}
