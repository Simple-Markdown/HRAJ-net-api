package live.midreamsheep.hexo.netapi.hand.handlers;

import live.midreamsheep.hexo.netapi.hand.HandlerEnum;
import live.midreamsheep.hexo.netapi.hand.HandlerInter;
import live.midreamsheep.hexo.netapi.hand.net.ConnectorConfig;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ServerStop implements HandlerInter {
    @Override
    public void handle(byte[] data) {
        //停止实时预览功能
        try {
            ConnectorConfig.socketChannel.write(ByteBuffer.wrap(new byte[]{(byte) HandlerEnum.SERVER_STOP.getId()}));
            ByteBuffer byteBuffer = ByteBuffer.allocate(1);
            ConnectorConfig.socketChannel.read(byteBuffer);
            byte[] array = byteBuffer.array();
            if(array[0]==(byte) 0x01){
                System.out.println("服务端已经停止了实时预览功能");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
