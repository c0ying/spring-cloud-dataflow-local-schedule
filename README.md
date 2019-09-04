# Spring Cloud DataFlow Quartz定时执行

官方版本定时任务执行只支持 Kubernetes ，使用cronJob实现。如果想本地测试，或者部署模式并不是大型集群，使用Kubernetes过于臃肿庞大。

使用Quartz实现定时任务执行，同时可开启Quartz分布式模式实现小型集群的定时任务管理需求



## 快速开始

1.创建数据表

执行resources/schema 文件夹下的创表SQL。使用mysql数据库作为存储

2.启动 Spring cloud dataflow

java -jar spring-cloud-dataflow-server-local.jar

3.创建定时任务开始使用



## 备注

基于Spring cloud DataFlow 1.7.4.RELEASE进行修改

[下载](https://github.com/c0ying/spring-cloud-dataflow-local-schedule/releases/download/1.7.4.RELEASE/spring-cloud-dataflow-server-local-quartz-1.7.4.RELEASE.7z)
