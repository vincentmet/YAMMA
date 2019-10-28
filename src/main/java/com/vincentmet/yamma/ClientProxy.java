package com.vincentmet.yamma;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class ClientProxy {
    public static Configuration cfg;

    public void preInit(FMLPreInitializationEvent e){
        EventHandler handler = new EventHandler();
        MinecraftForge.EVENT_BUS.register(handler);
        FMLCommonHandler.instance().bus().register(handler);

        File directory = e.getModConfigurationDirectory();
        cfg = new Configuration(new File(directory.getPath(), "YAMMA.cfg"));
        Config.readCfg();
    }

    public void init(FMLInitializationEvent e){

    }

    public void postInit(FMLPostInitializationEvent e){
        if(cfg.hasChanged()){
            cfg.save();
        }
    }
}