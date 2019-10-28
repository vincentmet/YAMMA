package com.vincentmet.yamma;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class EventHandler {
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event){
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        EntityPlayer player = event.getPlayer();
        if(player.getFoodStats().getFoodLevel() <= 0 && !player.isCreative()){
            player.sendStatusMessage(new TextComponentString("Too hungry to mine"), true);
        }else {
            if(!world.isRemote) {
                if(player.isSneaking()){
                    if(event.getState().getBlock() == Blocks.STONE) {
                        for(int x = -Config.radius; x < Config.radius; x++) {
                            for(int z = -Config.radius; z < Config.radius; z++) {
                                for(int y = 0; y < Config.height; y++) {
                                    BlockPos loopPos = pos.add(x, y, z);
                                    IBlockState currentBlockState = world.getBlockState(loopPos);
                                    Block currentBlock = currentBlockState.getBlock();
                                    if (currentBlockState.getBlockHardness(world, loopPos) != -1) {
                                        player.inventory.addItemStackToInventory(new ItemStack(currentBlock));
                                        world.setBlockState(loopPos, Blocks.AIR.getDefaultState());
                                    }
                                }
                            }
                        }
                        if(!player.isCreative()){
                            player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel() - Config.hungerPerOperation);
                        }
                        if(player.getFoodStats().getFoodLevel() < 0) {
                            player.getFoodStats().setFoodLevel(0);
                        }
                    }
                }
            }

        }
    }
}