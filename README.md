##**2022-03-31**<br/>
###1、关于服务间调用的的问题<br/>
- 1.1 nacos作为注册中心，服务使用默认分组或相同分组名，否则无法调用
- 1.2 pom中引用spring-cloud-starter-alibaba-nacos-discovery需要排除spring-cloud-starter-netflix-ribbon<br/>
  &nbsp;因为nacos弃用了ribbon，同时需要引用spring-cloud-loadbalancer
