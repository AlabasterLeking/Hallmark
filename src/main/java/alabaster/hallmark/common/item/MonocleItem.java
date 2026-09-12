package alabaster.hallmark.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class MonocleItem extends Item {
    public MonocleItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public EquipmentSlot getEquipmentSlot(ItemStack stack) {
        return EquipmentSlot.HEAD;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack held = player.getItemInHand(hand);
        ItemStack worn = player.getItemBySlot(EquipmentSlot.HEAD);
        if (!worn.isEmpty()) {
            return InteractionResultHolder.fail(held);
        }
        player.setItemSlot(EquipmentSlot.HEAD, held.copy());
        held.setCount(0);
        player.playSound(SoundEvents.ARMOR_EQUIP_GENERIC.value(), 1.0F, 1.0F);
        return InteractionResultHolder.sidedSuccess(held, level.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.monocle.hint").withStyle(ChatFormatting.GRAY));
    }

    public static boolean isWornBy(Player player) {
        return player != null && player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof MonocleItem;
    }
}