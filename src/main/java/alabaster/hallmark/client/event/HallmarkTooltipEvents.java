package alabaster.hallmark.client.event;

import alabaster.hallmark.Hallmark;
import alabaster.hallmark.common.item.MonocleItem;
import alabaster.hallmark.common.item.component.MintedData;
import alabaster.hallmark.common.item.component.StampData;
import alabaster.hallmark.common.registry.HallmarkModComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@EventBusSubscriber(modid = Hallmark.MODID, value = Dist.CLIENT)
public final class HallmarkTooltipEvents {
    private HallmarkTooltipEvents() {
    }

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        boolean monocle = MonocleItem.isWornBy(Minecraft.getInstance().player);

        MintedData minted = stack.get(HallmarkModComponents.MINTED.get());
        if (minted != null) {
            if (monocle) {
                event.getToolTip().add(1, Component.literal(minted.shortId()).withStyle(ChatFormatting.DARK_GRAY));
            }
            event.getToolTip().add(1, Component.translatable("minted.mark", minted.label()).withStyle(ChatFormatting.GOLD));
            return;
        }

        if (!monocle) {
            return;
        }
        StampData stamp = stack.get(HallmarkModComponents.STAMP.get());
        if (stamp != null) {
            event.getToolTip().add(Component.literal(stamp.shortId()).withStyle(ChatFormatting.DARK_GRAY));
        }
    }
}