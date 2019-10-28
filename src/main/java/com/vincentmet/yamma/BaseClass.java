package com.vincentmet.yamma;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(Ref.MODID)
public class BaseClass {
    public static BaseClass INSTANCE;

    public BaseClass(){
        INSTANCE = this;
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
        Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(Ref.MODID + "-common.toml"));
        MinecraftForge.EVENT_BUS.register(EventHandler.class);
    }
}
