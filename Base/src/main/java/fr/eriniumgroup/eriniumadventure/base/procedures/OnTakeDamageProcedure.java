package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.stats.Stats;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import javax.annotation.Nullable;

import fr.eriniumgroup.eriniumadventure.base.network.EriniumAdventureModVariables;

@Mod.EventBusSubscriber
public class OnTakeDamageProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getSource(), event.getEntity(), event.getAmount());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, DamageSource damagesource, Entity entity, double amount) {
		execute(null, world, x, y, z, damagesource, entity, amount);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, DamageSource damagesource, Entity entity, double amount) {
		if (damagesource == null || entity == null)
			return;
		double slot = 0;
		if (entity instanceof Player || entity instanceof ServerPlayer) {
			if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false) && !damagesource.is(DamageTypes.GENERIC_KILL)
					&& !damagesource.is(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("erinium_adventure:damage_admin")))) {
				if (damagesource.is(DamageTypes.IN_FIRE) || damagesource.is(DamageTypes.ON_FIRE) || damagesource.is(DamageTypes.HOT_FLOOR)) {
					if ((entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAdventureModVariables.PlayerVariables())).health_damage == 0) {
						HurtProcedureProcedure.execute(world, x, y, z, damagesource, entity, amount);
						{
							double _setval = (entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAdventureModVariables.PlayerVariables())).health - (new Object() {
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
							}.getDamageAfterArmorAbsorb(damagesource, (float) amount)
									* (1 - (entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAdventureModVariables.PlayerVariables())).fire_reduction));
							entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.health = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							double _setval = 20;
							entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.health_damage = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
					}
				} else if (damagesource.is(DamageTypes.LAVA)) {
					if ((entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAdventureModVariables.PlayerVariables())).health_damage == 0) {
						HurtProcedureProcedure.execute(world, x, y, z, damagesource, entity, amount);
						{
							double _setval = (entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAdventureModVariables.PlayerVariables())).health - (new Object() {
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
							}.getDamageAfterArmorAbsorb(damagesource, (float) amount)
									* (1 - (entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAdventureModVariables.PlayerVariables())).fire_reduction));
							entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.health = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							double _setval = 10;
							entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.health_damage = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
					}
				} else if (damagesource.is(DamageTypes.DROWN) || damagesource.is(DamageTypes.CACTUS) || damagesource.is(DamageTypes.FREEZE) || damagesource.is(DamageTypes.IN_WALL) || damagesource.is(DamageTypes.INDIRECT_MAGIC)
						|| damagesource.is(DamageTypes.MAGIC) || damagesource.is(DamageTypes.SWEET_BERRY_BUSH) || damagesource.is(DamageTypes.WITHER)) {
					if ((entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAdventureModVariables.PlayerVariables())).health_damage == 0) {
						HurtProcedureProcedure.execute(world, x, y, z, damagesource, entity, amount);
						{
							double _setval = (entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAdventureModVariables.PlayerVariables())).health - new Object() {
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
							}.getDamageAfterArmorAbsorb(damagesource, (float) amount);
							entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.health = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							double _setval = 20;
							entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.health_damage = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
					}
				} else {
					HurtProcedureProcedure.execute(world, x, y, z, damagesource, entity, amount);
					{
						double _setval = (entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAdventureModVariables.PlayerVariables())).health - new Object() {
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
						}.getDamageAfterArmorAbsorb(damagesource, (float) amount);
						entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.health = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				}
				if (entity instanceof LivingEntity _entity)
					_entity.setHealth(20);
			}
		}
	}
}
