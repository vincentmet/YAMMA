package com.vincentmet.yamma;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

public class ItemMiner extends Item {
    public ItemMiner(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        if(player.getFoodStats().getFoodLevel() <= 0 && !player.isCreative()){
            player.sendStatusMessage(new TranslationTextComponent("Too hungry to mine"), true);
        }else{
            //IShape shape = new Shapes.Cube(player.getPosition(), Config.radius.get());
            //IShape shape = new Shapes.Cylinder(player.getPosition(), Config.radius.get(), Config.height.get());
            IShape shape = new Shapes.Pyramid(player.getPosition(), Config.radius.get(), Config.height.get());
            for(BlockPos pos : shape.getAllBlockPos()){
                if (world.getBlockState(pos).getBlockHardness(world, pos) != -1) {
                    //ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(world.getBlockState(pos).getBlock(), 1));
                    player.addItemStackToInventory(new ItemStack(world.getBlockState(pos).getBlock(), 1));
                    world.setBlockState(pos, Blocks.AIR.getDefaultState());
                }
            }
            if(!player.isCreative()){
                player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel() - Config.hungerPerOperation.get());
            }
            if(player.getFoodStats().getFoodLevel() < 0) {
                player.getFoodStats().setFoodLevel(0);
            }
        }
        return super.onItemRightClick(world, player, hand);
    }
}