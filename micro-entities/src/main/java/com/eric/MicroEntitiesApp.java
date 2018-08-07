package com.eric;

import com.eric.entities.Dept;

/**
 * Hello world!
 *
 */
public class MicroEntitiesApp {
    public static void main( String[] args ) {
        Dept dept = new Dept();
        dept.setDeptno(5l);
        Dept a = dept.setDname("test");
        System.out.println( "This is Dname:" + dept.getDname() + ", Deptno:" + dept.getDeptno());
        System.out.println("********** Begin ********** This is Db_source:" + a.getDb_source() + ", Dname:" + a.getDname() + "********** End **********");
    }
}
