package alabaster.hallmark.common.event;

import alabaster.hallmark.Hallmark;
import alabaster.hallmark.common.item.StampItem;
import alabaster.hallmark.common.item.component.StampData;
import alabaster.hallmark.common.registry.HallmarkModComponents;
import alabaster.hallmark.common.util.HallmarkAdvancements;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AnvilUpdateEvent;
import net.neoforged.neoforge.event.entity.player.AnvilRepairEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

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

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        ItemStack stack = event.getItemStack();
        if (!(stack.getItem() instanceof BlockItem) || !StampItem.isMinted(stack)) {
            return;
        }
        event.setCanceled(true);
        event.setCancellationResult(InteractionResult.FAIL);
        if (!event.getLevel().isClientSide) {
            event.getEntity().displayClientMessage(
                    Component.translatable("message.hallmark.no_place").withStyle(ChatFormatting.RED), true);
        }
    }

    @SubscribeEvent
    public static void onAnvilRepair(AnvilRepairEvent event) {
        if (StampItem.isStamp(event.getOutput())) {
            HallmarkAdvancements.award(event.getEntity(), HallmarkAdvancements.MAKE_YOUR_MARK);
        }
    }

    @SubscribeEvent
    public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        StampData data = StampItem.dataOf(event.getCrafting());
        if (data != null && !data.isOriginal()) {
            HallmarkAdvancements.award(event.getEntity(), HallmarkAdvancements.COUNTERFEIT);
        }
    }
}