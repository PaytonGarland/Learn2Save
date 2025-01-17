package net.minecraft.block;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.Nullable;

import com.google.gson.Gson;
import com.mcbank.nessie.Customer;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockButtonCreateSavings extends BlockButton
{
    protected BlockButtonCreateSavings()
    {
        super(3);
    }

    protected void playClickSound(@Nullable EntityPlayer player, World worldIn, BlockPos pos)
    {
        worldIn.playSound(player, pos, SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.6F);
    }

    protected void playReleaseSound(World worldIn, BlockPos pos)
    {
        worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.5F);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing heldItem, float side, float hitX, float hitY)
    {
        if (((Boolean)state.getValue(POWERED)).booleanValue())
        {
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
            Minecraft.getMinecraft().player.sendChatMessage(top.getFirstName() + " " + top.getLastName());
            return true;
        }
        else
        {
            worldIn.setBlockState(pos, state.withProperty(POWERED, Boolean.valueOf(true)), 3);
            worldIn.markBlockRangeForRenderUpdate(pos, pos);
            this.playClickSound(playerIn, worldIn, pos);
            this.notifyNeighbors(worldIn, pos, (EnumFacing)state.getValue(FACING));
            worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
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
            Minecraft.getMinecraft().player.sendChatMessage(top.getFirstName() + " " + top.getLastName());
            return true;
        }
    }
}
