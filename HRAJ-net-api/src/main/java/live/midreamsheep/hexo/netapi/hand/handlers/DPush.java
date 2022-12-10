package live.midreamsheep.hexo.netapi.hand.handlers;

import live.midreamsheep.hexo.netapi.hand.HandlerEnum;
import live.midreamsheep.hexo.netapi.hand.HandlerInter;
import live.midreamsheep.hexo.netapi.hand.HandlerMapper;
import live.midreamsheep.hexo.netapi.hand.net.NetToolApi;

import java.io.IOException;

public class DPush implements HandlerInter {
    @Override
    public void handle(byte[] data) {
        //对比本地文件数据
        HandlerMapper.handlerMap.get(HandlerEnum.PUSH.getId()).handle(data);
        try {
            NetToolApi.sendMeta(HandlerEnum.DPUSH.getId(),data.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
