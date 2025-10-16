package org.chomookun.arch4vue;

import org.dizitart.no2.Nitrite;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConfigurationPropertiesScan
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setPathMatcher(new AntPathMatcher());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations(
                        "file:src/main/vue/dist/"
                )
                .setCachePeriod(0); // 개발 중 캐싱 방지
    }

    @Bean
    public Nitrite nitrite() {
        return Nitrite.builder()
                .openOrCreate();
    }



//    @Bean(destroyMethod = "stop")
//    public MongodExecutable embeddedMongoServer() throws IOException {
//        ImmutableMongodConfig mongodConfig = MongodConfig.builder()
//                .version(Version.Main.DEVELOPMENT)
//                .net(new Net(27017, false))
//                .build();
//        MongodStarter starter = MongodStarter.getDefaultInstance();
//        MongodExecutable mongodExecutable = starter.prepare(mongodConfig);
//        mongodExecutable.start();
//        return mongodExecutable;
//    }
//
//    @Bean
//    @DependsOn("embeddedMongoServer")
//    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
//        return new MongoTemplate(mongoClient, "test");
//    }

}
