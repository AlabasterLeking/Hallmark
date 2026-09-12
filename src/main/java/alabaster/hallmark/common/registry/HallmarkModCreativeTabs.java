package alabaster.hallmark.common.registry;

import alabaster.hallmark.Hallmark;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class HallmarkModCreativeTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Hallmark.MODID);

    public static final Supplier<CreativeModeTab> TAB_HALLMARK = CREATIVE_TABS.register(Hallmark.MODID,
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.hallmark"))
                    .icon(() -> new ItemStack(HallmarkModItems.STAMP.get()))
                    .displayItems((parameters, output) -> HallmarkModItems.CREATIVE_TAB_ITEMS.forEach((item) -> output.accept(item.get())))
                    .build());
}