package alabaster.hallmark.common.event;

import alabaster.hallmark.common.item.StampItem;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;

public final class HallmarkEvents {
    public static final int NAMING_COST = 5;
    public static final int MAX_LABEL_LENGTH = 32;

    private HallmarkEvents() {
    }

    public static void register() {
        UseBlockCallback.EVENT.register((player, level, hand, hit) -> {
            ItemStack stack = player.getItemInHand(hand);
            if (!(stack.getItem() instanceof BlockItem) || !StampItem.isMinted(stack)) {
                return InteractionResult.PASS;
            }
            if (!level.isClientSide) {
                player.displayClientMessage(
                        Component.translatable("message.hallmark.no_place").withStyle(ChatFormatting.RED), true);
            }
            return InteractionResult.FAIL;
        });
    }
}
