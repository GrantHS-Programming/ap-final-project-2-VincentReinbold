package com.vincentreinbold.firstmod.item;

import com.vincentreinbold.firstmod.FirstMod;
import com.vincentreinbold.firstmod.entities.custom.ModEntityTypes;
import com.vincentreinbold.firstmod.item.custom.Firestone;
import com.vincentreinbold.firstmod.item.custom.ModSpawnEggItem;
import net.minecraft.client.tutorial.Tutorial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.lwjgl.system.CallbackI;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FirstMod.MOD_ID);

    public static final RegistryObject<Item> AMETHYST = ITEMS.register("amethyst",
            () -> new Item(new Item.Properties().group(ModItemGroup.FIRST_GROUP)));

    public static final RegistryObject<Item> FIRESTONE = ITEMS.register("firestone",
            () -> new Firestone(new Item.Properties().group(ModItemGroup.FIRST_GROUP).maxDamage(8)));

    public static final RegistryObject<ModSpawnEggItem> BUFF_ZOMBIE_SPAWN_EGG = ITEMS.register("buff_zombie_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.BUFF_ZOMBIE,0x464F56, 0x1D6336,
                    new Item.Properties().maxStackSize(1).group(ModItemGroup.FIRST_GROUP)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


}
