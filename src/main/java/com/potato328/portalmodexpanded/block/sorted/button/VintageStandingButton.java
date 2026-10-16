package com.potato328.portalmodexpanded.block.sorted.button;

import net.minecraft.block.BlockState;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.portalmod.common.sorted.button.ButtonMode;
import net.portalmod.common.sorted.button.StandingButtonBlock;
import net.portalmod.core.math.BiHashMap;
import net.portalmod.core.math.Mat4;
import net.portalmod.core.math.Vec3;
import net.portalmod.core.math.VoxelShapeGroup;

public class VintageStandingButton extends StandingButtonBlock {

    private static final BiHashMap<Direction, DoubleBlockHalf, VoxelShapeGroup> SHAPE = new BiHashMap<>();

    public VintageStandingButton(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.UP)
                .setValue(HALF, DoubleBlockHalf.LOWER)
                .setValue(PRESSED, false)
                .setValue(ACTIVE, false)
                .setValue(MODE, ButtonMode.NORMAL)
        );
        this.initAABBs();
    }
    private static final VoxelShapeGroup LOWER = new VoxelShapeGroup.Builder()
            .add(5, 0, 5, 11, 16, 11)
            .build();

    private static final VoxelShapeGroup UPPER = new VoxelShapeGroup.Builder()
            .add(5, 0, 5, 11, 3, 11)
            .addPart("off", 6, 3, 6, 10, 5, 10)
            .addPart("on", 6, 3, 6, 10, 3.5, 10)
            .build();

    private void initAABBs() {
        for (Direction facing : Direction.values()) {
            Vec3 normal = new Vec3(facing.getNormal());

            Mat4 matrix = Mat4.identity();
            matrix.translate(new Vec3(.5));

            if (facing == Direction.DOWN) {
                matrix.scale(normal.mul(2).add(1));
            }

            if (facing.getAxis() != Direction.Axis.Y) {
                matrix.rotateDeg(normal.cross(Direction.UP.getNormal()).to3f(), -90);
            }

            matrix.translate(new Vec3(-.5));

            for(DoubleBlockHalf half : DoubleBlockHalf.values()) {
                VoxelShapeGroup shape = (half == DoubleBlockHalf.UPPER) ? UPPER : LOWER;
                SHAPE.put(facing, half, shape.clone().transform(matrix));
            }
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader level, BlockPos pos, ISelectionContext context) {
        return SHAPE.get(state.getValue(FACING), state.getValue(HALF)).getVariant(state.getValue(PRESSED) ? "on" : "off");
    }

}
