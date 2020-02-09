package com.vincentmet.yamma;

import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;
import java.util.List;

public class ItemMiner extends Item {
    public static Shapes state = Shapes.CUBE;//todo to enum
    public ItemMiner(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        if(player.getFoodStats().getFoodLevel() <= 0 && !player.isCreative()){
            player.sendStatusMessage(new TranslationTextComponent("Too hungry to mine"), true);
        }else{
            IShape shape = null;
            switch (state){
                case CUBE:
                    shape = new Shapes.Cube(player.getPosition(), Config.radius.get(), Config.height.get());
                    break;
                case CYLINDER:
                    shape = new Shapes.Cylinder(player.getPosition(), Config.radius.get(), Config.height.get());
                    break;
                case PYRAMID:
                    shape = new Shapes.Pyramid(player.getPosition(), Config.radius.get(), Config.height.get());
                    break;
                case CONE:
                    shape = new Shapes.Cone(player.getPosition(), Config.radius.get(), Config.height.get());
                    break;
            }
            for(BlockPos pos : shape.getAllBlockPos()){
                if (world.getBlockState(pos).getBlockHardness(world, pos) != -1) {
                    if(Config.shouldDropDrops.get()){
                        if(player.isCreative()){
                            player.addItemStackToInventory(new ItemStack(world.getBlockState(pos).getBlock(), 1));
                        }else{
                            ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(world.getBlockState(pos).getBlock(), 1));
                        }
                    }
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

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, PlayerEntity player) {
        switch(state){
            case CUBE:
                state = Shapes.CYLINDER;
                break;
            case CYLINDER:
                state = Shapes.PYRAMID;
                break;
            case PYRAMID:
                state = Shapes.CONE;
                break;
            case CONE:
                state = Shapes.CUBE;
                break;
        }
        System.out.println(state);
        return false;
    }

    @Override
    public void addInformation(ItemStack itemStack, @Nullable World world, List<ITextComponent> lines, ITooltipFlag tooltipFlag) {
        lines.add(new TranslationTextComponent("Current mode: " + state));
        super.addInformation(itemStack, world, lines, tooltipFlag);
    }
}