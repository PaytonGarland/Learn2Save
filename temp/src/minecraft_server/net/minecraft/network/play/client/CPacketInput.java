package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class CPacketInput implements Packet<INetHandlerPlayServer> {
   private float field_149624_a;
   private float field_149622_b;
   private boolean field_149623_c;
   private boolean field_149621_d;

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      this.field_149624_a = p_148837_1_.readFloat();
      this.field_149622_b = p_148837_1_.readFloat();
      byte b0 = p_148837_1_.readByte();
      this.field_149623_c = (b0 & 1) > 0;
      this.field_149621_d = (b0 & 2) > 0;
   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.writeFloat(this.field_149624_a);
      p_148840_1_.writeFloat(this.field_149622_b);
      byte b0 = 0;
      if(this.field_149623_c) {
         b0 = (byte)(b0 | 1);
      }

      if(this.field_149621_d) {
         b0 = (byte)(b0 | 2);
      }

      p_148840_1_.writeByte(b0);
   }

   public void func_148833_a(INetHandlerPlayServer p_148833_1_) {
      p_148833_1_.func_147358_a(this);
   }

   public float func_149620_c() {
      return this.field_149624_a;
   }

   public float func_149616_d() {
      return this.field_149622_b;
   }

   public boolean func_149618_e() {
      return this.field_149623_c;
   }

   public boolean func_149617_f() {
      return this.field_149621_d;
   }
}
