package com.swacky.totemaccessory.common.core;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(TotemAccessory.MODID)
public class TotemAccessory {
    public static final String MODID = "totemaccessory";

    public TotemAccessory() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TAConfig.SPEC);
    }
}
