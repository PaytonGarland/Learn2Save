package net.minecraft.server.network;

import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.INetHandlerHandshakeServer;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.server.SPacketDisconnect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.NetHandlerLoginServer;
import net.minecraft.server.network.NetHandlerStatusServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class NetHandlerHandshakeTCP implements INetHandlerHandshakeServer {
   private final MinecraftServer field_147387_a;
   private final NetworkManager field_147386_b;

   public NetHandlerHandshakeTCP(MinecraftServer p_i45295_1_, NetworkManager p_i45295_2_) {
      this.field_147387_a = p_i45295_1_;
      this.field_147386_b = p_i45295_2_;
   }

   public void func_147383_a(C00Handshake p_147383_1_) {
      switch(p_147383_1_.func_149594_c()) {
      case LOGIN:
         this.field_147386_b.func_150723_a(EnumConnectionState.LOGIN);
         if(p_147383_1_.func_149595_d() > 316) {
            TextComponentString textcomponentstring = new TextComponentString("Outdated server! I\'m still on 1.11.2");
            this.field_147386_b.func_179290_a(new SPacketDisconnect(textcomponentstring));
            this.field_147386_b.func_150718_a(textcomponentstring);
         } else if(p_147383_1_.func_149595_d() < 316) {
            TextComponentString textcomponentstring1 = new TextComponentString("Outdated client! Please use 1.11.2");
            this.field_147386_b.func_179290_a(new SPacketDisconnect(textcomponentstring1));
            this.field_147386_b.func_150718_a(textcomponentstring1);
         } else {
            this.field_147386_b.func_150719_a(new NetHandlerLoginServer(this.field_147387_a, this.field_147386_b));
         }
         break;
      case STATUS:
         this.field_147386_b.func_150723_a(EnumConnectionState.STATUS);
         this.field_147386_b.func_150719_a(new NetHandlerStatusServer(this.field_147387_a, this.field_147386_b));
         break;
      default:
         throw new UnsupportedOperationException("Invalid intention " + p_147383_1_.func_149594_c());
      }

   }

   public void func_147231_a(ITextComponent p_147231_1_) {
   }
}
