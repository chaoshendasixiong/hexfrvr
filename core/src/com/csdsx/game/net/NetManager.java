package com.csdsx.game.net;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.NetJavaSocketImpl;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.csdsx.game.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.*;

/**
 * 从发送队列中取消息发送 一个定时线程专门发送心跳
 * 去读消息到消息队列
 * <p/>
 * <p/>
 * Created by Administrator on 2016-07-23.
 */
public class NetManager {
    public static ByteBuffer byteBuffer;
    public static LinkedBlockingQueue<BaseMsg> send_queue;
    public static LinkedBlockingQueue<BaseMsg> recv_queue;
    public static ScheduledExecutorService scheduledThreadPool;

    public static Socket socket;//最好使用Gdx.net 来操作 尽管底层也是NetJavaSocketImpl

    public static String ip = "192.168.0.117";

    public static int port = 9999;

    public static void init() {
        socket = Gdx.net.newClientSocket(Net.Protocol.TCP, ip, port, new SocketHints());

        send_queue = new LinkedBlockingQueue<BaseMsg>();
        recv_queue = new LinkedBlockingQueue<BaseMsg>();
        scheduledThreadPool = Executors.newScheduledThreadPool(3);
        heartWork();
        sendWork();
        readWork();
    }

    private static void readWork() {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                while (!socket.isConnected()) {

                }
                byteBuffer = new ByteBuffer(socket.getInputStream());

                while (true) {
                    try {
                        byte[] data = byteBuffer.getData();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        scheduledThreadPool.submit(runnable);
    }

    private static void sendWork() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {

                    try {
                        BaseMsg baseMsg = send_queue.take();
                        socket.getOutputStream().write(baseMsg.getBytes());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        scheduledThreadPool.submit(runnable);
    }

    public static void heartWork() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                sendHeart();
            }
        };
        scheduledThreadPool.scheduleAtFixedRate(runnable, 1, 5, TimeUnit.SECONDS);
    }

    public static void sendHeart() {
        String data = "HEART";
        send_queue.offer(new BaseMsg(data));
    }
/*
    public List<byte[]> getPacket(ByteBuffer buffer) throws Exception {
        pLink.clear();
        try {
            while (buffer.remaining() > 0) {
                if (packetLen == 0) {
//此时存在两种情况及在数据包包长没有获得的情况下可能已经获得过一次数据包
                    if (buffer.remaining() + _packet.length < 3) {
                        byte[] temp = new byte[buffer.remaining()];
                        buffer.get(temp);
                        _packet = PacketUtil.joinBytes(_packet, temp);
                        break;  //保存包头
                    } else {
                        if (_packet.length == 0) {
                            buffer.get();

                            packetLen = PacketUtil.parserBuffer2ToInt(buffer);
                        } else if (_packet.length == 1) {
                            packetLen = PacketUtil.parserBuffer2ToInt(buffer);
                        } else if (_packet.length == 2) {
                            byte[] lenByte = new byte[2];
                            lenByte[0] = _packet[1];
                            lenByte[1] = buffer.get();
                            packetLen = PacketUtil.parserBytes2ToInt(lenByte);
                        } else {
                            packetLen = PacketUtil.parserBytes2ToInt(_packet, 1);
                        }
                    }
                }
                if (_packet.length <= 3) {
//此时_packet 没有有用数据，所需数据都在缓冲区中
                    if (buffer.remaining() < packetLen) {
                        _packet = new byte[buffer.remaining()];
                        buffer.get(_packet);
                    } else {
                        byte[] p = new byte[packetLen];
                        buffer.get(p);
                        pLink.add(p);
                        packetLen = 0;
                        _packet = new byte[0];
                    }
                } else {
                    if (buffer.remaining() + _packet.length - 3 < packetLen) {
//剩余数据包不足一个完整包，保存后等待写一个
                        byte[] temp = new byte[buffer.remaining()];
                        buffer.get(temp);
                        _packet = PacketUtil.joinBytes(_packet, temp);
                        break;
                    } else {
//数据包完整或者多出
                        byte[] temp = new byte[packetLen - (_packet.length - 3)];
                        buffer.get(temp);
                        pLink.add(PacketUtil.subPacket(PacketUtil.joinBytes(_packet, temp)));
                        _packet = new byte[0];
                        packetLen = 0;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("..GETPACKET packetLen = " + packetLen + " _packet.length = " + _packet.length);
            throw e;
        }
        return pLink;
    }
*/
}
