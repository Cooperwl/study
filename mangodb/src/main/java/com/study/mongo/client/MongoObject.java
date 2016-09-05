package com.study.mongo.client;

import com.mongodb.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangliang on 2016/9/4.
 */
public class MongoObject {
    private List<String> hostPorts;
    private int port = 27017;
    private String host = "127.0.0.1";
    private int connectionsPerHost = 5;// 每个主机的连接数
    // 线程队列数，它以上面connectionsPerHost值相乘的结果就是线程队列最大值。如果连接线程排满了队列就会抛出“Out to get db”错误。
    private int threadsAllowedToBlockForConnectionMultiplier = 30;
    private long maxWaitTime = 5000;// 最大等待连接的线程阻塞时间
    private long connectTimeout = 5000;// 连接超时的毫秒。0是默认和无限
    private long socketTimeout = 5000;// socket超时。0是默认和无限
    private boolean autoConnectRetry = false;// 这个控制是否在一个连接时，系统会自动

    public void setHostPorts(List<String> hostPorts) {
        this.hostPorts = hostPorts;
    }

    public MongoObject() {
        // TODO Auto-generated constructor stub
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getConnectionsPerHost() {
        return connectionsPerHost;
    }

    public void setConnectionsPerHost(int connectionsPerHost) {
        this.connectionsPerHost = connectionsPerHost;
    }

    public int getThreadsAllowedToBlockForConnectionMultiplier() {
        return threadsAllowedToBlockForConnectionMultiplier;
    }

    public void setThreadsAllowedToBlockForConnectionMultiplier(
            int threadsAllowedToBlockForConnectionMultiplier) {
        this.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
    }

    public long getMaxWaitTime() {
        return maxWaitTime;
    }

    public void setMaxWaitTime(long maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }

    public long getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(long connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public long getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(long socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public boolean isAutoConnectRetry() {
        return autoConnectRetry;
    }

    public void setAutoConnectRetry(boolean autoConnectRetry) {
        this.autoConnectRetry = autoConnectRetry;
    }

    public MongoClient run() {
        if (null != hostPorts) {
            if (null != host && port > 0) {
                hostPorts.add(host + ":" + port);
            }
        } else {
            hostPorts = new ArrayList<String>();
            if (null != host && port > 0) {
                hostPorts.add(host + ":" + port);
            } else {
                return null;
            }
        }
        List<ServerAddress> addresses = new ArrayList<ServerAddress>();
        for (String hostPort : hostPorts) {
            String[] spits = hostPort.split(":");
            ServerAddress address = new ServerAddress(spits[0],
                    Integer.valueOf(spits[1]));
            addresses.add(address);
        }

        MongoClient client = new MongoClient(addresses, getConfOptions());
        return client;
    }

    @SuppressWarnings("deprecation")
    private MongoClientOptions getConfOptions() {
        return new MongoClientOptions.Builder()
                .socketKeepAlive(true)
                // 是否保持长链接
                .connectTimeout((int) this.connectTimeout)
                // 链接超时时间
                .socketTimeout((int) this.socketTimeout)
                // read数据超时时间
                .readPreference(ReadPreference.primary())
                // 最近优先策略
                .connectionsPerHost(this.connectionsPerHost)
                // 每个地址最大请求数
                .maxWaitTime((int) this.maxWaitTime)
                // 长链接的最大等待时间
                .threadsAllowedToBlockForConnectionMultiplier(
                        this.threadsAllowedToBlockForConnectionMultiplier) // 一个socket最大的等待请求数
                .writeConcern(WriteConcern.NORMAL).build();
    }
}