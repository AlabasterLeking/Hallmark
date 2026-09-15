package alabaster.hallmark.data;

import alabaster.hallmark.data.advancement.HallmarkAdvancementProvider;
import alabaster.hallmark.data.loot.HallmarkBlockLoot;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class HallmarkDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();
        pack.addProvider(HallmarkBlockTags::new);
        pack.addProvider(HallmarkItemTags::new);
        pack.addProvider(Recipes::new);
        pack.addProvider(HallmarkAdvancementProvider::new);
        pack.addProvider(HallmarkBlockLoot::new);
        pack.addProvider(HallmarkModels::new);
    }
}
