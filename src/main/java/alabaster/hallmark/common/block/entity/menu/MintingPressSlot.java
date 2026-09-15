package alabaster.hallmark.common.block.entity.menu;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class MintingPressSlot extends Slot {
    public MintingPressSlot(Container container, int index, int x, int y) {
        super(container, index, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return this.container.canPlaceItem(this.getContainerSlot(), stack);
    }
}
