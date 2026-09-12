package alabaster.hallmark.common.mixin;

import alabaster.hallmark.common.registry.HallmarkModComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Ingredient.class)
public class IngredientMixin {
    @Inject(method = "test(Lnet/minecraft/world/item/ItemStack;)Z", at = @At("HEAD"), cancellable = true)
    private void hallmark$rejectMinted(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack != null && stack.has(HallmarkModComponents.MINTED.get())) {
            cir.setReturnValue(false);
        }
    }
}