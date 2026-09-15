package alabaster.hallmark.common.mixin;

import alabaster.hallmark.common.item.StampItem;
import alabaster.hallmark.common.item.component.StampData;
import alabaster.hallmark.common.util.HallmarkAdvancements;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ResultSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ResultSlot.class)
public class ResultSlotMixin {
    @Inject(method = "onTake", at = @At("HEAD"))
    private void hallmark$awardCounterfeit(Player player, ItemStack stack, CallbackInfo ci) {
        StampData data = StampItem.dataOf(stack);
        if (data != null && !data.isOriginal()) {
            HallmarkAdvancements.award(player, HallmarkAdvancements.COUNTERFEIT);
        }
    }
}
