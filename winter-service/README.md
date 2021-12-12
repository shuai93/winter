# winter-service

SpringBoot 学习代码


## 功能如下

- 1. 基于 PowerMockito 的单元测试

- 2. 基于 docker-compose 部署的 SkyWalking

- 3. 基于 log4j 配置生成带有 traceId 的日志，详细配置见配置文件


## 备注

启动收集服务的参数，使用 `java -jar` 的方式启动服务。

```bash
java \
-javaagent:./apache-skywalking-apm-bin/agent/skywalking-agent.jar \
-Dskywalking.agent.service_name=winter \
-Dskywalking.collector.backend_service=127.0.0.1:11800 -jar \
./target/winter-0.0.1-SNAPSHOT.jar

```
