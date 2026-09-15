package alabaster.hallmark.common.util;

import alabaster.hallmark.Hallmark;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public final class HallmarkAdvancements {
    public static final String MAKE_YOUR_MARK = "main/make_your_mark";
    public static final String LEGAL_TENDER = "main/blockchain";
    public static final String DEMONETIZED = "main/demonetized";
    public static final String COUNTERFEIT = "main/counterfeit";

    private HallmarkAdvancements() {
    }

    public static void award(Player player, String path) {
        if (!(player instanceof ServerPlayer serverPlayer)) {
            return;
        }
        AdvancementHolder holder = serverPlayer.server.getAdvancements().get(Hallmark.id(path));
        if (holder == null) {
            return;
        }
        AdvancementProgress progress = serverPlayer.getAdvancements().getOrStartProgress(holder);
        if (progress.isDone()) {
            return;
        }
        for (String criterion : progress.getRemainingCriteria()) {
            serverPlayer.getAdvancements().award(holder, criterion);
        }
    }
}
