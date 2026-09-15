package alabaster.hallmark.data.advancement;

import alabaster.hallmark.Hallmark;
import alabaster.hallmark.common.item.component.StampData;
import alabaster.hallmark.common.registry.HallmarkModComponents;
import alabaster.hallmark.common.registry.HallmarkModItems;
import alabaster.hallmark.common.util.HallmarkTextUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.ImpossibleTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class HallmarkAdvancementProvider extends FabricAdvancementProvider {
    private static final UUID ICON_STAMP_ID = UUID.fromString("00000000-0000-0000-0000-000000000001");
    private static final String ICON_STAMP_LABEL = "Hallmark";

    public HallmarkAdvancementProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(HolderLookup.Provider registryLookup, Consumer<AdvancementHolder> consumer) {
        AdvancementHolder hallmark = Advancement.Builder.advancement()
                .display(HallmarkModItems.STAMP,
                        HallmarkTextUtils.getTranslation("advancement.root"),
                        HallmarkTextUtils.getTranslation("advancement.root.desc"),
                        ResourceLocation.parse("minecraft:textures/block/dark_oak_planks.png"),
                        AdvancementType.TASK, false, false, false)
                .addCriterion("root", InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[]{}))
                .save(consumer, getNameId("main/root"));

        AdvancementHolder blankSlate = getAdvancement(hallmark, HallmarkModItems.STAMP, "blank_slate", AdvancementType.TASK, true, true, false)
                .addCriterion("stamp", InventoryChangeTrigger.TriggerInstance.hasItems(HallmarkModItems.STAMP))
                .save(consumer, getNameId("main/blank_slate"));

        AdvancementHolder makeYourMark = getAdvancement(blankSlate, namedStamp(StampData.ORIGINAL), "make_your_mark", AdvancementType.TASK, true, true, false)
                .addCriterion("named_stamp", CriteriaTriggers.IMPOSSIBLE.createCriterion(new ImpossibleTrigger.TriggerInstance()))
                .save(consumer, getNameId("main/make_your_mark"));

        AdvancementHolder pressGang = getAdvancement(blankSlate, HallmarkModItems.MINTING_PRESS, "minty", AdvancementType.TASK, true, true, false)
                .addCriterion("minting_press", InventoryChangeTrigger.TriggerInstance.hasItems(HallmarkModItems.MINTING_PRESS))
                .save(consumer, getNameId("main/minty"));

        AdvancementHolder legalTender = getAdvancement(pressGang, Items.GOLD_INGOT, "blockchain", AdvancementType.GOAL, true, true, false)
                .addCriterion("minted", CriteriaTriggers.IMPOSSIBLE.createCriterion(new ImpossibleTrigger.TriggerInstance()))
                .save(consumer, getNameId("main/blockchain"));

        getAdvancement(legalTender, HallmarkModItems.MINTING_PRESS, "demonetized", AdvancementType.TASK, true, true, false)
                .addCriterion("defaced", CriteriaTriggers.IMPOSSIBLE.createCriterion(new ImpossibleTrigger.TriggerInstance()))
                .save(consumer, getNameId("main/demonetized"));

        getAdvancement(hallmark, HallmarkModItems.MONOCLE, "assayer", AdvancementType.TASK, true, true, false)
                .addCriterion("monocle", InventoryChangeTrigger.TriggerInstance.hasItems(HallmarkModItems.MONOCLE))
                .save(consumer, getNameId("main/assayer"));

        getAdvancement(makeYourMark, namedStamp(1), "counterfeit", AdvancementType.CHALLENGE, true, true, true)
                .addCriterion("copied_stamp", CriteriaTriggers.IMPOSSIBLE.createCriterion(new ImpossibleTrigger.TriggerInstance()))
                .save(consumer, getNameId("main/counterfeit"));
    }

    private static ItemStack namedStamp(int generation) {
        ItemStack stack = new ItemStack(HallmarkModItems.STAMP);
        stack.set(HallmarkModComponents.STAMP, new StampData(ICON_STAMP_ID, ICON_STAMP_LABEL, generation));
        stack.set(DataComponents.ITEM_NAME, Component.literal(ICON_STAMP_LABEL));
        return stack;
    }

    protected static Advancement.Builder getAdvancement(AdvancementHolder parent, ItemLike display, String name, AdvancementType frame, boolean showToast, boolean announceToChat, boolean hidden) {
        return Advancement.Builder.advancement().parent(parent).display(display,
                HallmarkTextUtils.getTranslation("advancement." + name),
                HallmarkTextUtils.getTranslation("advancement." + name + ".desc"),
                null, frame, showToast, announceToChat, hidden);
    }

    protected static Advancement.Builder getAdvancement(AdvancementHolder parent, ItemStack display, String name, AdvancementType frame, boolean showToast, boolean announceToChat, boolean hidden) {
        return Advancement.Builder.advancement().parent(parent).display(display,
                HallmarkTextUtils.getTranslation("advancement." + name),
                HallmarkTextUtils.getTranslation("advancement." + name + ".desc"),
                null, frame, showToast, announceToChat, hidden);
    }

    private static String getNameId(String id) {
        return Hallmark.MODID + ":" + id;
    }
}
