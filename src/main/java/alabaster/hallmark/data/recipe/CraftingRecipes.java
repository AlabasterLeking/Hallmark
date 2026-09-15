package alabaster.hallmark.data.recipe;

import alabaster.hallmark.common.registry.HallmarkModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

public class CraftingRecipes {
    public static void register(RecipeOutput output) {
        recipesTools(output);
    }

    private static void recipesTools(RecipeOutput output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HallmarkModItems.STAMP, 1)
                .pattern("S")
                .pattern("G")
                .define('G', Items.GOLD_INGOT)
                .define('S', Items.STICK)
                .unlockedBy("has_gold_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_INGOT))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HallmarkModItems.MINTING_PRESS, 1)
                .pattern("GwG")
                .pattern("WWW")
                .pattern("WWW")
                .define('G', Items.GOLD_INGOT)
                .define('w', ItemTags.WOOL)
                .define('W', ItemTags.PLANKS)
                .unlockedBy("has_gold_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_INGOT))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HallmarkModItems.MONOCLE, 1)
                .pattern(" G ")
                .pattern("GgG")
                .pattern(" G ")
                .define('G', Items.GOLD_INGOT)
                .define('g', Items.GLASS_PANE)
                .unlockedBy("has_gold_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_INGOT))
                .unlockedBy("has_glass_pane", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLASS_PANE))
                .save(output);
    }
}