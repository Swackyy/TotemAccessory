package com.example.totemaccessory;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = TotemAccessory.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TAConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.EnumValue<TotemAccType> TOTEM_ACC_TYPE = BUILDER
            .comment("The accessory type of totems, defining which slot(s) they can be placed in")
            .comment("Allowed values: \"UTILITY\", \"SPECIAL\" (default)")
            .defineEnum("totemAccType", TotemAccType.SPECIAL);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static TotemAccType totemAccType;

    @SubscribeEvent
    static void onLoad(ModConfigEvent event) {
        totemAccType = TOTEM_ACC_TYPE.get();
    }

    public enum TotemAccType {
        UTILITY,
        SPECIAL
    }
}
