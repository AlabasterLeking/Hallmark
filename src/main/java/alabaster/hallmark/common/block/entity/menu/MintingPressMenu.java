package alabaster.hallmark.common.block.entity.menu;

import alabaster.hallmark.Hallmark;
import alabaster.hallmark.common.block.entity.MintingPressBlockEntity;
import alabaster.hallmark.common.registry.HallmarkModBlocks;
import alabaster.hallmark.common.registry.HallmarkModMenus;
import alabaster.hallmark.common.util.Minting;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import static alabaster.hallmark.common.block.entity.MintingPressBlockEntity.SLOT_COUNT;
import static alabaster.hallmark.common.block.entity.MintingPressBlockEntity.SLOT_INPUT;
import static alabaster.hallmark.common.block.entity.MintingPressBlockEntity.SLOT_OUTPUT;
import static alabaster.hallmark.common.block.entity.MintingPressBlockEntity.SLOT_STAMP;

public class MintingPressMenu extends AbstractContainerMenu {
    public static final ResourceLocation STAMP_SLOT_ICON = Hallmark.id("item/stamp_slot");

    private static final int PRESS_SLOT_COUNT = SLOT_COUNT;
    private static final int PLAYER_INVENTORY_START = PRESS_SLOT_COUNT;
    private static final int PLAYER_INVENTORY_END = PLAYER_INVENTORY_START + 36;

    private final Container container;
    private final ContainerLevelAccess access;

    public MintingPressMenu(int id, Inventory playerInv, BlockPos pos) {
        this(id, playerInv, containerAt(playerInv, pos), ContainerLevelAccess.create(playerInv.player.level(), pos));
    }

    public MintingPressMenu(int id, Inventory playerInv, MintingPressBlockEntity press) {
        this(id, playerInv, press, ContainerLevelAccess.create(press.getLevel(), press.getBlockPos()));
    }

    private MintingPressMenu(int id, Inventory playerInv, Container container, ContainerLevelAccess access) {
        super(HallmarkModMenus.MINTING_PRESS, id);
        checkContainerSize(container, SLOT_COUNT);
        this.container = container;
        this.access = access;

        int startX = 8;
        int borderSlotSize = 18;

        this.addSlot(new MintingPressSlot(container, SLOT_INPUT, 44, 50));

        this.addSlot(new MintingPressSlot(container, SLOT_STAMP, 44, 20) {
            @Override
            public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                return Pair.of(TextureAtlas.LOCATION_BLOCKS, STAMP_SLOT_ICON);
            }
        });

        this.addSlot(new MintResultSlot(container, SLOT_OUTPUT, 116, 35, this));

        int startPlayerInvY = 84;
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                this.addSlot(new Slot(playerInv, 9 + (row * 9) + column, startX + (column * borderSlotSize),
                        startPlayerInvY + (row * borderSlotSize)));
            }
        }

        for (int column = 0; column < 9; column++) {
            this.addSlot(new Slot(playerInv, column, startX + (column * borderSlotSize), 142));
        }

        updateResult();
    }

    private static Container containerAt(Inventory playerInv, BlockPos pos) {
        if (playerInv.player.level().getBlockEntity(pos) instanceof MintingPressBlockEntity press) {
            return press;
        }
        return new SimpleContainer(SLOT_COUNT);
    }

    public void updateResult() {
        ItemStack result = Minting.process(container.getItem(SLOT_INPUT), container.getItem(SLOT_STAMP));
        if (!ItemStack.matches(result, container.getItem(SLOT_OUTPUT))) {
            container.setItem(SLOT_OUTPUT, result);
        }
    }

    public void onResultTaken() {
        container.setItem(SLOT_INPUT, ItemStack.EMPTY);
        updateResult();
    }

    @Override
    public void clicked(int slotId, int button, ClickType clickType, Player player) {
        super.clicked(slotId, button, clickType, player);
        updateResult();
    }

    @Override
    public void broadcastChanges() {
        updateResult();
        super.broadcastChanges();
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = this.slots.get(index);
        if (!slot.hasItem()) {
            return ItemStack.EMPTY;
        }
        ItemStack stack = slot.getItem();
        ItemStack original = stack.copy();

        if (index == SLOT_OUTPUT) {
            if (!moveItemStackTo(stack, PLAYER_INVENTORY_START, PLAYER_INVENTORY_END, true)) {
                return ItemStack.EMPTY;
            }
            slot.onQuickCraft(stack, original);
        } else if (index < PRESS_SLOT_COUNT) {
            if (!moveItemStackTo(stack, PLAYER_INVENTORY_START, PLAYER_INVENTORY_END, true)) {
                return ItemStack.EMPTY;
            }
        } else if (container.canPlaceItem(SLOT_STAMP, stack)) {
            if (!moveItemStackTo(stack, SLOT_STAMP, SLOT_STAMP + 1, false)) {
                return ItemStack.EMPTY;
            }
        } else if (container.canPlaceItem(SLOT_INPUT, stack)) {
            if (!moveItemStackTo(stack, SLOT_INPUT, SLOT_INPUT + 1, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            return ItemStack.EMPTY;
        }

        if (stack.isEmpty()) {
            slot.set(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }
        if (stack.getCount() == original.getCount()) {
            return ItemStack.EMPTY;
        }
        slot.onTake(player, stack);
        return original;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        if (!player.level().isClientSide) {
            container.setItem(SLOT_OUTPUT, ItemStack.EMPTY);
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, HallmarkModBlocks.MINTING_PRESS);
    }
}
