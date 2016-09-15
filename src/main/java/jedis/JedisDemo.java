package jedis;

import redis.clients.jedis.Jedis;

/**
 * Created by hbowang on 7/15/16.
 */
public class JedisDemo {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.99.100",32768);
        String name = jedis.get("name1");
        System.out.println(name);
    }
}
