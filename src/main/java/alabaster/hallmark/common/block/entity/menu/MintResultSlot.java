package alabaster.hallmark.common.block.entity.menu;

import alabaster.hallmark.common.item.StampItem;
import alabaster.hallmark.common.util.HallmarkAdvancements;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

public class MintResultSlot extends SlotItemHandler {
    private final MintingPressMenu menu;

    public MintResultSlot(IItemHandler handler, int index, int x, int y, MintingPressMenu menu) {
        super(handler, index, x, y);
        this.menu = menu;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return false;
    }

    @Override
    public void onTake(Player player, ItemStack stack) {
        HallmarkAdvancements.award(player, StampItem.isMinted(stack)
                ? HallmarkAdvancements.LEGAL_TENDER
                : HallmarkAdvancements.DEMONETIZED);
        menu.onResultTaken();
        super.onTake(player, stack);
    }
}