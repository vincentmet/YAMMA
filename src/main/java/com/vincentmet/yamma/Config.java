package com.vincentmet.yamma;

import net.minecraftforge.common.config.Configuration;

public class Config {
    public static int radius = 5;
    public static int height = 5;
    public static int hungerPerOperation = 2;

    public static void readCfg(){
        Configuration cfg = ClientProxy.cfg;
        try{
            cfg.load();
            initCfg(cfg);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cfg.hasChanged()){
                cfg.save();
            }
        }
    }
    private static void initCfg(Configuration cfg){
        radius = cfg.getInt("Radius from block you mined (diameter will be 2n+1)", "sizes", radius, 0, 20, "Max 20");
        height = cfg.getInt("Height that will be mined (above the block you mined)", "sizes", height, 0, 10, "Max 10");
        hungerPerOperation = cfg.getInt("Hunger per operation", "hunger", hungerPerOperation, 1, 2160, "Max 20 (10 hunger icons)");
    }
}
