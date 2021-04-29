package com.stal111.valhelsia_structures.block.properties;

import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;

/**
 * Mod Block State Properties
 * Valhelsia Structures - com.stal111.valhelsia_structures.block.properties.ModBlockStateProperties
 *
 * @author Valhelsia Team
 * @version 16.0.4
 * @since 2020-10-16
 */
public class ModBlockStateProperties {
    public static final BooleanProperty ATTACHED = BooleanProperty.create("attached");
    public static final BooleanProperty TREASURE = BooleanProperty.create("treasure");
    public static final BooleanProperty ROTATED = BooleanProperty.create("rotated");
    public static final BooleanProperty MIRRORED = BooleanProperty.create("mirrored");

    public static final IntegerProperty LAYERS_1_5 = IntegerProperty.create("layers", 1, 5);
    public static final IntegerProperty ROTATION_0_7 = IntegerProperty.create("rotation", 0, 7);
    public static final IntegerProperty PARTS_1_4 = IntegerProperty.create("parts", 1, 4);

    public static final EnumProperty<DungeonDoorPart> DUNGEON_DOOR_PART = EnumProperty.create("part", DungeonDoorPart.class);
}
