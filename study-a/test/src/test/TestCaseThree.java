package test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class TestCaseThree {

    public String drawGift() {
        int a = (int) (Math.random() * 100);
        System.out.println(a);
        AtomicInteger length = new AtomicInteger();
        int random = a;
        AtomicReference<String> result = new AtomicReference<>();
//        getCard().forEach((k, v) -> {
//            System.out.println(length);
//            if ((random >= length.get()) && (random < length.addAndGet(v))) {
//                System.out.println(k);
//                result.set(k);
//            }
//        });
        // Stream<HashMap<String ,Integer>> stream=Stream.of(getCard()); 转化为流式处理

        for (Map.Entry<String, Integer> map : getCard().entrySet()) {
            if ((random >= length.get()) && (random < length.addAndGet(map.getValue()))) {
                result.set(map.getKey());
                break;
            }
        }
        return result.get();
    }


    public static void main(String[] args) {
        System.out.println(new TestCaseThree().drawGift());
    }

    public static HashMap<String, Integer> getCard() {
        HashMap<String, Integer> map = new HashMap();
        map.put("a", 50);
        map.put("b", 20);
        map.put("c", 10);
        map.put("d", 10);
        map.put("e", 8);
        map.put("f", 2);
        return map;
    }

}
