package com.vincentreinbold.firstmod.item.custom;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.NoteBlockEvent;

import java.util.Objects;

public class Firestone extends Item {
    public Firestone(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        World world = context.getWorld();

        if(!world.isRemote) {
            PlayerEntity playerEntity = Objects.requireNonNull(context.getPlayer());
            BlockState clickedBlock = world.getBlockState(context.getPos());

            rightClickOnCertianBlockState(clickedBlock, context, playerEntity);
            stack.damageItem(1, playerEntity, player -> player.sendBreakAnimation(context.getHand()));
        }
        return super.onItemUseFirst(stack, context);
    }

    private void rightClickOnCertianBlockState(BlockState clickedBlock, ItemUseContext context,
                                               PlayerEntity playerEntity) {
        boolean playerIsNotOnFire = !playerEntity.isBurning();


        if (random.nextFloat() > 0.5f) {
            lightEntityOnFire(playerEntity, 6);
        } else if (playerIsNotOnFire && blockIsValidForResistance(clickedBlock)) {
            gainFireResAndDesBlock(playerEntity, context.getWorld(), context.getPos());

        } else {
            lightGroundOnFire(context);
        }

    }

    private boolean blockIsValidForResistance(BlockState clickedBlock) {
        return clickedBlock.getBlock() == Blocks.OBSIDIAN;
    }

    public static void lightEntityOnFire(Entity entity, int second) {
        entity.setFire(second);
    }
    private void gainFireResAndDesBlock(PlayerEntity playerEntity, World world, BlockPos pos) {
        gainFireRes(playerEntity);
        world.destroyBlock(pos,  false);

    }

    public static void gainFireRes(PlayerEntity playerEntity) {
        playerEntity.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 200));
    }

    public static void lightGroundOnFire(ItemUseContext context) {
        PlayerEntity playerentity = context.getPlayer();
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        BlockState blockstate = world.getBlockState(blockpos);
        BlockPos blockpos1 = context.getPos().offset(context.getFace());
            if (AbstractFireBlock.canLightBlock(world, blockpos1, context.getPlacementHorizontalFacing())) {
                world.playSound(playerentity, blockpos1, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
                BlockState blockstate1 = AbstractFireBlock.getFireForPlacement(world, blockpos1);
                world.setBlockState(blockpos1, blockstate1, 11);




            } else {

            }
        }
    }






