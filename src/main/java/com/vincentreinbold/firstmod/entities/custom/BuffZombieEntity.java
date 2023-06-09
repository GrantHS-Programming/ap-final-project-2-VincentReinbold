package com.vincentreinbold.firstmod.entities.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;



public class BuffZombieEntity extends ZombieEntity {
    public BuffZombieEntity(EntityType<? extends ZombieEntity> p_i48549_1_, World p_i48549_2_) {
        super(p_i48549_1_, p_i48549_2_);
    }

        public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
            return MobEntity.func_233666_p_()
                    .createMutableAttribute(Attributes.MAX_HEALTH, 20.0D)
                    .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.33D)
                    .createMutableAttribute(Attributes.ATTACK_DAMAGE, 13.0D)
                    .createMutableAttribute(Attributes.FOLLOW_RANGE, 50.0D)
                    .createMutableAttribute(Attributes.ZOMBIE_SPAWN_REINFORCEMENTS);
    }
    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.goalSelector.addGoal(2, new ZombieAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp(ZombifiedPiglinEntity.class));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));

    }
    @Override
    protected int getExperiencePoints(PlayerEntity player) { return 3 + this.world.rand.nextInt(5);}

    @Override
    protected SoundEvent getDeathSound() { return SoundEvents.ENTITY_HOGLIN_DEATH;}

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.ENTITY_IRON_GOLEM_HURT;}

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn ) {this.playSound(SoundEvents.ENTITY_HOGLIN_STEP, 0.20F, 0.5F);}

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (!super.attackEntityAsMob(entityIn)) {
            return false;
        } else {
            if (entityIn instanceof LivingEntity) {
                ((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 3));
            }
            return true;
        }
    }

}
