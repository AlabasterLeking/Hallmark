package alabaster.hallmark.common.registry;

import alabaster.hallmark.Hallmark;
import alabaster.hallmark.common.recipe.StampCloneRecipe;
import alabaster.hallmark.common.recipe.StampResetRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class HallmarkModRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, Hallmark.MODID);

    public static final DeferredHolder<RecipeSerializer<?>, SimpleCraftingRecipeSerializer<StampCloneRecipe>> STAMP_CLONE =
            RECIPE_SERIALIZERS.register("stamp_clone", () -> new SimpleCraftingRecipeSerializer<>(StampCloneRecipe::new));

    public static final DeferredHolder<RecipeSerializer<?>, SimpleCraftingRecipeSerializer<StampResetRecipe>> STAMP_RESET =
            RECIPE_SERIALIZERS.register("stamp_reset", () -> new SimpleCraftingRecipeSerializer<>(StampResetRecipe::new));

    public static void register(IEventBus bus) {
        RECIPE_SERIALIZERS.register(bus);
    }
}