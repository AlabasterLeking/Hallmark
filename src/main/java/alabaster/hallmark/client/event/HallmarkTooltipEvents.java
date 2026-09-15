package alabaster.hallmark.client.event;

import alabaster.hallmark.common.item.MonocleItem;
import alabaster.hallmark.common.item.component.MintedData;
import alabaster.hallmark.common.item.component.StampData;
import alabaster.hallmark.common.registry.HallmarkModComponents;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public final class HallmarkTooltipEvents {
    private HallmarkTooltipEvents() {
    }

    public static void register() {
        ItemTooltipCallback.EVENT.register((stack, context, type, lines) -> {
            boolean monocle = MonocleItem.isWornBy(Minecraft.getInstance().player);

            MintedData minted = stack.get(HallmarkModComponents.MINTED);
            if (minted != null) {
                if (monocle) {
                    lines.add(1, Component.literal(minted.shortId()).withStyle(ChatFormatting.DARK_GRAY));
                }
                lines.add(1, Component.translatable("minted.mark", minted.label()).withStyle(ChatFormatting.GOLD));
                return;
            }

            if (!monocle) {
                return;
            }
            StampData stamp = stack.get(HallmarkModComponents.STAMP);
            if (stamp != null) {
                lines.add(Component.literal(stamp.shortId()).withStyle(ChatFormatting.DARK_GRAY));
            }
        });
    }
}
