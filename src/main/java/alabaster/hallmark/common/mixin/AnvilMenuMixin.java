package alabaster.hallmark.common.mixin;

import alabaster.hallmark.common.event.HallmarkEvents;
import alabaster.hallmark.common.item.StampItem;
import alabaster.hallmark.common.item.component.StampData;
import alabaster.hallmark.common.registry.HallmarkModComponents;
import alabaster.hallmark.common.util.HallmarkAdvancements;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin {
    @Shadow
    @Nullable
    private String itemName;

    @Shadow
    @Final
    private DataSlot cost;

    @Shadow
    public int repairItemCountCost;

    @Inject(method = "createResult", at = @At("RETURN"))
    private void hallmark$stampNaming(CallbackInfo ci) {
        AnvilMenu menu = (AnvilMenu) (Object) this;
        ItemStack left = menu.getSlot(AnvilMenu.INPUT_SLOT).getItem();

        if (StampItem.isMinted(left)) {
            hallmark$reject(menu);
            return;
        }
        if (!(left.getItem() instanceof StampItem)) {
            return;
        }
        if (StampItem.isStamp(left)
                || !menu.getSlot(AnvilMenu.ADDITIONAL_SLOT).getItem().isEmpty()
                || this.itemName == null
                || this.itemName.isBlank()) {
            hallmark$reject(menu);
            return;
        }

        String label = this.itemName.trim();
        if (label.length() > HallmarkEvents.MAX_LABEL_LENGTH) {
            label = label.substring(0, HallmarkEvents.MAX_LABEL_LENGTH);
        }

        ItemStack out = left.copy();
        out.setCount(1);
        out.set(HallmarkModComponents.STAMP, StampData.create(label));
        out.set(DataComponents.ITEM_NAME, Component.literal(label));
        menu.getSlot(AnvilMenu.RESULT_SLOT).set(out);
        this.repairItemCountCost = 0;
        this.cost.set(HallmarkEvents.NAMING_COST);
    }

    @Inject(method = "onTake", at = @At("HEAD"))
    private void hallmark$awardNamed(Player player, ItemStack stack, CallbackInfo ci) {
        if (StampItem.isStamp(stack)) {
            HallmarkAdvancements.award(player, HallmarkAdvancements.MAKE_YOUR_MARK);
        }
    }

    private void hallmark$reject(AnvilMenu menu) {
        menu.getSlot(AnvilMenu.RESULT_SLOT).set(ItemStack.EMPTY);
        this.repairItemCountCost = 0;
        this.cost.set(0);
    }
}
