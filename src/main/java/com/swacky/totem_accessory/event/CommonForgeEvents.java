package com.swacky.totem_accessory.event;

import com.swacky.ohmega.api.AccessoryHelper;
import com.swacky.totem_accessory.common.core.TotemAccessory;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = TotemAccessory.MODID)
public class CommonForgeEvents {
    @SubscribeEvent
    public static void hoverText(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.is(Items.TOTEM_OF_UNDYING)) {
            List<Component> tooltip = event.getToolTip();
            tooltip.add(MutableComponent.create(new TranslatableContents("item." + TotemAccessory.MODID + ".totem_of_undying.tooltip", null, TranslatableContents.NO_ARGS)).withStyle(ChatFormatting.GRAY));
            tooltip.add(AccessoryHelper.getTypeTooltip(Items.TOTEM_OF_UNDYING));
        }
    }
}
