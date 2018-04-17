package fan.jdbc.test;

import java.util.*;

//遍历hashmap的方法
public class test1 {
    public static void main(String[] args) {
//        Map<String,String> map = new HashMap<String,String>();
//        map.put("1","value1");
//        map.put("2","value2");
//        map.put("3","value3");
//        map.put("4","value4");
//
//
////        方法1：  通过keyset遍历key和value
//        System.out.println("通过map.keyset遍历");
//        for (String key: map.keySet()) {
//            System.out.println("key:"+key+" value:"+map.get(key));
//        }

//        testSet();
//        testLinkList();
//      testArraylist();

        testMap();
    }

    public static  void  testArraylist(){
//        ArrayList 有序，允许有重复元素
        List<String> list = new ArrayList<String>();
        list.add("hello");
        list.add("hello");
        list.add("world !");
        list.add("world");
        System.out.println(list);


        System.out.println("第一种遍历方式   get()");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        System.out.println("第二中方式  iterator");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println("第3中遍历方式  for each ");
        for (String str:list) {
            System.out.println(str);
        }
    }
    public  static  void  testLinkList(){
//        有序可重复
        List<String> list = new LinkedList<String>();
        list.add("hello");
        list.add("hello");
        list.add("world !");
        list.add("world");

        System.out.println("第一种遍历方式   get()");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        System.out.println("第二中方式  iterator");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println("第3中遍历方式  for each ");
        for (String str:list) {
            System.out.println(str);
        }
    }

    public  static  void  testSet(){
//        无序不重复
        Set<String> set = new HashSet<String>();
        set.add("hello");
        set.add("hello");    //自动过滤重复元素
        set.add("world !");
        set.add("world");
        set.add("3");
        set.add("3");
        set.add("6");
        set.add("9");
        System.out.println(set);


        System.out.println("第一种方式  Iterator");
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println("第2中方式  for each");
        for (String s:set) {
            System.out.println(s);
        }


    }
    public static  void  testMap(){
//        无序，key值不重复
        Map<String,String> map = new HashMap<String,String>();
        map.put("1","hello");
        map.put("2","hello");
        map.put("2","worlds");   //会覆盖上一个元素
        map.put("4","www");

        System.out.println(map);

        System.out.println("方法1   for each + map.ketset ");
        for (String key:map.keySet()) {
            System.out.println("key="+key+"  values="+map.get(key));
        }

        System.out.println("方法2  Iterator + map.keyset");
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next().toString();
            System.out.println("key="+key+" value="+map.get(key));
        }

        System.out.println("方法3  map.values");
        for (String key:map.values()) {
            System.out.println(key);
        }
    }
}
