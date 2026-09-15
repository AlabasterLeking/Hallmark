package alabaster.hallmark.client.renderer;

import alabaster.hallmark.common.block.MintingPressBlock;
import alabaster.hallmark.common.block.entity.MintingPressBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import com.mojang.math.Axis;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class MintingPressRenderer implements BlockEntityRenderer<MintingPressBlockEntity> {
    private final ItemRenderer itemRenderer;

    public MintingPressRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(MintingPressBlockEntity press, float partialTick, PoseStack pose, MultiBufferSource buffers, int packedLight, int packedOverlay) {
        Level level = press.getLevel();
        if (level == null) {
            return;
        }
        int light = LevelRenderer.getLightColor(level, press.getBlockPos().above());
        float yaw = press.getBlockState().getValue(MintingPressBlock.FACING).toYRot();

        renderStack(press.getRenderedStack(MintingPressBlockEntity.SLOT_INPUT), pose, buffers, light, packedOverlay, level, yaw, -0.16F);
        renderStack(press.getRenderedStack(MintingPressBlockEntity.SLOT_STAMP), pose, buffers, light, packedOverlay, level, yaw, 0.16F);
    }

    private void renderStack(ItemStack stack, PoseStack pose, MultiBufferSource buffers, int light, int overlay, Level level, float yaw, float offset) {
        if (stack.isEmpty()) {
            return;
        }
        pose.pushPose();
        pose.translate(0.5F, 1.01F, 0.5F);
        pose.mulPose(Axis.YP.rotationDegrees(180.0F - yaw));
        pose.translate(offset, 0.0F, 0.0F);
        pose.mulPose(Axis.XP.rotationDegrees(90.0F));
        pose.scale(0.4F, 0.4F, 0.4F);
        itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED, light, overlay, pose, buffers, level, 0);
        pose.popPose();
    }
}