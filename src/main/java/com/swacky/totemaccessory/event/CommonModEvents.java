package com.swacky.totemaccessory.event;

import com.swacky.ohmega.api.AccessoryType;
import com.swacky.ohmega.api.IAccessory;
import com.swacky.ohmega.api.events.BindAccessoriesEvent;
import com.swacky.totemaccessory.common.core.TAConfig;
import com.swacky.totemaccessory.common.core.TotemAccessory;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = TotemAccessory.MODID)
public class CommonModEvents {
    @SubscribeEvent
    public static void bindTotem(BindAccessoriesEvent event) {
        event.add(Items.TOTEM_OF_UNDYING, new IAccessory() {
            @Override
            public @NotNull AccessoryType getType() {
                return TAConfig.totemAccType;
            }
        });
    }
}
