package com.vincentmet.yamma;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.loading.FMLEnvironment;

import java.util.ArrayList;
import java.util.List;

public enum Shapes {
    CUBE(),
    CYLINDER(),
    PYRAMID(),
    CONE();

    Shapes(){

    }

    public static class Cube implements IShape{
        private List<BlockPos> allBlockPos = new ArrayList<>();
        public Cube(BlockPos origin, int radius, int height) {
            for (int x = -radius; x < radius; x++) {
                for (int z = -radius; z < radius; z++) {
                    for (int y = 0; y < height ; y++) {
                        allBlockPos.add(origin.add(x, y, z));
                    }
                }
            }
        }

        @Override
        public List<BlockPos> getAllBlockPos() {
            return allBlockPos;
        }
    }

    public static class Cylinder implements IShape{
        private List<BlockPos> allBlockPos = new ArrayList<>();
        public Cylinder(BlockPos origin, int radius, int height){
            for (int x = -radius; x < radius; x++) {
                for (int z = -radius; z < radius; z++) {
                    if(x*x+z*z < radius*radius){
                        for (int y = 0; y < height; y++) {
                            allBlockPos.add(origin.add(x, y, z));
                        }
                    }
                }
            }
        }

        @Override
        public List<BlockPos> getAllBlockPos() {
            return allBlockPos;
        }
    }

    public static class Pyramid implements IShape{
        private List<BlockPos> allBlockPos = new ArrayList<>();
        public Pyramid(BlockPos origin, int radius, int height){
            for (int y = 0; y < height; y++) {
                int rfh = (int)getRadiusForHeight(radius, height, y);
                for (int x = -rfh; x <= rfh; x++) {
                    for (int z = -rfh; z <= rfh; z++) {
                        allBlockPos.add(origin.add(-x, -y + height - 1, -z));
                    }
                }
            }
        }

        private float getRadiusForHeight(int ogRadius, int ogHeight, int currentHeight){
            float percentageOfHeight = (float)currentHeight/(float)ogHeight;
            return ogRadius * percentageOfHeight;
        }

        @Override
        public List<BlockPos> getAllBlockPos() {
            return allBlockPos;
        }
    }

    public static class Cone implements IShape{
        private List<BlockPos> allBlockPos = new ArrayList<>();
        public Cone(BlockPos origin, int radius, int height){
            for (int y = 0; y < height; y++) {
                int rfh = (int)getRadiusForHeight(radius, height, y);
                for (int x = -rfh; x <= rfh; x++) {
                    for (int z = -rfh; z <= rfh; z++) {
                        if(x*x+z*z < rfh*rfh) {
                            allBlockPos.add(origin.add(-x, -y + height - 1, -z));
                        }
                    }
                }
            }
        }

        private float getRadiusForHeight(int ogRadius, int ogHeight, int currentHeight){
            float percentageOfHeight = (float)currentHeight/(float)ogHeight;
            return ogRadius * percentageOfHeight;
        }

        @Override
        public List<BlockPos> getAllBlockPos() {
            return allBlockPos;
        }
    }
}