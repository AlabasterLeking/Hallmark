package alabaster.hallmark.client.event;

import alabaster.hallmark.Hallmark;
import alabaster.hallmark.client.renderer.MintingPressRenderer;
import alabaster.hallmark.client.gui.MintingPressGUI;
import alabaster.hallmark.common.registry.HallmarkModBlockEntities;
import alabaster.hallmark.common.registry.HallmarkModMenus;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = Hallmark.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public final class HallmarkClientEvents {
    private HallmarkClientEvents() {
    }

    @SubscribeEvent
    public static void onRegisterScreens(RegisterMenuScreensEvent event) {
        event.register(HallmarkModMenus.MINTING_PRESS.get(), MintingPressGUI::new);
    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(HallmarkModBlockEntities.MINTING_PRESS.get(), MintingPressRenderer::new);
    }
}