package com.example.sprboot.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.*;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;

/**
 * 创建时间:2019/05/13
 * 创建人:pmc
 * 描述:
 */

/**
 * redis配置
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport
{
    @Resource
    private RedisConnectionFactory redisConnectionFactory;
    private Duration timeToLive = Duration.ZERO;

    public void setTimeToLive(Duration timeToLive)
    {
        this.timeToLive = timeToLive;
    }

    //自定义生成key策略
    @Bean
    public KeyGenerator keyGenerator()
    {
        return new KeyGenerator()
        {
            @Override
            public Object generate(Object o, Method method, Object... objects)
            {
                StringBuilder stringBuilder = new StringBuilder();
                //stringBuilder.append(o.getClass().getName());
                //stringBuilder.append(method.getName());
                //为方便更新、删除、查询自定义的key策略
                String[] valuearr = new String[1];
                Cacheable cacheable = method.getAnnotation(Cacheable.class);
                if (cacheable != null)
                {
                    valuearr = cacheable.value();
                }
                CachePut cachePut = method.getAnnotation(CachePut.class);
                if (cachePut != null)
                {
                    valuearr = cachePut.value();
                }
                CacheEvict cacheEvict = method.getAnnotation(CacheEvict.class);
                if (cacheEvict != null)
                {
                    valuearr = cacheEvict.value();
                }
                stringBuilder.append(valuearr[0]);
                for (Object object : objects)
                {
                    stringBuilder.append(object.toString());
                }
                return stringBuilder.toString();
            }
        };
    }

    //缓存管理器
    /*容器中将注册的是CacheManager实例RedisCacheManager对象，RedisCacheManager来负责创建RedisCache作为缓存管理组件，由RedisCache操作redis服务器实现缓存数据操作。实际测试发现默认注入的RedisCacheManager操作缓存用的是RedisTemplate<Object, Object>，因此我们需要自定义cacheManager，替换掉默认的序列化器。*/
    @Bean
    public CacheManager cacheManager()
    {
        RedisCacheManager.RedisCacheManagerBuilder redisCacheManagerBuilder = RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory);
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().disableCachingNullValues().entryTtl(this.timeToLive).serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer())).serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()));
        //redisCacheConfiguration = redisCacheConfiguration.entryTtl(Duration.ofMillis(30l)).disableCachingNullValues();//设置缓存时间30min和空值不缓存
        //return redisCacheManagerBuilder.cacheDefaults(redisCacheConfiguration).build();
        return redisCacheManagerBuilder.cacheDefaults(redisCacheConfiguration).build();//启动事务
    }

    /**
     * 更换 RedisTemplate配置
     *
     * @return RedisTemplate
     */
    @Bean
    @SuppressWarnings("unchecked")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
    {
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);//创建jackson实例
        ObjectMapper objectMapper = new ObjectMapper();//jackson objectmapper 序列对象
        objectMapper.setVisibility(PropertyAccessor.ALL, Visibility.ANY);//设置访问所有属性
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-DD HH:mm:ss"));//设置时间映射格式
        objectMapper.enableDefaultTyping(DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);//更改jackson序列的objectmapper对象

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.afterPropertiesSet();
        redisTemplate.setKeySerializer(keySerializer());// key序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);// value序列化
        redisTemplate.setHashKeySerializer(keySerializer());//hash key序列化
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);//hash value序列化

        return redisTemplate;
    }

    private RedisSerializer<String> keySerializer()
    {
        return new StringRedisSerializer();
    }

    private RedisSerializer<Object> valueSerializer()
    {
        return new Jackson2JsonRedisSerializer(Object.class);
    }
}
