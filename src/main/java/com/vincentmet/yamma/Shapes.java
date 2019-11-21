package com.vincentmet.yamma;

import net.minecraft.util.math.BlockPos;
import java.util.ArrayList;
import java.util.List;

public class Shapes {
    public static class Cube implements IShape{
        private List<BlockPos> allBlockPos = new ArrayList<>();
        public Cube(BlockPos origin, int radius) {
            for (int x = -radius; x < radius; x++) {
                for (int z = -radius; z < radius; z++) {
                    for (int y = 0; y < radius; y++) {
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
            int currentSize = radius;
            for (int y = 0; y < height; y++) {
                for (int x = -currentSize; x <= currentSize; x++) {
                    for (int z = -currentSize; z <= currentSize; z++) {
                        allBlockPos.add(origin.add(x, y, z));
                    }
                }
                currentSize -= height/radius;
            }
        }

        @Override
        public List<BlockPos> getAllBlockPos() {
            return allBlockPos;
        }
    }
}