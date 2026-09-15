package alabaster.hallmark.common.registry;

import alabaster.hallmark.Hallmark;
import alabaster.hallmark.common.item.MonocleItem;
import alabaster.hallmark.common.item.StampItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import java.util.LinkedHashSet;

public class HallmarkModItems {
    public static final LinkedHashSet<Item> CREATIVE_TAB_ITEMS = new LinkedHashSet<>();

    public static final Item STAMP = registerWithTab("stamp", new StampItem(basicItem()));

    public static final Item MINTING_PRESS = registerWithTab("minting_press",
            new BlockItem(HallmarkModBlocks.MINTING_PRESS, basicItem()));

    public static final Item MONOCLE = registerWithTab("monocle", new MonocleItem(basicItem()));

    private static Item registerWithTab(String name, Item item) {
        Item registered = Registry.register(BuiltInRegistries.ITEM, Hallmark.id(name), item);
        CREATIVE_TAB_ITEMS.add(registered);
        return registered;
    }

    public static Item.Properties basicItem() {
        return new Item.Properties();
    }

    public static void register() {
    }
}
