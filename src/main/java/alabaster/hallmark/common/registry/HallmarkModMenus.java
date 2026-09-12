package alabaster.hallmark.common.registry;

import alabaster.hallmark.Hallmark;
import alabaster.hallmark.common.block.entity.menu.MintingPressMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class HallmarkModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, Hallmark.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<MintingPressMenu>> MINTING_PRESS =
            MENUS.register("minting_press", () -> IMenuTypeExtension.create(MintingPressMenu::new));

}