package alabaster.hallmark.client;

import alabaster.hallmark.Hallmark;
import alabaster.hallmark.client.event.HallmarkTooltipEvents;
import alabaster.hallmark.client.gui.MintingPressGUI;
import alabaster.hallmark.client.renderer.MintingPressRenderer;
import alabaster.hallmark.common.item.StampItem;
import alabaster.hallmark.common.registry.HallmarkModBlockEntities;
import alabaster.hallmark.common.registry.HallmarkModItems;
import alabaster.hallmark.common.registry.HallmarkModMenus;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;

public class HallmarkClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MenuScreens.register(HallmarkModMenus.MINTING_PRESS, MintingPressGUI::new);
        BlockEntityRenderers.register(HallmarkModBlockEntities.MINTING_PRESS, MintingPressRenderer::new);
        ItemProperties.register(HallmarkModItems.STAMP, Hallmark.id("named"),
                (stack, level, entity, seed) -> StampItem.isStamp(stack) ? 1.0F : 0.0F);
        HallmarkTooltipEvents.register();
    }
}
