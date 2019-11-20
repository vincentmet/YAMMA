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
                if(x>0&&x<10) {
                    for (int z = -radius; z < radius; z++) {
                        if(z>0&&z<10)
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
        public Cylinder(int radius, int height){

        }

        @Override
        public List<BlockPos> getAllBlockPos() {
            return allBlockPos;
        }
    }
}
        }

        @Override
        public List<BlockPos> getAllBlockPos() {
            return allBlockPos;
        }
    }
}