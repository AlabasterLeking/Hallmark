package alabaster.hallmark.common.event;

import alabaster.hallmark.Hallmark;
import alabaster.hallmark.common.item.StampItem;
import alabaster.hallmark.common.item.component.StampData;
import alabaster.hallmark.common.registry.HallmarkModComponents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AnvilUpdateEvent;

@EventBusSubscriber(modid = Hallmark.MODID)
public final class HallmarkEvents {
    public static final int NAMING_COST = 5;
    public static final int MAX_LABEL_LENGTH = 32;

    private HallmarkEvents() {
    }

    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event) {
        ItemStack left = event.getLeft();

        if (StampItem.isMinted(left)) {
            event.setCanceled(true);
            return;
        }
        if (!(left.getItem() instanceof StampItem)) {
            return;
        }
        if (StampItem.isStamp(left)) {
            event.setCanceled(true);
            return;
        }
        if (!event.getRight().isEmpty()) {
            event.setCanceled(true);
            return;
        }

        String name = event.getName();
        if (name == null || name.isBlank()) {
            event.setCanceled(true);
            return;
        }

        String label = name.trim();
        if (label.length() > MAX_LABEL_LENGTH) {
            label = label.substring(0, MAX_LABEL_LENGTH);
        }

        ItemStack out = left.copy();
        out.setCount(1);
        out.set(HallmarkModComponents.STAMP.get(), StampData.create(label));
        out.set(DataComponents.ITEM_NAME, Component.literal(label));
        event.setOutput(out);
        event.setMaterialCost(0);
        event.setCost(NAMING_COST);
    }
}