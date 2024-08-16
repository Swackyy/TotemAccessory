package com.swacky.totemaccessory.common.core;

import com.swacky.ohmega.api.AccessoryType;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = TotemAccessory.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TAConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.EnumValue<AccessoryType> TOTEM_ACC_TYPE = BUILDER
            .comment("The accessory type of totems, defining which slot(s) they can be placed in")
            .defineEnum("totemAccType", AccessoryType.SPECIAL);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static AccessoryType totemAccType;

    @SubscribeEvent
    static void onLoad(ModConfigEvent event) {
        totemAccType = TOTEM_ACC_TYPE.get();
    }
}
