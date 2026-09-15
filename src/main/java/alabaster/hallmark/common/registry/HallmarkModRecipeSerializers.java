package alabaster.hallmark.common.registry;

import alabaster.hallmark.Hallmark;
import alabaster.hallmark.common.recipe.StampCloneRecipe;
import alabaster.hallmark.common.recipe.StampResetRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;

public class HallmarkModRecipeSerializers {
    public static final SimpleCraftingRecipeSerializer<StampCloneRecipe> STAMP_CLONE = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            Hallmark.id("stamp_clone"),
            new SimpleCraftingRecipeSerializer<>(StampCloneRecipe::new));

    public static final SimpleCraftingRecipeSerializer<StampResetRecipe> STAMP_RESET = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            Hallmark.id("stamp_reset"),
            new SimpleCraftingRecipeSerializer<>(StampResetRecipe::new));

    public static void register() {
    }
}
