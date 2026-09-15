package alabaster.hallmark.common.registry;

import alabaster.hallmark.Hallmark;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class HallmarkModCreativeTabs {
    public static final CreativeModeTab TAB_HALLMARK = Registry.register(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            Hallmark.id(Hallmark.MODID),
            FabricItemGroup.builder()
                    .title(Component.translatable("itemGroup.hallmark"))
                    .icon(() -> new ItemStack(HallmarkModItems.STAMP))
                    .displayItems((parameters, output) -> HallmarkModItems.CREATIVE_TAB_ITEMS.forEach(output::accept))
                    .build());

    public static void register() {
    }
}
