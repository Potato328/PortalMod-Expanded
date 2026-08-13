package com.potato328.portalmodexpanded.block;

import com.potato328.portalmodexpanded.tileentity.ModTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.portalmod.common.sorted.autoportal.AutoPortalBlock;
import net.portalmod.common.sorted.button.QuadBlockCorner;
import net.portalmod.common.sorted.portal.OrthonormalBasis;
import net.portalmod.core.math.BiHashMap;
import net.portalmod.core.math.Mat4;
import net.portalmod.core.math.Vec3;
import net.portalmod.core.math.VoxelShapeGroup;

import javax.annotation.Nullable;

public class P1AutoPortalBlock extends AutoPortalBlock {
    public P1AutoPortalBlock(Properties properties) {
        super(properties);
        this.initAABBs();
    }

    private static final BiHashMap<String, QuadBlockCorner, VoxelShapeGroup> SHAPES = new BiHashMap<>();

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntities.P1_AUTOPORTAL.get().create();
    }
    private void initAABBs() {
        VoxelShapeGroup leftShape = new VoxelShapeGroup.Builder()
                .add(5, 0, 0, 7, 16, 2.01)
                .build();
        VoxelShapeGroup rightShape = new VoxelShapeGroup.Builder()
                .add(9, 0, 0, 11, 16, 2.01)
                .build();

        for(Direction direction : Direction.values()) {
            if(direction.getAxis() == Direction.Axis.Y)
                continue;

            for (Direction facing : Direction.values()) {
                for (QuadBlockCorner corner : QuadBlockCorner.values()) {
                    Tuple<Direction, Direction> directions = placementDirectionsFromFacingAndDirection(facing, direction);
                    Direction a = directions.getA();
                    Direction b = directions.getB();
                    int x = QuadBlockCorner.DOWN_RIGHT.getX() - QuadBlockCorner.DOWN_LEFT.getX();
                    int y = QuadBlockCorner.UP_LEFT.getY() - QuadBlockCorner.DOWN_LEFT.getY();

                    if (facing.getAxisDirection() == Direction.AxisDirection.NEGATIVE)
                        x *= -1;
                    if (x < 0)
                        a = a.getOpposite();
                    if (y < 0)
                        b = b.getOpposite();

                    Vec3 up = new Vec3(b);
                    Vec3 right = new Vec3(a);
                    Mat4 matrix = new OrthonormalBasis(right, up).getChangeOfBasisFromCanonicalMatrix();

                    Mat4 am = Mat4.identity()
                            .translate(.5, .5, .5)
                            .mul(matrix)
                            .translate(-.5, -.5, -.5);

                    SHAPES.put(facing + " " + direction, corner, (corner.isLeft() ? leftShape : rightShape).clone().transform(am));
                }
            }
        }
    }

    private VoxelShapeGroup getShapeGroup(BlockState state) {
        return SHAPES.get(state.getValue(FACING) + " " + state.getValue(DIRECTION), state.getValue(CORNER));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader level, BlockPos pos, ISelectionContext context) {
        VoxelShape shape = this.getShapeGroup(state).getShape();
        return shape != null ? shape : VoxelShapes.empty();
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader level, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, level, pos, context);
    }
}
