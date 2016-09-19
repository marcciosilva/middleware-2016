package com.fing;

import com.fing.Domain.DtoOrder;
import com.fing.Domain.DtoOrderItems;
import com.fing.Domain.DtoOrderItemsWithOrderInfo;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.splitter.AbstractMessageSplitter;
import org.springframework.messaging.Message;

import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan Manuel on 9/18/2016.
 */
public class SplitterBean extends AbstractMessageSplitter {
    //public class SplitterBean {
    @Override
    protected Object splitMessage(Message<?> message) {

        List<String> toReturn = new ArrayList<>();

        try {
            JAXBContext jc = JAXBContext.newInstance(DtoOrder.class);
            JAXBContext jc2 = JAXBContext.newInstance(ArrayList.class);
            Unmarshaller u = null;
            u = jc.createUnmarshaller();
            StringBuffer xmlStr = new StringBuffer(message.getPayload().toString());
            Object o = u.unmarshal(new StreamSource(new StringReader(xmlStr.toString())));
            DtoOrder order = (DtoOrder) o;

            if (order != null) {
                for (DtoOrderItems item : order.getItemsList()) {
                    DtoOrder newOrder = new DtoOrder();
                    newOrder.getOrderValues(order);
                    newOrder.addItemToList(item);

                    System.out.print(newOrder);
                    // output pretty printed
                    Marshaller jaxbMarshaller = jc.createMarshaller();
                    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                    StringWriter writer = new StringWriter();

                    jaxbMarshaller.marshal(newOrder, writer);
                    toReturn.add(writer.toString());
                }


                return toReturn;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

//    @Splitter
//    public List<DtoOrderItemsWithOrderInfo> split(DtoOrder order) {
//        List<DtoOrderItemsWithOrderInfo> toReturn = new ArrayList<>();
//        if (order != null) {
//            for(DtoOrderItems item: order.getItemsList()){
//                DtoOrderItemsWithOrderInfo dtoOrderItemsWithOrderInfo = DtoOrderItemsWithOrderInfo.toDtoOrderItemsWithOrderInfo(order, item);
//                toReturn.add(dtoOrderItemsWithOrderInfo);
//                System.out.print(dtoOrderItemsWithOrderInfo);
//            }
//        }
//        return toReturn;
//    }
}
