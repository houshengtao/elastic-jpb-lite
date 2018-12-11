package io.elasticjob.lite.console;

import io.elasticjob.lite.api.JobScheduler;
import io.elasticjob.lite.config.JobCoreConfiguration;
import io.elasticjob.lite.config.JobRootConfiguration;
import io.elasticjob.lite.config.LiteJobConfiguration;
import io.elasticjob.lite.config.simple.SimpleJobConfiguration;
import io.elasticjob.lite.reg.base.CoordinatorRegistryCenter;
import io.elasticjob.lite.reg.zookeeper.ZookeeperConfiguration;
import io.elasticjob.lite.reg.zookeeper.ZookeeperRegistryCenter;

public class TestJob {
    public static void main(String[] args) {
        new JobScheduler(createRegistryCenter(), createJobConfiguration("A")).init();
    }
    private static CoordinatorRegistryCenter createRegistryCenter() {
        //192.168.112.128:2181,192.168.112.128:2182 这个为zk的地址
        //demo-job 这个为1个zk环境的下的1个namespace 可以有多个 1个namespace下有多个job
        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(
                new ZookeeperConfiguration("localhost:2181", "demo-job"));
        regCenter.init();
        return regCenter;
    }
    private static LiteJobConfiguration createJobConfiguration(String jobParameter) {
        // mySimpleTest 为jobname 0/10 * * * * ?为cron表达式  2 分片数量  0=北京,1=上海 分片对应内容  jobParameter 自定义参数
        JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration.newBuilder("simpleJobDemo", "0/10 * * * * ?", 2).shardingItemParameters("0=北京,1=上海").jobParameter(jobParameter).build();
        SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(simpleCoreConfig, SimpleJobDemo.class.getCanonicalName());
        JobRootConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).build();

        return  null;
    }
}
