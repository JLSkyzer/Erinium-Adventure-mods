package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.CombatRules;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class TestProcedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		double slot = 0;
		for (int index0 = 0; index0 < 4; index0++) {
			{
				ItemStack _ist = (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) slot)) : ItemStack.EMPTY);
				if (_ist.hurt(1, RandomSource.create(), null)) {
					_ist.shrink(1);
					_ist.setDamageValue(0);
				}
			}
			slot = slot + 1;

			/*if(entity instanceof LivingEntity livingEntity){
			if (entity1 != null && !p_21016_.is(DamageTypeTags.IS_EXPLOSION)) {
				double d0 = entity1.getX() - this.getX();

				double d1;
				for(d1 = entity1.getZ() - this.getZ(); d0 * d0 + d1 * d1 < 1.0E-4D; d1 = (Math.random() - Math.random()) * 0.01D) {
					d0 = (Math.random() - Math.random()) * 0.01D;
				}

				this.knockback((double)0.4F, d0, d1);
				if (!flag) {
					this.indicateDamage(d0, d1);
				}
			}
		}*/


			DamageSource damagesource = null;
			int amount = 10;

			{
				float damage = (new Object() {
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
									if (entity1.getArmorValue() > 0){
										int k = EnchantmentHelper.getDamageProtection(entity1.getArmorSlots(), eridamageSource);
										if (k > 0) {
											eridamage = net.minecraft.world.damagesource.CombatRules.getDamageAfterMagicAbsorb(eridamage, (float) k);
										}
										if (!eridamageSource.is(DamageTypeTags.BYPASSES_ARMOR)) {
											eridamage = net.minecraft.world.damagesource.CombatRules.getDamageAfterAbsorb(eridamage, (float)entity1.getArmorValue(), (float)entity1.getAttributeValue(net.minecraft.world.entity.ai.attributes.Attributes.ARMOR_TOUGHNESS));
										}
										return eridamage;
									}else {
										return eridamage;
									}
								}
							}
						}
						return 0.0F;
					}
				}.getDamageAfterArmorAbsorb(damagesource, (float) amount));
			}
		}
	}
}
