package spittr.db.jdbc;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.junit.Test;
import spittr.domain.Spitter;

public class OriginalEhCacheTest {

    @Test
    public void test1() {
        // 1. 创建缓存管理器
        CacheManager cacheManager = CacheManager.create("src/main/resources/spittr/cache/hellocache.xml");

        // 2. 获取缓存对象
        Cache cache = cacheManager.getCache("HelloWorldCache");

        // 3. 创建元素
        Element element1 = new Element("key1", "value1");

        // 4. 将元素添加到缓存
        cache.put(element1);

        // 5. 获取缓存
        Element element2 = cache.get("key1");
        System.out.println(element2);
        System.out.println(element2.getObjectValue());

        // 6. 删除元素
//        cache.remove("key1");

        Spitter spitter = new Spitter(1L, "habuma", "password", "Craig Walls", "craig@habuma.com", false);

        Element celement = new Element("zhangsan", spitter);
        cache.put(celement);
        Element customer2 = cache.get("zhangsan");
        System.out.println(customer2);
        System.out.println(customer2.getObjectValue());

        System.out.println(cache.getSize());

        // 7. 刷新缓存，持久化到硬盘
        cache.flush();

        // 8. 关闭缓存管理器
        cacheManager.shutdown();

    }

}
