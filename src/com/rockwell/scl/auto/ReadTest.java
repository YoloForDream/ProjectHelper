package com.rockwell.scl.auto;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author RWang18
 */
public class ReadTest {



    public static void main(String[] args) throws Exception {
//////////
        System.setProperty("com.rockwell.test.username", "BJMESADMIN");
        System.setProperty("com.rockwell.test.password", "CNK2sva1!");
        System.setProperty("HOST_ADDRESS", "192.168.59.20");
//
//        System.setProperty("com.rockwell.test.username", "BJMESADMIN");
//        System.setProperty("com.rockwell.test.password", "CNK2sva1!");
//        System.setProperty("HOST_ADDRESS", "192.168.59.10");
//        S1-03-089-rey S1-07-082S1-03-266
//        ReadTag readTag = new ReadTag("S1-03-268");
//        Map<String, Object> res = readTag.getTagValue();
//        Set<Map.Entry<String, Object>> entrySet = res.entrySet();
//        for (Map.Entry<String, Object> entry : entrySet) {
//            System.out.println(entry.getKey() + ":" + entry.getValue());
//        }``12
//        System.exit(0);
//        CP1 S1-03-093
//        CP2 S1-03-266
//        DX1 S1-03-216
//        SD1 S1-03-136

//        IAutomationService aiService = ServiceFactory.getService(IAutomationService.class);
//        IS88EquipmentService is88EquipmentService = ServiceFactory.getService(IS88EquipmentService.class);
//        IMESS88Equipment imess88Equipment = is88EquipmentService.loadEquipmentByIdentifier("S1-03-136");
//        List<IMESEquipmentProperty<?>> imesEquipmentPropertyList = ReadTag.getAllAutomationProperties(imess88Equipment);
//        for (int i = 0; i < imesEquipmentPropertyList.size(); i++) {
//           System.out.println(imesEquipmentPropertyList.get(i).getIdentifier());
//        }

        ReadOPCHelper readOPCHelper= new ReadOPCHelper("S1-03-136");

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(readOPCHelper.runnable1, 10, 10, TimeUnit.SECONDS);
//        ReadOPCHelper readOPCHelper= new ReadOPCHelper("S1-03-136","压力51");
//        System.out.println(readOPCHelper.getValue());


//        service.scheduleAtFixedRate(runnable1, 5, 1, TimeUnit.SECONDS);

//
//            BigDecimal foo,foo1;
//
//            foo= BigDecimal.valueOf(6.31f);
//            foo1= BigDecimal.valueOf(6.5f);
//            System.out.println(foo);
//            System.out.println(foo1);
//
//
//    }
    }
}

