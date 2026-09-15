package alabaster.hallmark.common.mixin;

import alabaster.hallmark.common.registry.HallmarkModComponents;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {
    @Inject(method = "getRecipeFor(Lnet/minecraft/world/item/crafting/RecipeType;Lnet/minecraft/world/item/crafting/RecipeInput;Lnet/minecraft/world/level/Level;)Ljava/util/Optional;", at = @At("HEAD"), cancellable = true)
    private void hallmark$blockMinted(RecipeType<?> type, RecipeInput input, Level level, CallbackInfoReturnable<Optional<?>> cir) {
        if (containsMinted(input)) {
            cir.setReturnValue(Optional.empty());
        }
    }

    @Inject(method = "getRecipeFor(Lnet/minecraft/world/item/crafting/RecipeType;Lnet/minecraft/world/item/crafting/RecipeInput;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/crafting/RecipeHolder;)Ljava/util/Optional;", at = @At("HEAD"), cancellable = true)
    private void hallmark$blockMintedCached(RecipeType<?> type, RecipeInput input, Level level, RecipeHolder<?> lastRecipe, CallbackInfoReturnable<Optional<?>> cir) {
        if (containsMinted(input)) {
            cir.setReturnValue(Optional.empty());
        }
    }

    @Inject(method = "getRemainingItemsFor(Lnet/minecraft/world/item/crafting/RecipeType;Lnet/minecraft/world/item/crafting/RecipeInput;Lnet/minecraft/world/level/Level;)Lnet/minecraft/core/NonNullList;", at = @At("HEAD"), cancellable = true)
    private void hallmark$noRemaindersForMinted(RecipeType<?> type, RecipeInput input, Level level, CallbackInfoReturnable<NonNullList<ItemStack>> cir) {
        if (containsMinted(input)) {
            cir.setReturnValue(NonNullList.withSize(input.size(), ItemStack.EMPTY));
        }
    }

    private static boolean containsMinted(RecipeInput input) {
        for (int i = 0; i < input.size(); i++) {
            if (input.getItem(i).has(HallmarkModComponents.MINTED)) {
                return true;
            }
        }
        return false;
    }
}