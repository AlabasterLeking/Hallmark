package alabaster.hallmark.client.gui;

import alabaster.hallmark.Hallmark;
import alabaster.hallmark.common.block.entity.MintingPressBlockEntity;
import alabaster.hallmark.common.block.entity.menu.MintingPressMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MintingPressGUI extends AbstractContainerScreen<MintingPressMenu> {
    private static final ResourceLocation MINTING_PRESS_GUI =
            ResourceLocation.fromNamespaceAndPath(Hallmark.MODID, "textures/gui/minting_press.png");

    public MintingPressGUI(MintingPressMenu menu, Inventory playerInv, Component title) {
        super(menu, playerInv, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
        this.inventoryLabelY = 72;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(MINTING_PRESS_GUI, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    protected void renderTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        super.renderTooltip(guiGraphics, mouseX, mouseY);

        if (this.hoveredSlot != null && this.hoveredSlot.getItem().isEmpty()) {
            Component label = slotLabel(this.hoveredSlot.index);
            if (label != null) {
                guiGraphics.renderTooltip(this.font, label, mouseX, mouseY);
            }
        }
    }

    private static Component slotLabel(int index) {
        if (index == MintingPressBlockEntity.SLOT_INPUT) {
            return Component.translatable("gui.hallmark.minting_press.input_slot");
        }
        if (index == MintingPressBlockEntity.SLOT_STAMP) {
            return Component.translatable("gui.hallmark.minting_press.stamp_slot");
        }
        if (index == MintingPressBlockEntity.SLOT_OUTPUT) {
            return Component.translatable("gui.hallmark.minting_press.output_slot");
        }
        return null;
    }
}