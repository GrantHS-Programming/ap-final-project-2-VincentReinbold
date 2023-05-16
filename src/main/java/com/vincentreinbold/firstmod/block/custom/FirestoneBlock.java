package com.vincentreinbold.firstmod.block.custom;

import com.vincentreinbold.firstmod.item.custom.Firestone;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class FirestoneBlock extends Block {
    public FirestoneBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState p_225533_1_, World p_225533_2_, BlockPos p_225533_3_,
                                             PlayerEntity p_225533_4_, Hand p_225533_5_, BlockRayTraceResult p_225533_6_) {
        if(!p_225533_2_.isRemote()) {
            if(p_225533_5_ == Hand.MAIN_HAND) {
                System.out.println("I right clicked a firestone block for the main hand.");
            }
            if(p_225533_5_ == Hand.OFF_HAND) {
                System.out.println("I right clicked a firestone block for the off hand.");
            }
        }
        return super.onBlockActivated(p_225533_1_, p_225533_2_, p_225533_3_, p_225533_4_, p_225533_5_, p_225533_6_);
    }

    @Override
    public void onBlockClicked(BlockState p_196270_1_, World p_196270_2_, BlockPos p_196270_3_, PlayerEntity p_196270_4_) {
        if(!p_196270_2_.isRemote()) {
            System.out.println("I right clicked the block");
        }
    }

    @Override
    public void onEntityWalk(World p_176199_1_, BlockPos p_176199_2_, Entity p_176199_3_) {
        Firestone.lightEntityOnFire(p_176199_3_, 5 );
        super.onEntityWalk(p_176199_1_, p_176199_2_, p_176199_3_);
    }
}
