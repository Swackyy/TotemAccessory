package com.swacky.totem_accessory.event;

import com.swacky.ohmega.api.AccessoryHelper;
import com.swacky.ohmega.api.IAccessory;
import com.swacky.totem_accessory.common.core.TotemAccessory;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.Optional;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = TotemAccessory.MODID)
public class CommonModEvents {
    @SubscribeEvent
    public static void bindTotem(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> AccessoryHelper.bindAccessory(Items.TOTEM_OF_UNDYING, new IAccessory() {
            @Override
            public boolean autoSync(ServerPlayer player) {
                return true;
            }
        }));
    }

    @SubscribeEvent
    public static void addPack(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.SERVER_DATA) {
            PackLocationInfo info = new PackLocationInfo("totem_acc_type", Component.translatable("dataPack.totem_acc_type.title"), PackSource.FEATURE, Optional.empty());
            PathPackResources.PathResourcesSupplier resources = new PathPackResources.PathResourcesSupplier(ModList.get().getModFileById(TotemAccessory.MODID).getFile().findResource("data/" + TotemAccessory.MODID + "/datapacks/totem_acc_type"));
            PackSelectionConfig config = new PackSelectionConfig(false, Pack.Position.TOP, false);
            Pack pack = Pack.readMetaAndCreate(info, resources, PackType.SERVER_DATA, config);
            event.addRepositorySource((consumer) -> consumer.accept(pack));
        }
    }
}
