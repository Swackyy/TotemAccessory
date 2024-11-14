package com.swacky.totem_accessory.common.core.mixin;

import com.swacky.ohmega.api.AccessoryHelper;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow protected abstract boolean checkTotemDeathProtection(DamageSource pDamageSource);

    @Redirect(method = "hurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;checkTotemDeathProtection(Lnet/minecraft/world/damagesource/DamageSource;)Z"))
    private boolean hurt(LivingEntity instance, DamageSource source) {
        if (checkTotemDeathProtection(source)) {
            return true;
        }
        return totemAccessory$customCheckTotemDeathProtection(instance, source);
    }

    @Unique
    private boolean totemAccessory$customCheckTotemDeathProtection(LivingEntity instance, DamageSource source) {
        if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return false;
        }
        ItemStack stack = null;

        if (instance instanceof Player player) {
            int slot = AccessoryHelper.getSlotFor(player, Items.TOTEM_OF_UNDYING);
            if (slot >= 0) {
                ItemStack stack0 = AccessoryHelper.getStackInSlot(player, slot);
                stack = stack0.copy();
                stack0.shrink(1);
                AccessoryHelper.setSlotChanged(player, slot);
            }
        }

        if (stack != null) {
            if (instance instanceof ServerPlayer svr) {
                svr.awardStat(Stats.ITEM_USED.get(Items.TOTEM_OF_UNDYING));
                CriteriaTriggers.USED_TOTEM.trigger(svr, stack);
                instance.gameEvent(GameEvent.ITEM_INTERACT_FINISH);
            }

            instance.setHealth(1);
            instance.removeAllEffects();
            instance.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 900, 1));
            instance.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 1));
            instance.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 800, 0));
            instance.level().broadcastEntityEvent(instance, (byte) 35);
        }

        return stack != null;
    }
}
