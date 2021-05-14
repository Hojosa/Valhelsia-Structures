package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.config.StructureConfigEntry;
import com.stal111.valhelsia_structures.init.ModStructureFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;

/**
 * Big Tree Structure
 * Valhelsia-Structures - com.stal111.valhelsia_structures.world.structures.BigTreeStructure
 *
 * @author Valhelsia Team
 * @version 16.0.3
 * @since 2021-05-14
 */

public class BigTreeStructure extends AbstractValhelsiaStructure {

    public BigTreeStructure(Codec<VillageConfig> villageConfigCodec) {
        super(villageConfigCodec, "big_tree", 2,
                new StructureConfigEntry(0.7D, 30, 8,
                        Biome.Category.PLAINS.getName(),
                        Biome.Category.FOREST.getName()
                ));
    }

    @Override
    public int getSeedModifier() {
        return 35122018;
    }

    @Override
    public StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> getStructureFeature() {
        return ModStructureFeatures.BIG_TREE;
    }
}