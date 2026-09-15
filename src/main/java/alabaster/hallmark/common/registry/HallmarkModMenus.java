package alabaster.hallmark.common.registry;

import alabaster.hallmark.Hallmark;
import alabaster.hallmark.common.block.entity.menu.MintingPressMenu;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;

public class HallmarkModMenus {
    public static final MenuType<MintingPressMenu> MINTING_PRESS = Registry.register(
            BuiltInRegistries.MENU,
            Hallmark.id("minting_press"),
            new ExtendedScreenHandlerType<>(MintingPressMenu::new, BlockPos.STREAM_CODEC));

    public static void register() {
    }
}
