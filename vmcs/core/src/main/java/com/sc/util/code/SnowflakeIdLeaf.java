package com.sc.util.code;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Bodil
 * @version 2018/2/1
 * 雪花算法 Id Leaf Service 实现
 * id format
 * timestamp |machineId |sequence
 * 41        |10        |12
 */
public class SnowflakeIdLeaf {
    private static final long SEQUENCE_BITS = 12L;//序列码长度
    private static final long MACHINE_ID_BITS = 10L;//机器id长度
    private static final long MACHINE_ID_SHIFT = SEQUENCE_BITS;//机器id左位移数
    private static final long TIMESTAMP_LEFT_SHIFT = MACHINE_ID_BITS + SEQUENCE_BITS;//时间戳左位移数
    private static final long TWEPOCH = 1517414400000L;//2018-02-01 00:00:00时间戳
    private static final long MAX_MACHINE_ID = ~(-1L << MACHINE_ID_BITS);//机器id最大值1023
    private static final long MAX_SEQUENCE = 4095;//2**12 单位毫秒最大序列值

    private volatile long lastTimestamp = -1L;
    private volatile AtomicLong sequence = new AtomicLong(0);

    private long machineId;

    public SnowflakeIdLeaf() {
        machineId = getMachineId();
        if (machineId > MAX_MACHINE_ID || machineId < 0) {
            throw new RuntimeException("machineId > MaxMachineId");
        }
    }


    private long getMachineId() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            long id;
            if (network == null) {
                id = 1;
            } else {
                byte[] mac = network.getHardwareAddress();
                id = ((0x000000FF & (long) mac[mac.length - 1]) |
                        (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
            }
            return id;
        } catch (SocketException | UnknownHostException e) {
            throw new RuntimeException("获取机器id失败", e);
        }
    }

    private long nextId() {
        long timestamp = System.currentTimeMillis();
        if (timestamp < lastTimestamp) {
            throw new IllegalStateException("Clock moved backwards.");
        }

        if (lastTimestamp == timestamp) {
            sequence.set((sequence.get() + 1) & MAX_SEQUENCE);
            if (sequence.get() == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence.set(0L);
        }

        lastTimestamp = timestamp;
        return ((timestamp - TWEPOCH) << TIMESTAMP_LEFT_SHIFT) | (machineId << MACHINE_ID_SHIFT) | sequence.get();
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    public long getId() {
        return nextId();
    }

    public static void main(String[] args) {
        System.out.println(new SnowflakeIdLeaf().getId());
    }
}
