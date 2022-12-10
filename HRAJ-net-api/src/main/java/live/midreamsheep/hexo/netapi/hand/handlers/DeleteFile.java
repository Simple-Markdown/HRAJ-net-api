package live.midreamsheep.hexo.netapi.hand.handlers;

import live.midreamsheep.hexo.netapi.hand.HandlerEnum;
import live.midreamsheep.hexo.netapi.hand.HandlerInter;
import live.midreamsheep.hexo.netapi.hand.net.ConnectorConfig;
import live.midreamsheep.hexo.netapi.hand.net.NetToolApi;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DeleteFile implements HandlerInter {
    @Override
    public void handle(byte[] data) {
        try {
            NetToolApi.sendMeta(HandlerEnum.DELETE_FILE.getId(),data.length);
            ConnectorConfig.socketChannel.write(ByteBuffer.wrap(data));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
