package com.vincentmet.yamma;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler {
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event){
        event.getRegistry().registerAll(
                Objects.Items.itemMiner = new ItemMiner(new Item.Properties().group(ItemGroup.BUILDING_BLOCKS).maxStackSize(1)).setRegistryName(new ResourceLocation(Ref.MODID, "item_miner"))
        );
    }
}