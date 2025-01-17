package net.minecraft.block;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.mcbank.nessie.Customer;
import com.mcbank.nessie.SavingsBalance;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BlockPressurePlateWeighted extends BlockBasePressurePlate
{
    public static final PropertyInteger POWER = PropertyInteger.create("power", 0, 15);
    private final int maxWeight;

    protected BlockPressurePlateWeighted(Material materialIn, int p_i46379_2_)
    {
        this(materialIn, p_i46379_2_, materialIn.getMaterialMapColor());
    }

    protected BlockPressurePlateWeighted(Material materialIn, int p_i46380_2_, MapColor color)
    {
        super(materialIn, color);
        this.setDefaultState(this.blockState.getBaseState().withProperty(POWER, Integer.valueOf(0)));
        this.maxWeight = p_i46380_2_;
    }

    protected int computeRedstoneStrength(World worldIn, BlockPos pos)
    {
        int i = Math.min(worldIn.getEntitiesWithinAABB(Entity.class, PRESSURE_AABB.offset(pos)).size(), this.maxWeight);

        if (i > 0)
        {
            float f = (float)Math.min(this.maxWeight, i) / (float)this.maxWeight;
            return MathHelper.ceil(f * 15.0F);
        }
        else
        {
            return 0;
        }
    }

    protected void playClickOnSound(World worldIn, BlockPos color)
    {
        worldIn.playSound((EntityPlayer)null, color, SoundEvents.BLOCK_METAL_PRESSPLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.90000004F);
    }

    protected void playClickOffSound(World worldIn, BlockPos pos)
    {
        worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_METAL_PRESSPLATE_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.75F);
    }

    protected int getRedstoneStrength(IBlockState state)
    {
        return ((Integer)state.getValue(POWER)).intValue();
    }

    protected IBlockState setRedstoneStrength(IBlockState state, int strength)
    {
        return state.withProperty(POWER, Integer.valueOf(strength));
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World worldIn)
    {
        return 10;
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(POWER, Integer.valueOf(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(POWER)).intValue();
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {POWER});
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
            int goalToRemove = 0;
            SavingsBalance current = BlockButtonWood.getBalance();
            
            if (current.getBalance() >= 10000)
            {
            	Minecraft.getMinecraft().player.sendChatMessage("Congratulations! You turned in 10,000 coins for 1000 diamonds.");
            	goalToRemove = 10000;
            } else if (current.getBalance() >= 1000)
            {
            	Minecraft.getMinecraft().player.sendChatMessage("Congratulations! You turned in 1,000 coins for 50 diamonds.");
            	goalToRemove = 1000;
            } else if (current.getBalance() >= 500)
            {
            	Minecraft.getMinecraft().player.sendChatMessage("Congratulations! You turned in 500 coins for 10 diamonds.");
            	goalToRemove = 500;
            } else if (current.getBalance() >= 100)
            {
            	Minecraft.getMinecraft().player.sendChatMessage("Congratulations! You turned in 100 coins for 1 diamond.");
            	goalToRemove = 100;
            } else
            {
            	Minecraft.getMinecraft().player.sendChatMessage("Sorry, not enough coins!");
            	goalToRemove = 0;
            }
           
            if (goalToRemove != 0)
            {
    	        try
    	        {
    				String urlParameters = "{\"medium\": \"balance\",\"transaction_date\": \"2017-03-26\",\"amount\": " + goalToRemove + ",\"description\": \"Remove for goal\"}";
    	            System.out.println(urlParameters);
    	            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
    	            int postDataLength = postData.length;
    	            String request = "http://api.reimaginebanking.com/accounts/58d7bf181756fc834d909c86/withdrawals?key=37eda199c5d3895687d139770b1d9c9a";
    	            URL url = new URL(request);
    	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    	            conn.setDoOutput(true);
    	            conn.setInstanceFollowRedirects(false);
    	            conn.setRequestMethod("POST");
    	            conn.setRequestProperty("Content-Type", "application/json");
    	            conn.setRequestProperty("Accept", "application/json");
    	            conn.setUseCaches(false);
    	            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
    	            wr.write(postData);
    	
    	
    	            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    	    		
    	    		String json = "";
    	    		while ((json = rd.readLine()) != null)
    	                System.out.println(json);
    	            rd.close();
    	        }
    	        catch (Exception e)
    	        {
    	        }
            }
        }
        else if (flag1 && !flag)
        {
            this.playClickOnSound(worldIn, pos);
        }

//		Gson gson = new Gson();
//		SavingsBalance current = gson.fromJson(json, SavingsBalance.class);
//		Minecraft.getMinecraft().player.sendChatMessage("Your current balance is: " + current.getBalance() + " coins.");
//		int balance = Integer.parseInt(current.getBalance());
//		if (balance >= 10000)
//		{
//			Minecraft.getMinecraft().player.sendChatMessage("Congratulations! You have redeemed 10,000 coins for 500 diamonds.");
//		} else if (balance >= 1000) {
//			Minecraft.getMinecraft().player.sendChatMessage("Congratulations! You have redeemed 1,000 coins for 100 diamonds.");
//		} else if (balance >= 500) {
//			Minecraft.getMinecraft().player.sendChatMessage("Congratulations! You have redeemed 500 coins for 10 diamonds.");
//		} else if (balance >= 100) {
//			Minecraft.getMinecraft().player.sendChatMessage("Congratulations! You have redeemed 100 coins for 1 diamond.");
//		} else {
//			Minecraft.getMinecraft().player.sendChatMessage("Sorry, not enough coins to redeem your goal.");
//		}

        if (flag1)
        {
            worldIn.scheduleUpdate(new BlockPos(pos), this, this.tickRate(worldIn));
        }
    }
}
