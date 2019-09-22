package com.vincentmet.yamma;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "yamma", bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler {
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event){ //todo fix this
        World world = event.getWorld().getWorld();
        BlockPos pos = event.getPos();
        PlayerEntity player = event.getPlayer();
        if(player.getFoodStats().getFoodLevel() <= 0 && !player.isCreative()){
            player.sendStatusMessage(new TranslationTextComponent("Too hungry to mine"), true);
        }else {
            if(Minecraft.getInstance().gameSettings.keyBindSneak.isKeyDown()){
                if(!world.isRemote) {
                    if(event.getState().getBlock() == Blocks.STONE) {
                        for(int x = -5; x < 5; x++) {
                            for(int z = -5; z < 5; z++) {
                                for(int y = 0; y < 5; y++) {
                                    BlockPos loopPos = pos.add(x, y, z);
                                    BlockState currentBlockState = world.getBlockState(loopPos);
                                    Block currentBlock = currentBlockState.getBlock();
                                    if (currentBlockState.getBlockHardness(world, loopPos) != -1) {
                                        player.inventory.addItemStackToInventory(new ItemStack(currentBlock));
                                        world.setBlockState(loopPos, Blocks.AIR.getDefaultState());
                                    }
                                }
                            }
                        }
                        if(!player.isCreative()){
                            player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel() - 2);
                        }
                        if(player.getFoodStats().getFoodLevel() < 0) {
                            player.getFoodStats().setFoodLevel(0);
                        }
                    }
                }
            }

        }
    }
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event){
        System.out.println("Test");
    }
}