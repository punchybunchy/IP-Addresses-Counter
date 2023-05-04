package punchybunchy.code.util;

import java.util.stream.IntStream;

public class IpAddressConvertor {
    public static final int INVALID_IP_ADDRESS = -1;
    public static final int IP_ADDRESS_OCTETS_NUMBER = 4;
    public static final int SIZE_IN_DECIMAL_FORM = 256;
    public static final int EXPONENTIATION = 3;

    public static long ipToLong(String ipAddress) {

        if (ipAddress.isEmpty()) {
            return INVALID_IP_ADDRESS;
        }

        String[] ip = ipAddress.split("\\.");

        //Проверяем, что в полученный ip адрес состоит из 4 октетов
        if (ip.length != IP_ADDRESS_OCTETS_NUMBER) {
            System.out.printf("The input string: %s - is not an IP address \n", ipAddress);
            return INVALID_IP_ADDRESS;
        }

        //Проверяем, что числа октетов, полученного ip адреса валидные
        for (String element : ip) {
            int decimalNumber = Integer.parseInt(element);
            if ( decimalNumber >= SIZE_IN_DECIMAL_FORM) {
                System.out.printf("The input string: %s - is not an IP address \n", element);
                return INVALID_IP_ADDRESS;
            }
        }

        return IntStream
                .range(0, ip.length)
                .mapToObj(index -> (Long.parseLong(ip[index]) * Math.pow(SIZE_IN_DECIMAL_FORM, EXPONENTIATION - index)))
                .mapToLong(Double::longValue)
                .sum();
    }
}
