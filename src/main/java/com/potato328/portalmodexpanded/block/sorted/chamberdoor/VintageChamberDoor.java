package com.potato328.portalmodexpanded.block.sorted.chamberdoor;

import net.minecraft.block.BlockState;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.IBlockReader;
import net.portalmod.common.sorted.door.ChamberDoorBlock;
import net.portalmod.core.math.Mat4;
import net.portalmod.core.math.Vec3;
import net.portalmod.core.math.VoxelShapeGroup;

public class VintageChamberDoor extends ChamberDoorBlock {

    private static final VoxelShapeGroup UPPERLEFT = new VoxelShapeGroup.Builder()
            .add(2, 0, 0, 4, 16, 4)
            .add(4, 14, 0, 16, 16, 4)
            .add(-6, 0, -1, 2, 16, 0)
            .add(4, 0, 4, 0, 16, 8)
            .add(2, 0, 8, 0, 16, 11)
            .addPart("closed", 4, 0, 1, 16, 14, 4)
            .addPart("open", 4, 0, 1, 6, 14, 4)
            .build();

    private static final VoxelShapeGroup LOWERLEFT = new VoxelShapeGroup.Builder()
            .add(2, 0, 0, 4, 16, 4)
            .add(-6, 0, -1, 2, 16, 0)
            .add(4, 0, 4, 0, 16, 8)
            .add(2, 0, 8, 0, 16, 11)
            .addPart("closed", 4, 0, 1, 16, 16, 4)
            .addPart("open", 4, 0, 1, 6, 16, 4)
            .build();
    private static final VoxelShapeGroup UPPERRIGHT = new VoxelShapeGroup.Builder()
            .add(12, 0, 0, 14, 16, 4)
            .add(0, 14, 3, 12, 16, 4)
            .add(14, 0, -1, 22, 16, 0)
            .add(12, 0, 4, 16, 16, 8)
            .add(14, 0, 8, 16, 16, 11)
            .addPart("closed", 0, 0, 1, 12, 14, 4)
            .addPart("open", 10, 0, 1, 12, 14, 4)
            .build();

    private static final VoxelShapeGroup LOWERRIGHT = new VoxelShapeGroup.Builder()
            .add(12, 0, 0, 14, 16, 4)
            .add(14, 0, -1, 22, 16, 0)
            .addPart("closed", 0, 0, 1, 12, 16, 4)
            .addPart("open", 10, 0, 1, 12, 16, 4)
            .build();

    public VintageChamberDoor(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader level, BlockPos pos, ISelectionContext context) {
        int facing = state.getValue(FACING).get2DDataValue() * 90;
        VoxelShapeGroup shapeGroup = state.getValue(HALF) == DoubleBlockHalf.UPPER ? UPPERLEFT : LOWERLEFT;
        VoxelShapeGroup shapeGroup2 = state.getValue(HALF) == DoubleBlockHalf.UPPER ? UPPERRIGHT : LOWERRIGHT;

        Mat4 matrix = Mat4.identity();
        matrix.translate(new Vec3(.5));
        matrix.rotateDeg(Vector3f.YN, facing + 180);
        matrix.translate(new Vec3(-.5));


                if(state.getValue(SIDE) == Side.LEFT)
                    return shapeGroup2.clone().transform(matrix).getVariant(state.getValue(OPEN) ? "open" : "closed");
                else
                    return shapeGroup.clone().transform(matrix).getVariant(state.getValue(OPEN) ? "open" : "closed");
    }
}
