#eureka-legacy-registrar
[![][travis img]][travis]
[![][maven img]][maven]
[![][release img]][release]
[![][license img]][license]

A small lib to allow registration of legacy applications in [Eureka](https://github.com/Netflix/eureka) service discovery. It also comes with (optional) Spring Boot support with
annotations making it very easy to use together with Spring Boot.
 
##Typical use cases
Some typical use cases where the `eureka-legacy-registrar` lib can be very useful:

###You are introducing Eureka in an existing architecture
The `eureka-legacy-registrar` lib will make it very easy to register all existing services in Eureka so that new, and migrated, applications can use a Eureka-only solution rather
than having a mix of using service discovery and the previous legacy solutions of finding services it needs to talk to.

This also allows for the existing services to get Eureka-enabled independently and in a pace suitable for the respective development team. 

###You are unable to affect legacy applications
If you, for whatever reason, have an existing application that you are unable to have register itself in Eureka, either by changing its codebase or by having a side-car process, then
the `eureka-legacy-registrar` will allow you to register that application in Eureka. This scenario could, for example, be caused by 3rd party applications, or applications where it is 
impossible to affect either code or deployments.

##Examples
Check out the example code in the test package for code examples.

###Spring Boot example
Just add the `@EnableEurekaLegacyRegistrar`annotation and appropriate configuration in your `application.yml` and you are good to go.

Eg.:

```java
@EnableEurekaLegacyRegistrar
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

An example of an `application.yml` for registering two different services, where one service have two instances:  

```yaml
eureka:
  legacy:
    client:
      serviceUrlDefault: http://eureka-server-1:8761/eureka/,http://eureka-server-2:8761/eureka/
  
    instances:
      -
        appName: legacy-app
        virtualHostName: legacy-app
        instanceId: 10.0.1.15:legacy-app:1 # instanceId should be unique per instance
        hostName: 10.0.1.15
        ipAddress: 10.0.1.15 # optional property ipAddress
        port: 8080 # optional, will default to 8080
        healthCheckUrl: http://10.0.1.15:8081/manage/health #optional
        statusPageUrl: http://10.0.1.15:8081/manage/info #optional
        instanceEnabledOnit: true #optional, will default to true
      -
        appName: legacy-app
        virtualHostName: legacy-app
        instanceId: 10.0.1.18:legacy-app:2
        hostName: 10.0.1.18
        ipAddress: 10.0.1.18 
        port: 8080
        healthCheckUrl: http://10.0.1.18:8081/manage/health
        statusPageUrl: http://10.0.1.18:8081/manage/info
        instanceEnabledOnit: true
      -
        appName: legacy-app-2
        virtualHostName: legacy-app-2
        instanceId: 10.0.1.18:legacy-app-2:1
        hostName: 10.0.1.18
        ipAddress: 10.0.1.18
        port: 8180
        healthCheckUrl: http://10.0.1.18:8180/
        statusPageUrl: http://10.0.1.18:8180/
        instanceEnabledOnit: true
```

###Non-Spring Boot example
Create an instance of `EurekaLegacyRegistrarApplication` and place your configuration in a file called `eureka-client.properties`.

Eg.:

```java
final EurekaLegacyRegistrarApplication application = new EurekaLegacyRegistrarApplication();
application.start();
```

An example of an `eureka-client.properties` for registering two different services, where one service have two instances:  

```ini
eureka.preferSameZone=true
eureka.shouldUseDns=false
eureka.serviceUrl.default=http://localhost:8761/eureka/

eureka.decoderName=JacksonJson


eureka.legacy.app0.name=legacy-app
eureka.legacy.app0.vipAddress=legacy-app
# instanceId should be unique per instance
eureka.legacy.app0.instanceId=10.0.1.15:legacy-app:1
eureka.legacy.app0.hostName=10.0.1.15
# optional property ipAddress
eureka.legacy.app0.ipAddress=10.0.1.15
eureka.legacy.app0.port=8080
eureka.legacy.app0.healthCheckUrl=http://10.0.1.15:8081/manage/health
eureka.legacy.app0.statusPageUrl=http://10.0.1.15:8081/manage/info
eureka.legacy.app0.traffic.enabled=true

eureka.legacy.app1.name=legacy-app
eureka.legacy.app1.vipAddress=legacy-app
eureka.legacy.app1.instanceId=10.0.1.15:legacy-app:2
eureka.legacy.app1.hostName=10.0.1.18
eureka.legacy.app1.ipAddress=10.0.1.18
eureka.legacy.app1.port=8080
eureka.legacy.app1.healthCheckUrl=http://10.0.1.18:8081/manage/health
eureka.legacy.app1.statusPageUrl=http://10.0.1.18:8081/manage/info
eureka.legacy.app1.traffic.enabled=true

eureka.legacy.app2.name=legacy-app-2
eureka.legacy.app2.vipAddress=legacy-app-2
eureka.legacy.app2.instanceId=10.0.1.15:legacy-app-2:2
eureka.legacy.app2.hostName=10.0.1.18
eureka.legacy.app2.ipAddress=10.0.1.18
eureka.legacy.app2.port=8180
eureka.legacy.app2.healthCheckUrl=http://10.0.1.18:8080/manage/health
eureka.legacy.app2.statusPageUrl=http://10.0.1.18:8080/manage/info
eureka.legacy.app2.traffic.enabled=true
```

##Releases

Releases are available at the Maven central repository. Or you can just use the source code/binaries directly from GitHub if you prefer that.

####Maven
```xml
<dependency>
    <groupId>se.sawano.eureka</groupId>
    <artifactId>legacy-registrar</artifactId>
    <version>0.0.3</version>
</dependency>
```

####Gradle
```groovy
'se.sawano.eureka:legacy-registrar:0.0.3'
```

[travis]:https://travis-ci.org/sawano/eureka-legacy-registrar
[travis img]:https://travis-ci.org/sawano/eureka-legacy-registrar.svg?branch=master
[maven]:http://search.maven.org/#search|gav|1|g:"se.sawano.eureka"%20AND%20a:"legacy-registrar"
[maven img]:https://maven-badges.herokuapp.com/maven-central/se.sawano.eureka/legacy-registrar/badge.svg
[release]:https://github.com/sawano/eureka-legacy-registrar/releases
[release img]:https://img.shields.io/github/release/sawano/eureka-legacy-registrar.svg
[license]:LICENSE
[license img]:https://img.shields.io/badge/License-Apache%202-blue.svg
