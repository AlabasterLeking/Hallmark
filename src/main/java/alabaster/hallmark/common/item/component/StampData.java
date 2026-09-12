package alabaster.hallmark.common.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.UUID;

public record StampData(UUID id, String label, int generation) {
    public static final int ORIGINAL = 0;
    public static final int MAX_GENERATION = 3;

    public static final Codec<StampData> CODEC = RecordCodecBuilder.create(i -> i.group(
            UUIDUtil.STRING_CODEC.fieldOf("id").forGetter(StampData::id),
            Codec.STRING.optionalFieldOf("label", "").forGetter(StampData::label),
            Codec.intRange(0, MAX_GENERATION).optionalFieldOf("generation", ORIGINAL).forGetter(StampData::generation)
    ).apply(i, StampData::new));

    public static final StreamCodec<ByteBuf, StampData> STREAM_CODEC = StreamCodec.composite(
            UUIDUtil.STREAM_CODEC, StampData::id,
            ByteBufCodecs.STRING_UTF8, StampData::label,
            ByteBufCodecs.VAR_INT, StampData::generation,
            StampData::new);

    public static StampData create(String label) {
        return new StampData(UUID.randomUUID(), label, ORIGINAL);
    }

    public boolean isOriginal() {
        return generation == ORIGINAL;
    }

    public boolean canCopy() {
        return true;
    }

    public boolean canMint() {
        return true;
    }

    public StampData copied() {
        return new StampData(id, label, Math.min(generation + 1, MAX_GENERATION));
    }

    public MintedData toMinted() {
        return new MintedData(id, label);
    }

    public String shortId() {
        return id.toString().substring(0, 8);
    }
}