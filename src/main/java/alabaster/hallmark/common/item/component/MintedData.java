package alabaster.hallmark.common.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.UUID;

public record MintedData(UUID id, String label) {
    public static final Codec<MintedData> CODEC = RecordCodecBuilder.create(i -> i.group(
            UUIDUtil.STRING_CODEC.fieldOf("id").forGetter(MintedData::id),
            Codec.STRING.optionalFieldOf("label", "").forGetter(MintedData::label)
    ).apply(i, MintedData::new));

    public static final StreamCodec<ByteBuf, MintedData> STREAM_CODEC = StreamCodec.composite(
            UUIDUtil.STREAM_CODEC, MintedData::id,
            ByteBufCodecs.STRING_UTF8, MintedData::label,
            MintedData::new);

    public String shortId() {
        return id.toString().substring(0, 8);
    }
}