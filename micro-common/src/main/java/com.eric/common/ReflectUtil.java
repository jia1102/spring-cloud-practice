package com.eric.common;

import com.alibaba.fastjson.JSON;
import com.eric.entities.LoggerEntity;
import com.google.common.collect.Maps;
import lombok.extern.log4j.Log4j;
import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;
import org.apache.commons.beanutils.PropertyUtilsBean;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Des: 动态为类添加属性的反射方法
 */
//@Log4j
public class ReflectUtil {

    public static Object getTarget(Object dest, Map<String, Object> addProperties) {
        // get property map
        PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
        PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(dest);
        Map<String, Class> propertyMap = Maps.newHashMap();
        for (PropertyDescriptor d : descriptors) {
            if (!"class".equalsIgnoreCase(d.getName())) {
                propertyMap.put(d.getName(), d.getPropertyType());
            }
        }
        // add extra properties
        addProperties.forEach((k, v) -> propertyMap.put(k, v.getClass()));
        // new dynamic bean
        DynamicBean dynamicBean = new DynamicBean(dest.getClass(), propertyMap);
        // add old value
        propertyMap.forEach((k, v) -> {
            try {
                // filter extra properties
                if (!addProperties.containsKey(k)) {
                    dynamicBean.setValue(k, propertyUtilsBean.getNestedProperty(dest, k));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // add extra value
        addProperties.forEach((k, v) -> {
            try {
                dynamicBean.setValue(k, v);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Object target = dynamicBean.getTarget();
        return target;
    }
    // 显示全部属性的对象
    public static class DynamicBean {
        /**
         * 目标对象
         */
        private Object target;

        /**
         * 属性集合
         */
        private BeanMap beanMap;

        public DynamicBean(Class superclass, Map<String, Class> propertyMap) {
            this.target = generateBean(superclass, propertyMap);
            this.beanMap = BeanMap.create(this.target);
        }


        /**
         * bean 添加属性和值
         *
         * @param property
         * @param value
         */
        public void setValue(String property, Object value) {
            beanMap.put(property, value);
        }

        /**
         * 获取属性值
         *
         * @param property
         * @return
         */
        public Object getValue(String property) {
            return beanMap.get(property);
        }

        /**
         * 获取对象
         *
         * @return
         */
        public Object getTarget() {
            return this.target;
        }


        /**
         * 根据属性生成对象
         *
         * @param superclass
         * @param propertyMap
         * @return
         */
        private Object generateBean(Class superclass, Map<String, Class> propertyMap) {
            BeanGenerator generator = new BeanGenerator();
            if (null != superclass) {
                generator.setSuperclass(superclass);
            }
            BeanGenerator.addProperties(generator, propertyMap);
            return generator.create();
        }
    }
    // 只显示最新属性的对象
    public static class DynamicBean2 {

        /**
         * 目标对象
         */
        private Object target;

        /**
         * 缓存方法
         */
        private Map<String, Method> methodMap;

        public DynamicBean2() {
            super();
        }

        public DynamicBean2(Class superclass, Map<String, Class> propertyMap) {
            this.target = generateBean(superclass, propertyMap);
            methodMap = Maps.newConcurrentMap();
            Method[] methods = this.target.getClass().getMethods();
            for (Method m : methods) {
                methodMap.put(m.getName().toLowerCase(), m);
            }
        }


        /**
         * bean 添加属性和值
         *
         * @param property
         * @param value
         */
        public void setValue(String property, Object value) throws Exception {
            String name = String.format("%s%s", "set", property).toLowerCase();
            if (methodMap.containsKey(name)) {
                methodMap.get(name).invoke(this.target, value);
            }
        }

        /**
         * 获取属性值
         *
         * @param property
         * @return
         */
        public Object getValue(String property) throws Exception {
            String name = String.format("%s%s", "get", property).toLowerCase();
            if (methodMap.containsKey(name)) {
                return methodMap.get(name).invoke(this.target);
            }
            return null;
        }

        /**
         * 获取对象
         *
         * @return
         */
        public Object getTarget() {
            return this.target;
        }


        /**
         * 根据属性生成对象
         *
         * @param superclass
         * @param propertyMap
         * @return
         */
        private Object generateBean(Class superclass, Map<String, Class> propertyMap) {
            BeanGenerator generator = new BeanGenerator();
            if (null != superclass) {
                generator.setSuperclass(superclass);
            }
            BeanGenerator.addProperties(generator, propertyMap);
            return generator.create();
        }
    }

    public static void main(String[] args) {
        LoggerEntity entity = new LoggerEntity();
        entity.setAppName("appname");
        entity.setOperator("add");
        entity.setResult("result");
        entity.setUri("uri");
        entity.setMethod("method");
        Map<String, Object> addProperties = new HashMap() {{
            put("hello", "world");
            put("abc", "123");
        }};
        Object obj = ReflectUtil.getTarget(entity, addProperties);
//        List<Object> list = Lists.newArrayList();
//        list.add(obj);
        System.out.println(JSON.toJSONString(ReflectUtil.getTarget(entity, addProperties)));
    }
}
