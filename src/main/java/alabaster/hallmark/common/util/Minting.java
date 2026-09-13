package alabaster.hallmark.common.util;

import alabaster.hallmark.common.item.StampItem;
import alabaster.hallmark.common.item.component.MintedData;
import alabaster.hallmark.common.item.component.StampData;
import alabaster.hallmark.common.registry.HallmarkModComponents;
import net.minecraft.world.item.ItemStack;

public final class Minting {
    private Minting() {
    }

    public static boolean canMint(ItemStack input, ItemStack stamp) {
        if (input.isEmpty() || stamp.isEmpty()) {
            return false;
        }
        if (StampItem.isMinted(input)) {
            return false;
        }
        StampData data = StampItem.dataOf(stamp);
        return data != null && data.canMint();
    }

    public static ItemStack mint(ItemStack input, ItemStack stamp) {
        if (!canMint(input, stamp)) {
            return ItemStack.EMPTY;
        }
        ItemStack out = input.copy();
        out.set(HallmarkModComponents.MINTED.get(), StampItem.dataOf(stamp).toMinted());
        return out;
    }

    public static boolean canDeface(ItemStack input, ItemStack stamp) {
        MintedData minted = StampItem.mintedOf(input);
        if (minted == null) {
            return false;
        }
        StampData data = StampItem.dataOf(stamp);
        return data != null && data.id().equals(minted.id());
    }

    public static ItemStack deface(ItemStack input, ItemStack stamp) {
        if (!canDeface(input, stamp)) {
            return ItemStack.EMPTY;
        }
        ItemStack out = input.copy();
        out.remove(HallmarkModComponents.MINTED.get());
        return out;
    }

    public static ItemStack process(ItemStack input, ItemStack stamp) {
        if (canMint(input, stamp)) {
            return mint(input, stamp);
        }
        if (canDeface(input, stamp)) {
            return deface(input, stamp);
        }
        return ItemStack.EMPTY;
    }
}