package alabaster.hallmark.common.registry;

import alabaster.hallmark.Hallmark;
import alabaster.hallmark.common.item.MonocleItem;
import alabaster.hallmark.common.item.StampItem;
import com.google.common.collect.Sets;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

public class HallmarkModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, Hallmark.MODID);
    public static LinkedHashSet<Supplier<Item>> CREATIVE_TAB_ITEMS = Sets.newLinkedHashSet();

    public static Supplier<Item> registerWithTab(String name, Supplier<Item> supplier) {
        Supplier<Item> item = ITEMS.register(name, supplier);
        CREATIVE_TAB_ITEMS.add(item);
        return item;
    }

    public static final Supplier<Item> STAMP = registerWithTab("stamp",
            () -> new StampItem(basicItem()));

    public static final Supplier<Item> MINTING_PRESS = registerWithTab("minting_press",
            () -> new BlockItem(HallmarkModBlocks.MINTING_PRESS.get(), basicItem()));

    public static final Supplier<Item> MONOCLE = registerWithTab("monocle",
            () -> new MonocleItem(basicItem()));

    // Helper methods
    public static Item.Properties basicItem() {
        return (new Item.Properties());
    }
}