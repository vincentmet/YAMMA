package com.vincentmet.yamma;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Ref.MODID, version = "1.12.2-0.2", name = "Yet Another Mining Mod Attempt")
public class BaseClass {
    @SidedProxy(clientSide = "com.vincentmet.yamma.ClientProxy", serverSide = "com.vincentmet.yamma.ClientProxy")
    public static ClientProxy proxy;

    @Mod.Instance(Ref.MODID)
    public static BaseClass instance;


    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent e){
        proxy.preInit(e);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e){
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent e){
        proxy.postInit(e);
    }
}
