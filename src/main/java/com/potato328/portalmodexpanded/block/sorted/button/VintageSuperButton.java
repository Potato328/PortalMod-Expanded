package com.potato328.portalmodexpanded.block.sorted.button;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.IBlockReader;
import net.portalmod.common.sorted.button.ButtonMode;
import net.portalmod.common.sorted.button.QuadBlockCorner;
import net.portalmod.common.sorted.button.SuperButtonBlock;
import net.portalmod.core.math.BiHashMap;
import net.portalmod.core.math.Mat4;
import net.portalmod.core.math.Vec3;
import net.minecraft.util.Direction.Axis;
import net.portalmod.core.math.VoxelShapeGroup;

public class VintageSuperButton extends SuperButtonBlock {

    private static final BiHashMap<Direction, QuadBlockCorner, VoxelShapeGroup> SHAPES = new BiHashMap<>();

    public VintageSuperButton(Properties properties) {
        super(properties);
        this.registerDefaultState(stateDefinition.any()
                .setValue(FACING, Direction.UP)
                .setValue(CORNER, QuadBlockCorner.UP_LEFT)
                .setValue(PRESSED, false)
                .setValue(ACTIVE, false)
                .setValue(MODE, ButtonMode.NORMAL)
        );
        this.initAABBs();
    }

    private void initAABBs() {
        VoxelShapeGroup shape = new VoxelShapeGroup.Builder()
                .add(0, 0, 0, 12, 3, 12)
                .addPart("normal",  0, 3, 0, 9.5f, 5, 9.5f)
                .addPart("pressed", 0, 3, 0, 9.5f, 4, 9.5f)
                .addPart("trigger", 0, 4, 0, 9.5f, 6, 9.5f)
                .build();

        for(Direction facing : Direction.values()) {
            for(QuadBlockCorner corner : QuadBlockCorner.values()) {
                Mat4 matrix = Mat4.identity();
                matrix.translate(new Vec3(.5));

                if(facing.getAxisDirection() == Direction.AxisDirection.NEGATIVE) {
                    matrix.scale(new Vec3(facing.getNormal()).mul(2).add(1));
                    matrix.rotateDeg(new Vec3(facing.getNormal()).to3f(), corner.getRot() - 90);
                } else {
                    matrix.rotateDeg(new Vec3(facing.getNormal()).to3f(), corner.getRot());
                }

                if(facing.getAxis() == Axis.X)
                    matrix.rotateDeg(Vector3f.ZP, -90)
                            .rotateDeg(Vector3f.YP, 90);

                if(facing.getAxis() == Axis.Z)
                    matrix.rotateDeg(Vector3f.XP, 90);

                matrix.rotateDeg(Vector3f.YP, 90);
                matrix.translate(new Vec3(-.5));

                SHAPES.put(facing, corner, shape.clone().transform(matrix));
            }
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader blockReader, BlockPos pos, ISelectionContext selectionContext) {
        VoxelShape shape = this.getShapeGroup(state).getVariant(state.getValue(PRESSED) ? "pressed" : "normal");
        return shape != null ? shape : VoxelShapes.empty();
    }

    private VoxelShapeGroup getShapeGroup(BlockState state) {
        return SHAPES.get(state.getValue(FACING), state.getValue(CORNER));
    }
}
