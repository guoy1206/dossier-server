package com.thunisoft.dzjz.app.example;

import com.thunisoft.dzjz.DossierApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 作者: guo yao
 * 日期: 2019年11月27日
 * 时间: 下午14:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DossierApplication.class})
public class RedisCacheExample {

    private static final String OBJECT_KEY = "MATERIALS_PROCESS_FAIL_KEY";

    @Resource
    private RedisTemplate redisTemplate;


    // 通用方法
    public void normal(){
        // 设置获取过期时间
        redisTemplate.expire(OBJECT_KEY,1, TimeUnit.DAYS);
        // 获取过期时间
        redisTemplate.getExpire(OBJECT_KEY);
        // 随机取出一个key
        redisTemplate.randomKey();
    }

    // list的操作
    @Test
    public void listOpsTestI() {
        String listKey = "LIST_TEST";
        // 清空操作
        redisTemplate.delete(listKey);
        //List 其实是一个双向链表，从下面的代码就能看出来可以从两边进行push/pop操作
        BoundListOperations<String, String> listOps = redisTemplate.boundListOps(listKey);
        listOps.leftPush("a");
        listOps.leftPush("b");
        // 查看listTest这个List的值
        System.err.println(listOps.range(0, -1));
        System.err.println(listOps.range(0, listOps.size()));
        // 取左边第一个值,并移除
//        System.err.println(listOps.leftPop());
        listOps.rightPush("c");
        listOps.rightPush("d");
        // 获取index的值
        System.err.println(listOps.index(0));
        System.err.println(listOps.index(-1));
        // 查看listTest这个List的值
        System.err.println(listOps.range(0, -1));
        //修改操作  将下标为0的值修改为 "set_1"，(下标从0开始)
        listOps.set(0, "set_1");  //
        System.err.println(listOps.range(0, -1));   //[3, set_1, 2]
        //删除操作  将下标为1，值为 set_1的元素删除
        listOps.remove(1, "set_1");
        System.err.println(listOps.range(0, -1));   //[3, 2]
    }

    @Test
    public void listOpsTestII() {
        String listKey = "LIST2_TEST";
        // 清空操作
        redisTemplate.delete(listKey);
        BoundListOperations<String, String> listOps = redisTemplate.boundListOps(listKey);
        listOps.rightPushAll("1", "2", "3", "4", "5", "6");
        System.err.println(listOps.range(0, -1));
        // trim测试 [裁剪start到end位置的元素]
//        listOps.trim(1, 3);
//        System.err.println(listOps.range(0, -1));
        System.err.println(listOps.range(3, 5));
    }

    // set的操作
    @Test
    public void setOpsTestI() {
        String setKey = "SET_TEST";
        redisTemplate.delete(setKey);
        BoundSetOperations<String, String> setOps = redisTemplate.boundSetOps(setKey);
        //添加
        setOps.add("c", "1", "2", "3", "4");
        setOps.add("g", "a", "b", "1", "2");
        // 查询 set集合是无效的
        System.err.println(setOps.members());
        // 判断是否有这个值
        System.err.println(setOps.isMember("a"));
        System.err.println(setOps.isMember("bb"));
        // 删除，可删除多个值
        setOps.remove("setTest1", "3");
        System.err.println(setOps.members());
    }

    // 非绑定方式demo
    @Test
    public void setOpsTestII() {
        SetOperations<String, String> setOps = redisTemplate.opsForSet();
        setOps.add("key1", "1", "2", "3", "4");
        setOps.add("key2", "a", "b", "1", "2");
        // 查询 set集合是无效的
        System.err.println(setOps.members("key1"));
        System.err.println(setOps.members("key2"));
        // 判断是否有这个值
        System.err.println(setOps.isMember("key1", "4")); // true
        System.err.println(setOps.isMember("key2", "4")); // true
        // 交集
        System.err.println(setOps.intersect("key1", "key2"));//[2]
        // 并集
        System.err.println(setOps.union("key1", "key2"));//[d, 2, c, 1, a, 3, b]
        // 差集 [查找key1中不包含key2的元素]
        System.err.println(setOps.difference("key1", "key2"));//[3,4]

        // 随机获取一个元素
        System.err.println(setOps.randomMember("key1"));
        // 随机获取并移除一个元素
        System.err.println(setOps.pop("key1"));
        // 移除
        setOps.remove("key1", "2", "4");
        System.err.println(setOps.members("key1"));
        // 删除
        redisTemplate.delete("key1");
        redisTemplate.delete("key2");
        System.err.println(setOps.members("key1"));
        System.err.println(setOps.members("key2"));

    }

    // ZSetOps操作
    @Test
    public void testZSetOps() {
        String zsetKey = "ZSET_TEST";
        redisTemplate.delete(zsetKey);
        BoundZSetOperations<String, String> zsetOps = redisTemplate.boundZSetOps(zsetKey);
        // 添加
        zsetOps.add("tumblr.com", 10);
        zsetOps.add("baidu.com", 8);
        zsetOps.add("163.com", 6);
        zsetOps.add("sina.com", 2);

        // 默认按照分数的升序排列
        System.err.println(zsetOps.range(0, -1));  // [sina.com, 163.com, baidu.com, tumblr.com]
        // 获取第一个元素的值
        System.err.println(zsetOps.range(0, 0).toString());  // [sina.com]  错误的
        System.err.println(zsetOps.range(0, 0).toArray()[0]); // sina.com
        // 按分数从大到小排列
        System.err.println(zsetOps.reverseRange(0, -1)); // [tumblr.com, baidu.com, 163.com, sina.com]
        // 查看对象score
        System.err.println(zsetOps.score("163.com")); // 6.0
        // 查看排名
        System.err.println(zsetOps.rank("tumblr.com"));  // 3 第一名是0 sina.com
        // 按照分数范围查询
        System.err.println(zsetOps.rangeByScore(6, 8));  // [163.com, baidu.com]

        // 删除,按照对象
        System.err.println(zsetOps.range(0, -1));
        zsetOps.remove("360.com");
        System.err.println(zsetOps.range(0, -1));
        // 删除，根据下标 (范围)
        zsetOps.removeRange(0, 1);
        System.err.println(zsetOps.range(0, -1));
        // 删除，根据score (范围)
        zsetOps.removeRangeByScore(2, 8);
        System.err.println(zsetOps.range(0, -1));
    }

    // String 操作
    @Test
    public void testValueOps() {
        String stringKey = "stringtest";
        redisTemplate.delete(stringKey);
        BoundValueOperations<String, String> stringOps = redisTemplate.boundValueOps(stringKey);
        stringOps.set("abc");
        System.err.println(stringOps.get());  // abc
        stringOps.append("-dfg");
        System.err.println(stringOps.get());  // abc-dfg
    }

    // hash表操作 和Map差不多
    @Test
    public void testHashOps() {
        String mapKey = "MAP_TEST";
        redisTemplate.delete(mapKey);
        BoundHashOperations<String, String, Object> mapOps = redisTemplate.boundHashOps(mapKey);
        // 添加
        mapOps.put("key1", "aa");
        Map<String, String> map = new HashMap<String, String>();
        map.put("key2", "bb");
        map.put("key3", "cc");
        map.put("key4", "dd");
        mapOps.putAll(map);

        // 根据key查询
        System.err.println(mapOps.get("key3")); // cc

        // 查询所有的key
        System.err.println(mapOps.keys());  // [key3, key4, key2, key1]

        // 查询 所有的value
        System.err.println(mapOps.values());  // [aa, bb, cc, dd]

        // 删除 key
        mapOps.delete("key1");
        System.err.println(mapOps.keys()); // [key2, key4, key3]
    }

}
