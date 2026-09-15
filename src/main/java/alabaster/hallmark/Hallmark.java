package alabaster.hallmark;

import alabaster.hallmark.common.event.HallmarkEvents;
import alabaster.hallmark.common.registry.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hallmark implements ModInitializer {
    public static final String MODID = "hallmark";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    @Override
    public void onInitialize() {
        HallmarkModComponents.register();
        HallmarkModBlocks.register();
        HallmarkModItems.register();
        HallmarkModBlockEntities.register();
        HallmarkModMenus.register();
        HallmarkModRecipeSerializers.register();
        HallmarkModCreativeTabs.register();
        HallmarkEvents.register();
        LOGGER.info("Hallmark is starting");
    }
}
