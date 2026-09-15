package alabaster.hallmark.data.loot;

import alabaster.hallmark.common.registry.HallmarkModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class HallmarkBlockLoot extends FabricBlockLootTableProvider {
    public HallmarkBlockLoot(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate() {
        dropSelf(HallmarkModBlocks.MINTING_PRESS);
    }
}
