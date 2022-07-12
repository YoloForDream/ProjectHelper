package com.rockwell.scl.orderitem;

/**
 * @author RWang18
 */
public class tt {
    public static void main(String[] args){
        OrderFilter orderFilter1 = new OrderFilter("192.168.59.5","BJMESADMIN","CNK2sva1!","Record EBR(SCL) [1.0]");
        OrderFilter orderFilter2 = new OrderFilter("192.168.59.5","BJMESADMIN","CNK2sva1!","Record Pause Interval(SCL) [1.0]");
        OrderFilter orderFilter3 = new OrderFilter("192.168.59.5","BJMESADMIN","CNK2sva1!","Sublot Correction(SCL) [1.0]");
        orderFilter1.start();
        orderFilter2.start();
        orderFilter3.start();
        System.out.println(orderFilter1.getProcessOrderItem()+": "+ orderFilter1.getPath());
        System.out.println(orderFilter2.getProcessOrderItem()+": "+ orderFilter2.getPath());
        System.out.println(orderFilter3.getProcessOrderItem()+": "+ orderFilter3.getPath());
    }
}
