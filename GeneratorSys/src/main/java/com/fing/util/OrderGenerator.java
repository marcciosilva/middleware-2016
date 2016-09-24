package com.fing.util;

import com.fing.domain.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by jmsmuy on 19/08/16.
 * <p>
 * Esta clase genera una orden de compra aleatoria
 */
public class OrderGenerator {

    private static final int CANT_PALABRAS_POR_DESCRIPCION = 5;

    public static DtoOrder generateOrder() {
        DtoOrder order = new DtoOrder();
        // Primero obtenemos la lista de items
        List<DtoOrderItems> itemsList = generateOrderItems();
        // Ahora calculamos la suma
        Double totalAmount = 0d;
        if (itemsList != null) {
            for (DtoOrderItems item : itemsList) {
                totalAmount = totalAmount + item.getPrice();
            }
        }

        // Llenamos la orden
        order.setCreationDate(generateDate(true));
        order.setCurrency(generateCurrency());
        order.setCustomerId(generateCustomerId());
        order.setInstallments(generateInstallments());
        order.setOrderNumber(generateOrderNumber());
        order.setPaymentMethod(generatePaymentMethod());
        order.setTotalAmount(totalAmount);
        order.setItemsList(itemsList);

        return order;
    }

    /**
     * Genera una lista de items aleatorios
     *
     * @return
     */
    private static List<DtoOrderItems> generateOrderItems() {
        DtoOrderItems dtoOrderItem;
        List<DtoOrderItems> dtoOrderItemsList = new ArrayList<>();
        for (int i = 0; i < RandomGenerator.randomGen(0, 20); i++) {
            dtoOrderItem = new DtoOrderItems();
            dtoOrderItem.setAmount(generateOrderItemAmount());
            dtoOrderItem.setCategory(generateOrderItemCategory());
            dtoOrderItem.setDescription(generateOrderItemDescription());
            dtoOrderItem.setItemNumber(i);
            dtoOrderItem.setPrice(generateOrderItemPrice());
            dtoOrderItem.setProductId(generateOrderItemProductId());
            dtoOrderItemsList.add(dtoOrderItem);
        }
        return dtoOrderItemsList;
    }

    /**
     * Devuelve aleatoriamente un metodo de pago
     *
     * @return
     */
    private static String generatePaymentMethod() {
        int selectedValue = RandomGenerator.randomGen(0, EnumPaymentMethod.values().length - 1);
        return EnumPaymentMethod.values()[selectedValue].getEnumValue();
    }

    /**
     * Devuelve aleatoriamente un numero de orden
     * Por ahora solamente un random de 0 - 10000
     *
     * @return
     */
    private static int generateOrderNumber() {
        return RandomGenerator.randomGen(0, 10000);
    }

    /**
     * Devuelve aleatoriamente una cantidad de cuotas
     * Por ahora 1-36
     *
     * @return
     */
    private static Integer generateInstallments() {
        return RandomGenerator.randomGen(1, 36);
    }

    /**
     * Devuelve aleatoriamente un id de cliente
     * Por ahora 0 - 10000
     *
     * @return
     */
    private static int generateCustomerId() {
        return RandomGenerator.randomGen(0, 10000);
    }

    /**
     * Devuelve aleatoriamente un codigo de moneda
     *
     * @return
     */
    private static int generateCurrency() {
        int selectedValue = RandomGenerator.randomGen(0, EnumCurrency.values().length - 1);
        return EnumCurrency.values()[selectedValue].getEnumValue();
    }

    /**
     * Devuelve una fecha aleatoria
     *
     * @param todayIsMax indica si se toma la fecha de hoy como máxima
     * @return
     */
    private static Date generateDate(boolean todayIsMax) {
        int amountToAdd = 0;
        if (todayIsMax) {
            amountToAdd = RandomGenerator.randomGen(-1000, 0);
        } else {
            amountToAdd = RandomGenerator.randomGen(-1000, 1000);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, amountToAdd);
        return calendar.getTime();
    }

    /**
     * Devuelve aleatoriamente un id de producto
     * Por ahora entre 0 - 10000
     *
     * @return
     */
    private static Integer generateOrderItemProductId() {
        return RandomGenerator.randomGen(0, 10000);
    }

    /**
     * Devuelve aleatoriamente un precio de producto
     * Por ahora entre 0 - 1000
     *
     * @return
     */
    private static Double generateOrderItemPrice() {
        return RandomGenerator.randomGenDbl(0d, 1000d);
    }

    /**
     * Devuelve aleatoriamente una descripción del producto
     *
     * @return
     */
    private static String generateOrderItemDescription() {
        return PhraseGenerator.generateString(CANT_PALABRAS_POR_DESCRIPCION);
    }

    /**
     * Devuelve aleatoriamente una categoria del producto
     *
     * @return
     */
    private static Integer generateOrderItemCategory() {
        int selectedValue = RandomGenerator.randomGen(0, EnumItemCategory.values().length - 1);
        return EnumItemCategory.values()[selectedValue].getEnumValue();
    }

    /**
     * Devuelve aleatoriamente una cantidad
     * Por ahora entre 1 - 10
     *
     * @return
     */
    private static Integer generateOrderItemAmount() {
        return RandomGenerator.randomGen(1, 10);
    }

}
