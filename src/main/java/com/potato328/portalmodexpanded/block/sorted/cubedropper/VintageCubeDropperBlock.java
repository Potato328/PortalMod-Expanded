package com.potato328.portalmodexpanded.block.sorted.cubedropper;

import com.potato328.portalmodexpanded.tileentity.ModTileEntities;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.IBlockReader;
import net.portalmod.common.sorted.button.QuadBlockCorner;
import net.portalmod.common.sorted.cubedropper.CubeDropperBlock;
import net.portalmod.core.math.BiHashMap;
import net.portalmod.core.math.Mat4;
import net.portalmod.core.math.Vec3;
import net.portalmod.core.math.VoxelShapeGroup;

import javax.annotation.Nullable;

public class VintageCubeDropperBlock extends CubeDropperBlock {

    private static final BiHashMap<DoubleBlockHalf, QuadBlockCorner, VoxelShapeGroup> SHAPE = new BiHashMap<>();

    public VintageCubeDropperBlock(Properties properties) {
        super(properties);
        this.initAABBs();
    }
    private void initAABBs() {
        VoxelShapeGroup upper = new VoxelShapeGroup.Builder()
                .add(0, 0, 4, 4, 16, 16)
                .add(0, 0, 0, 16, 16, 4)
                .build();
        VoxelShapeGroup lower = new VoxelShapeGroup.Builder()
                .add(0, 0, 4, 4, 16, 16)
                .add(0, 0, 0, 16, 16, 4)
                .addPart("closed", 4, 0, 4, 16, 2, 16)
                .build();

        for(QuadBlockCorner corner : QuadBlockCorner.values()) {
            for(DoubleBlockHalf half : DoubleBlockHalf.values()) {
                Mat4 matrix = Mat4.identity();
                matrix.translate(new Vec3(.5));
                matrix.rotateDeg(new Vector3f(0, 1, 0), corner.getRot() - 90);
                matrix.translate(new Vec3(-.5));
                VoxelShapeGroup group = half == DoubleBlockHalf.LOWER ? lower : upper;

                SHAPE.put(half, corner, group.clone().transform(matrix));
            }
        }
    }

    @Override
    public VoxelShape getShape(BlockState blockState, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return SHAPE.get(blockState.getValue(HALF), blockState.getValue(CORNER)).getVariant(blockState.getValue(OPEN) ? "" : "closed");
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return this.isMainBlock(state) ? ModTileEntities.VINTAGE_CUBE_DROPPER.get().create() : null;
    }
    @Override
    public boolean hasTileEntity(BlockState state) {
        return this.isMainBlock(state);
    }
    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.MODEL;
    }


}
