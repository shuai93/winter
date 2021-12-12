# winter-agent
SpringBoot Agent 开发


## 功能如下

用于监控接口的各项指标，可以直接在 winter-service 服务中使用

收集代码各个方法的执行时间、入参、返回值等。

```bash

java -javaagent:../agent-agent/target/agent-agent-1.0-SNAPSHOT.jar=../agent-agent/target/classes/agent.properties -jar target/agent-service-1.0-SNAPSHOT.jar

```

## 其它

- agent 代理技术可以用来做 接口的 Mock，配合自动化测试完成业务接口的顺利测试
- agent-agent 不应该使用任何 Spring 的包。否则会出现 ClassNotFoundException