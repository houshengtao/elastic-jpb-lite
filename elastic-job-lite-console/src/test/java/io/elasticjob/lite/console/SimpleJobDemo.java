package io.elasticjob.lite.console;

import io.elasticjob.lite.api.ShardingContext;
import io.elasticjob.lite.api.simple.SimpleJob;

import java.util.Date;

public class SimpleJobDemo implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println(new Date()+" job名称 = "+shardingContext.getJobName()
                +"分片数量"+shardingContext.getShardingTotalCount()
                +"当前分区"+shardingContext.getShardingItem()
                +"当前分区名称"+shardingContext.getShardingParameter()
                +"当前自定义参数"+shardingContext.getJobParameter()+"============start=================");
    }
}
