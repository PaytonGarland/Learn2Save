package net.minecraft.block;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;
import com.mcbank.nessie.Customer;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPressurePlate extends BlockBasePressurePlate
{
    public static final PropertyBool POWERED = PropertyBool.create("powered");
    private final BlockPressurePlate.Sensitivity sensitivity;

    protected BlockPressurePlate(Material materialIn, BlockPressurePlate.Sensitivity sensitivityIn)
    {
        super(materialIn);
        this.setDefaultState(this.blockState.getBaseState().withProperty(POWERED, Boolean.valueOf(false)));
        this.sensitivity = sensitivityIn;
    }

    protected int getRedstoneStrength(IBlockState state)
    {
        return ((Boolean)state.getValue(POWERED)).booleanValue() ? 15 : 0;
    }

    protected IBlockState setRedstoneStrength(IBlockState state, int strength)
    {
        return state.withProperty(POWERED, Boolean.valueOf(strength > 0));
    }

    protected void playClickOnSound(World worldIn, BlockPos color)
    {
        if (this.blockMaterial == Material.WOOD)
        {
            worldIn.playSound((EntityPlayer)null, color, SoundEvents.BLOCK_WOOD_PRESSPLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.8F);
        }
        else
        {
            worldIn.playSound((EntityPlayer)null, color, SoundEvents.BLOCK_STONE_PRESSPLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.6F);
        }
    }

    protected void playClickOffSound(World worldIn, BlockPos pos)
    {
        if (this.blockMaterial == Material.WOOD)
        {
            worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_WOOD_PRESSPLATE_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.7F);
        }
        else
        {
            worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_STONE_PRESSPLATE_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.5F);
        }
    }

    protected int computeRedstoneStrength(World worldIn, BlockPos pos)
    {
        AxisAlignedBB axisalignedbb = PRESSURE_AABB.offset(pos);
        List <? extends Entity > list;

        switch (this.sensitivity)
        {
            case EVERYTHING:
                list = worldIn.getEntitiesWithinAABBExcludingEntity((Entity)null, axisalignedbb);
                break;

            case MOBS:
                list = worldIn.<Entity>getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);
                break;

            default:
                return 0;
        }

        if (!list.isEmpty())
        {
            for (Entity entity : list)
            {
                if (!entity.doesEntityNotTriggerPressurePlate())
                {
                    return 15;
                }
            }
        }

        return 0;
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(POWERED, Boolean.valueOf(meta == 1));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((Boolean)state.getValue(POWERED)).booleanValue() ? 1 : 0;
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {POWERED});
    }

    public static enum Sensitivity
    {
        EVERYTHING,
        MOBS;
    }

    /**
     * Updates the pressure plate when stepped on
     */
    @Override
    protected void updateState(World worldIn, BlockPos pos, IBlockState state, int oldRedstoneStrength)
    {
        int i = this.computeRedstoneStrength(worldIn, pos);
        boolean flag = oldRedstoneStrength > 0;
        boolean flag1 = i > 0;

        if (oldRedstoneStrength != i)
        {
            state = this.setRedstoneStrength(state, i);
            worldIn.setBlockState(pos, state, 2);
            this.updateNeighbors(worldIn, pos);
            worldIn.markBlockRangeForRenderUpdate(pos, pos);
        }

        if (!flag1 && flag)
        {
            this.playClickOffSound(worldIn, pos);
        }
        else if (flag1 && !flag)
        {
            this.playClickOnSound(worldIn, pos);
            String json = "";
            URL customerInfo;

            try
            {
                customerInfo = new URL("http://api.reimaginebanking.com/customers/58d603b11756fc834d9064ca?key=37eda199c5d3895687d139770b1d9c9a");
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(customerInfo.openStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null)
                {
                    json = json + inputLine;
                }

                in.close();
            }
            catch (MalformedURLException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Customer customer = new Customer();
            Gson gson = new Gson();
            Customer top = gson.fromJson(json, Customer.class);
            Minecraft.getMinecraft().player.sendChatMessage("WELCOME to the VILLAGE BANK");
            Minecraft.getMinecraft().player.sendChatMessage(top.getFirstName() + " " + top.getLastName() + " - " + top.getId());
            Minecraft.getMinecraft().player.sendChatMessage(top.getAddress().getStreetNumber() + " "
                    + top.getAddress().getStreetName());
            Minecraft.getMinecraft().player.sendChatMessage(top.getAddress().getCity() + ", " + top.getAddress().getState());
            Minecraft.getMinecraft().player.sendChatMessage(top.getAddress().getZip());
        }

        if (flag1)
        {
            worldIn.scheduleUpdate(new BlockPos(pos), this, this.tickRate(worldIn));
        }
    }
}
