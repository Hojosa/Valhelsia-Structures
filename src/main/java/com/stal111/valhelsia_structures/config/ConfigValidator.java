package com.stal111.valhelsia_structures.config;

import com.stal111.valhelsia_structures.init.ModStructures;
import com.stal111.valhelsia_structures.utils.StructureType;
import com.stal111.valhelsia_structures.world.structures.AbstractValhelsiaStructure;
import com.stal111.valhelsia_structures.world.structures.RemovedStructure;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;
import net.valhelsia.valhelsia_core.util.AbstractConfigValidator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Config Validator
 * Valhelsia Structures - com.stal111.valhelsia_structures.config.ConfigValidator
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-02-16
 */
public class ConfigValidator extends AbstractConfigValidator {

    @Override
    public void validate() {
        for (Map.Entry<StructureType, List<AbstractValhelsiaStructure>> entry : ModStructures.STRUCTURES_MAP.entrySet()) {
            for (AbstractValhelsiaStructure structure : entry.getValue()) {
                if (!(structure instanceof RemovedStructure)) {
                    StructureConfigEntry configEntry = structure.getStructureConfigEntry();

                    if ((configEntry.configuredSpacing.get() - configEntry.configuredSeparation.get()) <= 0) {
                        addError(new TranslationTextComponent("gui.valhelsia_structures.config.spacing_error"), "structures." + structure.getName()  + ".spacing", new TranslationTextComponent("gui.valhelsia_structures.config.spacing_solution", configEntry.configuredSeparation.get() + 1));
                    }

                    for (String string : configEntry.configuredBiomeCategories.get()) {
                        if (Arrays.stream(Biome.Category.values()).noneMatch(category -> category.getName().equals(string))) {
                            addError(new TranslationTextComponent("gui.valhelsia_structures.config.invalid_biome_category").appendString(" " + string), "structures." + structure.getName()  + ".biome_categories", new TranslationTextComponent("gui.valhelsia_core.config.ignoring"));
                        }
                    }

                    for (String string : configEntry.configuredBlacklistedBiomes.get()) {
                        if (!ForgeRegistries.BIOMES.containsKey(new ResourceLocation(string))) {
                            addError(new TranslationTextComponent("gui.valhelsia_structures.config.invalid_blacklisted_biome").appendString(" " + string), "structures." + structure.getName()  + ".blacklisted_biomes", new TranslationTextComponent("gui.valhelsia_core.config.ignoring"));
                        }
                    }
                }
            }
        }
    }
}
