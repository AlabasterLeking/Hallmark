package alabaster.hallmark.common.item;

import alabaster.hallmark.common.item.component.MintedData;
import alabaster.hallmark.common.item.component.StampData;
import alabaster.hallmark.common.registry.HallmarkModComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class StampItem extends Item {
    public StampItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        StampData data = stack.get(HallmarkModComponents.STAMP);
        if (data == null) {
            tooltip.add(Component.translatable("stamp.blank").withStyle(ChatFormatting.GRAY));
            return;
        }
        tooltip.add(Component.translatable("stamp.generation." + data.generation()).withStyle(ChatFormatting.GRAY));
    }

    public static StampData dataOf(ItemStack stack) {
        return stack.get(HallmarkModComponents.STAMP);
    }

    public static boolean isStamp(ItemStack stack) {
        return stack.has(HallmarkModComponents.STAMP);
    }

    public static boolean isMinted(ItemStack stack) {
        return stack.has(HallmarkModComponents.MINTED);
    }

    public static MintedData mintedOf(ItemStack stack) {
        return stack.get(HallmarkModComponents.MINTED);
    }
}
