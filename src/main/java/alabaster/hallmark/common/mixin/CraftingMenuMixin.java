package alabaster.hallmark.common.mixin;

import alabaster.hallmark.common.registry.HallmarkModComponents;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CraftingMenu.class)
public class CraftingMenuMixin {
    @Inject(method = "slotChangedCraftingGrid", at = @At("TAIL"))
    private static void hallmark$clearMintedResult(AbstractContainerMenu menu, Level level, Player player, CraftingContainer craftSlots, ResultContainer resultSlots, RecipeHolder<CraftingRecipe> recipe, CallbackInfo ci) {
        if (level.isClientSide || !(player instanceof ServerPlayer serverPlayer)) {
            return;
        }
        if (!containsMinted(craftSlots)) {
            return;
        }
        resultSlots.setItem(0, ItemStack.EMPTY);
        menu.setRemoteSlot(0, ItemStack.EMPTY);
        serverPlayer.connection.send(new ClientboundContainerSetSlotPacket(menu.containerId, menu.incrementStateId(), 0, ItemStack.EMPTY));
    }

    private static boolean containsMinted(CraftingContainer craftSlots) {
        for (int i = 0; i < craftSlots.getContainerSize(); i++) {
            if (craftSlots.getItem(i).has(HallmarkModComponents.MINTED.get())) {
                return true;
            }
        }
        return false;
    }
}