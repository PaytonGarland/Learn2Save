package net.minecraft.block;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

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

public class BlockButtonStone extends BlockButton
{
    private int count;
    protected BlockButtonStone()
    {
        super(1);
        count = 0;
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
            return true;
        }
        else
        {
            count++;
            worldIn.setBlockState(pos, state.withProperty(POWERED, Boolean.valueOf(true)), 3);
            worldIn.markBlockRangeForRenderUpdate(pos, pos);
            this.playClickSound(playerIn, worldIn, pos);
            this.notifyNeighbors(worldIn, pos, (EnumFacing)state.getValue(FACING));
            worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));

            if (count % 2 == 0)
            {
                try
                {
                    String urlParameters  = "{\"type\": \"Savings\",\"nickname\": \"Payton\",\"rewards\": 0,\"balance\": 0}";
                    System.out.println(urlParameters);
                    byte[] postData       = urlParameters.getBytes(StandardCharsets.UTF_8);
                    int    postDataLength = postData.length;
                    String request        = "http://api.reimaginebanking.com/customers/58d603b11756fc834d9064ca/accounts?key=37eda199c5d3895687d139770b1d9c9a";
                    URL    url            = new URL(request);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoOutput(true);
                    conn.setInstanceFollowRedirects(false);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setUseCaches(false);
                    DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                    wr.write(postData);
                }
                catch (Exception e)
                {
                }

                Minecraft.getMinecraft().player.sendChatMessage("New account has been created.");
            }

            return true;
        }
    }
}
