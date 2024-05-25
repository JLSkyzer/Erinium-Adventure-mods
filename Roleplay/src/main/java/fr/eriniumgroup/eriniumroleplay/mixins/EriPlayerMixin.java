package fr.eriniumgroup.eriniumroleplay.mixins;

import com.mojang.authlib.GameProfile;
import fr.eriniumgroup.eriniumroleplay.network.EriniumroleplayModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.extensions.IPlayerExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.*;

@Mixin(Player.class)
public abstract class EriPlayerMixin extends LivingEntity implements IPlayerExtension{
    @Shadow public abstract boolean isSpectator();

    @Shadow public abstract Abilities getAbilities();

    @Shadow public abstract void onUpdateAbilities();

    @Shadow public abstract boolean isCreative();

    @Shadow public abstract void stopFallFlying();

    @Shadow public abstract void startFallFlying();

    private final Abilities abilities = new Abilities();
    private final GameProfile gameProfile;
    private ItemStack lastItemInMainHand;
    private final ItemCooldowns cooldowns;
    private Optional<GlobalPos> lastDeathLocation;
    private final Collection<MutableComponent> prefixes;
    private final Collection<MutableComponent> suffixes;
    @Nullable
    private Pose forcedPose;
    private Component displayname;

    public EriPlayerMixin(Level p_250508_, BlockPos p_250289_, float p_251702_, GameProfile p_252153_) {
        super(EntityType.PLAYER, p_250508_);
        this.lastItemInMainHand = ItemStack.EMPTY;
        this.cooldowns = this.createItemCooldowns();
        this.lastDeathLocation = Optional.empty();
        this.prefixes = new LinkedList();
        this.suffixes = new LinkedList();
        this.displayname = null;
        this.setUUID(p_252153_.getId());
        this.gameProfile = p_252153_;
        this.moveTo((double)p_250289_.getX() + 0.5, (double)(p_250289_.getY() + 1), (double)p_250289_.getZ() + 0.5, p_251702_, 0.0F);
        this.rotOffs = 180.0F;
    }

    protected void updatePlayerPose() {
        if (this.forcedPose != null) {
            this.setPose(this.forcedPose);
        } else {
            if (this.canPlayerFitWithinBlocksAndEntitiesWhen(Pose.SWIMMING)) {
                Pose pose;
                if (this.isFallFlying() && !this.getData(EriniumroleplayModVariables.PLAYER_VARIABLES).isDead) {
                    pose = Pose.FALL_FLYING;
                } else if (this.isSleeping() && !this.getData(EriniumroleplayModVariables.PLAYER_VARIABLES).isDead) {
                    pose = Pose.SLEEPING;
                } else if (this.isSwimming() && !this.getData(EriniumroleplayModVariables.PLAYER_VARIABLES).isDead) {
                    pose = Pose.SWIMMING;
                } else if (this.isAutoSpinAttack() && !this.getData(EriniumroleplayModVariables.PLAYER_VARIABLES).isDead) {
                    pose = Pose.SPIN_ATTACK;
                } else if (this.isShiftKeyDown() && !this.abilities.flying && !this.getData(EriniumroleplayModVariables.PLAYER_VARIABLES).isDead) {
                    pose = Pose.CROUCHING;
                } else if (this.getData(EriniumroleplayModVariables.PLAYER_VARIABLES).isDead) {
                    pose = Pose.SLEEPING;
                } else {
                    pose = Pose.STANDING;
                }

                Pose pose1;
                if (!this.isSpectator() && !this.isPassenger() && !this.canPlayerFitWithinBlocksAndEntitiesWhen(pose)) {
                    if (this.canPlayerFitWithinBlocksAndEntitiesWhen(Pose.CROUCHING)) {
                        pose1 = Pose.CROUCHING;
                    } else {
                        pose1 = Pose.SWIMMING;
                    }
                } else {
                    pose1 = pose;
                }

                this.setPose(pose1);
            }

        }
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;updateIsUnderwater()Z"))
    private void tick(CallbackInfo ci){
        this.noPhysics = this.getData(EriniumroleplayModVariables.PLAYER_VARIABLES).vanish_mode || this.isSpectator();

        if (this.getData(EriniumroleplayModVariables.PLAYER_VARIABLES).vanish_mode == true) {
            this.setOnGround(false);
            this.getAbilities().flying = true;
            this.onUpdateAbilities();
            this.setInvisible(true);
        }
    }

    @Override
    protected void doWaterSplashEffect() {
        if (!this.isSpectator() && !this.getData(EriniumroleplayModVariables.PLAYER_VARIABLES).vanish_mode) {
            super.doWaterSplashEffect();
        }
    }

    protected boolean canPlayerFitWithinBlocksAndEntitiesWhen(Pose p_294172_) {
        return this.level().noCollision(this, this.getDimensions(p_294172_).makeBoundingBox(this.position()).deflate(1.0E-7));
    }

    protected ItemCooldowns createItemCooldowns() {
        return new ItemCooldowns();
    }

    @Override
    public LivingEntity self() {
        return super.self();
    }
}
