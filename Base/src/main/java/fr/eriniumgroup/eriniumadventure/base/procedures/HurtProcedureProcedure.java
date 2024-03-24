package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.stats.Stats;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;

import fr.eriniumgroup.eriniumadventure.base.init.EriniumAdventureModParticleTypes;

public class HurtProcedureProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, DamageSource damagesource, Entity entity, double amount) {
		if (damagesource == null || entity == null)
			return;
		Entity erientity = null;
		double tempDAMAGEVALUE = 0;
		erientity = entity;
		tempDAMAGEVALUE = damagesource.getFoodExhaustion();
		if (new Object() {
			private float getDamageAfterArmorAbsorb(DamageSource eridamageSource, float eridamage) {
				if (entity instanceof LivingEntity entity1) {
					if (eridamageSource.is(DamageTypeTags.BYPASSES_EFFECTS)) {
						return eridamage;
					} else {
						if (entity1.hasEffect(MobEffects.DAMAGE_RESISTANCE) && !eridamageSource.is(DamageTypeTags.BYPASSES_RESISTANCE)) {
							int i = (entity1.getEffect(MobEffects.DAMAGE_RESISTANCE).getAmplifier() + 1) * 5;
							int j = 25 - i;
							float f = (eridamage * (float) j);
							float f1 = eridamage;
							eridamage = Math.max(f / 25.0F, 0.0F);
							float f2 = f1 - eridamage;
							if (f2 > 0.0F && f2 < 3.4028235E37F) {
								if (entity1 instanceof ServerPlayer) {
									((ServerPlayer) entity1).awardStat(Stats.CUSTOM.get(Stats.DAMAGE_RESISTED), Math.round(f2 * 10.0F));
								} else if (eridamageSource.getEntity() instanceof ServerPlayer) {
									((ServerPlayer) eridamageSource.getEntity()).awardStat(Stats.CUSTOM.get(Stats.DAMAGE_DEALT_RESISTED), Math.round(f2 * 10.0F));
								}
							}
						}
						if (eridamage <= 0.0F) {
							return 0.0F;
						} else if (eridamageSource.is(DamageTypeTags.BYPASSES_ENCHANTMENTS)) {
							return eridamage;
						} else if (entity1.isUsingItem() && !entity1.getItemInHand(entity1.getUsedItemHand()).isEmpty()) {
							net.minecraft.world.item.Item item = entity1.getItemInHand(entity1.getUsedItemHand()).getItem();
							if (!entity1.getItemInHand(entity1.getUsedItemHand()).canPerformAction(net.minecraftforge.common.ToolActions.SHIELD_BLOCK)) {
								return eridamage;
							} else {
								return 0.0F;
							}
						} else {
							if (entity1.getArmorValue() > 0) {
								int k = EnchantmentHelper.getDamageProtection(entity1.getArmorSlots(), eridamageSource);
								if (k > 0) {
									eridamage = net.minecraft.world.damagesource.CombatRules.getDamageAfterMagicAbsorb(eridamage, (float) k);
								}
								if (!eridamageSource.is(DamageTypeTags.BYPASSES_ARMOR)) {
									eridamage = net.minecraft.world.damagesource.CombatRules.getDamageAfterAbsorb(eridamage, (float) entity1.getArmorValue(),
											(float) entity1.getAttributeValue(net.minecraft.world.entity.ai.attributes.Attributes.ARMOR_TOUGHNESS));
								}
								return eridamage;
							} else {
								return eridamage;
							}
						}
					}
				}
				return 0.0F;
			}
		}.getDamageAfterArmorAbsorb(damagesource, (float) amount) > 0) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (EriniumAdventureModParticleTypes.BLOOD.get()), x, (y + 1), z, 20, 1, 0, 1, 0.3);
		}
	}
}
