package dev.katcodes.weirdhappenings.utils;

import dev.katcodes.weirdhappenings.HappeningsMod;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class BlockUtils {
    public static BlockPos FindBlock(Player player, float radius, Class<? extends Block> block,boolean debug) {
        Level lvl=player.level;
        Vec3 rot=player.getLookAngle();
        rot=rot.multiply(-1,-1,-1);
        double x=rot.x;
        double y=rot.y;
        double z=rot.z;
        if(x>0.5)
            x=1;
        else if(x<-0.5)
            x=-1;
        else
            x=0;
        if(z>0.5)
            z=1;
        else if(z<-0.5)
            z=-1;
        else
            z=0;
        rot=new Vec3(x,y,z);
        BlockPos playerPos=player.getOnPos();
        double x1=x==0?radius: ( x>0?0:radius);
        double x2=x==0?radius: ( x<0?0:radius);
        double z1=z==0?radius: ( z>0?0:radius);
        double z2=z==0?radius: ( z<0?0:radius);
        for(x=(playerPos.getX() - x1);x<= playerPos.getX() + x2; x++){
            for(y=(playerPos.getY() - radius);y< playerPos.getY() + (radius); y++){
                for(z=(playerPos.getZ() - z1);z<= playerPos.getZ() + z2; z++){
                    BlockPos posToTest=new BlockPos(Math.ceil(x),Math.ceil(y),Math.ceil(z));
                    if(debug)
                        HappeningsMod.LOGGER.info("Checking block at "+posToTest+" Is a "+ lvl.getBlockState(posToTest).getBlock().getClass().getName()+" equals "+block.isInstance(lvl.getBlockState(posToTest).getBlock()));
                    if(block.isInstance(lvl.getBlockState(posToTest).getBlock()))
                        return posToTest;
                }
            }
        }

        return null;
    }
}
