package com.axkea.review.util.random;


import java.util.Map;

/**
 * @author Genius
 * @date 2023/10/26 23:10
 **/
public class RandomFactory {

    private static Map<Class<? extends RandomMachine<?>>,RandomMachine<?>> map = Map.of(
            NumberRandomMachine.class,new NumberRandomMachine()
    );

    public static <T> RandomMachine<T> RandomMachine(Class<? extends RandomMachine<T>> clazz){
       if(!map.containsKey(clazz)){
          throw new RuntimeException("Invalid Random Machine");
       }
       return clazz.cast(map.get(clazz));
    }

}
