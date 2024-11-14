package com.swacky.totem_accessory.datagen.client.lang;

import com.swacky.totem_accessory.common.core.TotemAccessory;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModEnUsProvider extends LanguageProvider {
    public ModEnUsProvider(PackOutput output) {
        super(output, TotemAccessory.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("dataPack." + TotemAccessory.MODID + ".description", "Mod resources for Totem Accessory");
        add("dataPack.totem_acc_type.title", "Totem Accessory Type");
        add("dataPack.totem_acc_type.description", "Adds a dedicated Accessory Type for Totems of Undying");
        add("item." + TotemAccessory.MODID + ".totem_of_undying.tooltip", "Prevents certain death");
        add("accessory_type." + TotemAccessory.MODID + ".totem", "Totem");
    }
}
