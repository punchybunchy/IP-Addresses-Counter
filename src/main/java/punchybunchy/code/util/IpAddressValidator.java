package punchybunchy.code.util;


public class IpAddressValidator {
    public static final int IP_ADDRESS_OCTETS_NUMBER = 4;
    public static final int MAX_IN_DECIMAL_FORM = 255;
    public static final int MIN_IN_DECIMAL_FORM = 0;

    public static boolean checkIp(String ipAddress) {

        if (ipAddress.isEmpty()) {
            return false;
        }

        String[] ip = ipAddress.split("\\.");

        //Проверяем, что в полученный ip адрес состоит из 4 октетов
        if (ip.length != IP_ADDRESS_OCTETS_NUMBER) {
            System.out.printf("The input string: %s - is not an IP address \n", ipAddress);
            return false;
        }

        //Проверяем, что значения октетов, полученного ip адреса валидные
        for (String element : ip) {
            int decimalNumber = Integer.parseInt(element);
            if (decimalNumber > MAX_IN_DECIMAL_FORM || decimalNumber < MIN_IN_DECIMAL_FORM) {
                System.out.printf("The input string: %s - is not an IP address \n", element);
                return false;
            }
        }
        return true;
    }
}
