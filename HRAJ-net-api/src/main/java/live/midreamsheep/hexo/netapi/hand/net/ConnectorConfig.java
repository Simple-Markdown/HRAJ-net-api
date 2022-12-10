package live.midreamsheep.hexo.netapi.hand.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ConnectorConfig {
    public static SocketChannel socketChannel;
    public static void init() throws IOException {
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress(toIp, toPort));
            //传输密码
            byte[] bytes = new byte[2];
            byte[] passwords = password.getBytes();
            //密码长度
            bytes[0] = (byte) (passwords.length >> 8);
            bytes[1] = (byte) (passwords.length);
            socketChannel.write(ByteBuffer.wrap(bytes));
            socketChannel.write(ByteBuffer.wrap(passwords));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String nativeHexoPath;
    //服务器地址
    public static String toIp;
    //服务器端口
    public static int toPort;
    //服务器通行密码
    public static String password;

    public static volatile boolean isPulling = false;

    public static void setConfig(String config){
        for (String s : config.split("\n")) {
            if(s.trim().startsWith("#")){
                continue;
            }
            String[] split = s.split("=");
            switch (split[0]){
                case "nativeHexoPath":
                    nativeHexoPath = split[1].trim();
                    break;
                case "toIp":
                    toIp = split[1].trim();
                    break;
                case "toPort":
                    toPort = Integer.parseInt(split[1].trim());
                    break;
                case "password":
                    password = split[1].trim();
                    break;
                default:
                    System.err.println("无效的配置项"+split[0]);
                    break;
            }
        }
    }
}
