package com.vincentreinbold.firstmod.entities.custom;

import com.vincentreinbold.firstmod.FirstMod;
import net.minecraft.client.tutorial.Tutorial;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES
            = DeferredRegister.create(ForgeRegistries.ENTITIES, FirstMod.MOD_ID);

    public static final RegistryObject<EntityType<BuffZombieEntity>> BUFF_ZOMBIE =
            ENTITY_TYPES.register("buff_zombie",
                    () -> EntityType.Builder.create(BuffZombieEntity::new, EntityClassification.MONSTER)
                            .size(1f,3f).build(new ResourceLocation(FirstMod.MOD_ID,
                                    "buff_zombie").toString()));

 //   public static final RegistryObject<EntityType<PigeonEntity>> PIGEON =
   //         ENTITY_TYPES.register("pigeon",
     //               () -> EntityType.Builder.create(PigeonEntity::new, EntityClassification.CREATURE)
      //                      .size(0.4f,0.3f).build(new ResourceLocation(FirstMod.MOD_ID,
        //                            "pigeon").toString()));



    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
