package alabaster.hallmark.common.recipe;

import alabaster.hallmark.common.item.StampItem;
import alabaster.hallmark.common.item.component.StampData;
import alabaster.hallmark.common.registry.HallmarkModComponents;
import alabaster.hallmark.common.registry.HallmarkModRecipeSerializers;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class StampCloneRecipe extends CustomRecipe {
    public StampCloneRecipe(CraftingBookCategory category) {
        super(category);
    }

    private static ItemStack findSource(CraftingInput input) {
        ItemStack source = ItemStack.EMPTY;
        int blanks = 0;
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) {
                continue;
            }
            if (!(stack.getItem() instanceof StampItem)) {
                return ItemStack.EMPTY;
            }
            if (StampItem.isStamp(stack)) {
                if (!source.isEmpty()) {
                    return ItemStack.EMPTY;
                }
                StampData data = StampItem.dataOf(stack);
                if (data == null || !data.canCopy()) {
                    return ItemStack.EMPTY;
                }
                source = stack;
            } else {
                blanks++;
            }
        }
        return blanks == 1 ? source : ItemStack.EMPTY;
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        return !findSource(input).isEmpty();
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        ItemStack source = findSource(input);
        if (source.isEmpty()) {
            return ItemStack.EMPTY;
        }
        StampData copy = StampItem.dataOf(source).copied();
        ItemStack out = new ItemStack(source.getItem());
        out.set(HallmarkModComponents.STAMP.get(), copy);
        out.set(DataComponents.ITEM_NAME, Component.literal(copy.label()));
        return out;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        NonNullList<ItemStack> remaining = NonNullList.withSize(input.size(), ItemStack.EMPTY);
        for (int i = 0; i < remaining.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (StampItem.isStamp(stack)) {
                remaining.set(i, stack.copy());
            } else {
                remaining.set(i, stack.getCraftingRemainingItem());
            }
        }
        return remaining;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return HallmarkModRecipeSerializers.STAMP_CLONE.get();
    }
}