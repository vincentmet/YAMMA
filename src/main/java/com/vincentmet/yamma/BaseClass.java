package com.vincentmet.yamma;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("yamma")
public class BaseClass {
    public BaseClass(){
        MinecraftForge.EVENT_BUS.register(EventHandler.class);
    }
}
