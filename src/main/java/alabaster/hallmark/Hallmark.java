package alabaster.hallmark;

import alabaster.hallmark.common.registry.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Hallmark.MODID)
public class Hallmark {
    public static final String MODID = "hallmark";
    public static final Logger LOGGER = LogManager.getLogger();

    public Hallmark(IEventBus modEventBus, ModContainer modContainer) {

        NeoForge.EVENT_BUS.register(this);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
        HallmarkModBlocks.BLOCKS.register(modEventBus);
        HallmarkModItems.ITEMS.register(modEventBus);
        HallmarkModCreativeTabs.CREATIVE_TABS.register(modEventBus);
        HallmarkModComponents.DATA_COMPONENTS.register(modEventBus);
        HallmarkModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        HallmarkModMenus.MENUS.register(modEventBus);
        HallmarkModRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("Hallmark is starting");
    }
}