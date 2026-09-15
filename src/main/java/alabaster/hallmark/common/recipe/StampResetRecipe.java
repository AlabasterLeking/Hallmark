package alabaster.hallmark.common.recipe;

import alabaster.hallmark.common.item.StampItem;
import alabaster.hallmark.common.item.component.StampData;
import alabaster.hallmark.common.registry.HallmarkModRecipeSerializers;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class StampResetRecipe extends CustomRecipe {
    public StampResetRecipe(CraftingBookCategory category) {
        super(category);
    }

    private static ItemStack findResettable(CraftingInput input) {
        ItemStack found = ItemStack.EMPTY;
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) {
                continue;
            }
            if (!found.isEmpty()) {
                return ItemStack.EMPTY;
            }
            if (!(stack.getItem() instanceof StampItem)) {
                return ItemStack.EMPTY;
            }
            StampData data = StampItem.dataOf(stack);
            if (data == null || data.isOriginal()) {
                return ItemStack.EMPTY;
            }
            found = stack;
        }
        return found;
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        return !findResettable(input).isEmpty();
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        ItemStack source = findResettable(input);
        if (source.isEmpty()) {
            return ItemStack.EMPTY;
        }
        return new ItemStack(source.getItem());
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 1;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return HallmarkModRecipeSerializers.STAMP_RESET;
    }
}