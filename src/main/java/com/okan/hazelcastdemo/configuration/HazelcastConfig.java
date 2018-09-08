package com.okan.hazelcastdemo.configuration;

import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.MapStore;
import com.okan.hazelcastdemo.domain.Fruit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {

    @Bean
    public HazelcastInstance getHazelcastInstance(MapStore<String, Fruit> fruitMapStore) {
        final Config config = new Config();

        final NetworkConfig networkConfig = new NetworkConfig();
        networkConfig.setPort(5701);
        networkConfig.setPortAutoIncrement(true);
        config.setNetworkConfig(networkConfig);

        final JoinConfig joinConfig = new JoinConfig();
        networkConfig.setJoin(joinConfig);

        final MulticastConfig multicastConfig = new MulticastConfig();
        multicastConfig.setEnabled(false);
        joinConfig.setMulticastConfig(multicastConfig);

        final TcpIpConfig tcpIpConfig = new TcpIpConfig();
        tcpIpConfig.setEnabled(false);
        joinConfig.setTcpIpConfig(tcpIpConfig);

        final AwsConfig awsConfig = new AwsConfig();
        awsConfig.setEnabled(false);
        joinConfig.setAwsConfig(awsConfig);

        final SSLConfig sslConfig = new SSLConfig();
        sslConfig.setEnabled(false);
        networkConfig.setSSLConfig(sslConfig);

        final MapConfig mapConfig = new MapConfig();
        mapConfig.setName("fruitMap");

        final MapStoreConfig mapStoreConfig = new MapStoreConfig();
        mapStoreConfig.setEnabled(true);
        mapStoreConfig.setWriteDelaySeconds(60);
        mapStoreConfig.setInitialLoadMode(MapStoreConfig.InitialLoadMode.LAZY);
        mapStoreConfig.setImplementation(fruitMapStore);
        mapConfig.setMapStoreConfig(mapStoreConfig);
        config.addMapConfig(mapConfig);


        return Hazelcast.newHazelcastInstance(config);
    }
}
