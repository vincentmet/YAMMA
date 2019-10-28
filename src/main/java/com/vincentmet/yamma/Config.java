package com.vincentmet.yamma;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import java.nio.file.Path;

@Mod.EventBusSubscriber
public class Config {
    public static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec COMMON_CONFIG;

    public static ForgeConfigSpec.IntValue radius;
    public static ForgeConfigSpec.IntValue height;
    public static ForgeConfigSpec.IntValue hungerPerOperation;

    static{
        radius = COMMON_BUILDER.comment("Radius").defineInRange("radius", 5, 0, 20);
        height = COMMON_BUILDER.comment("Height").defineInRange("height", 5, 0, 10);
        hungerPerOperation = COMMON_BUILDER.comment("Hunger Per Operation (1 hunger icon = 2, 2 hunger icons = 4, etc, etc)").defineInRange("hungerPerOperation", 2, 0, 20);

        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path){
        final CommentedFileConfig config = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
        config.load();
        spec.setConfig(config);
    }
}
