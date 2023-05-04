package punchybunchy.code;

import punchybunchy.code.util.FileReader;
import punchybunchy.code.util.IpAddressConvertor;
import punchybunchy.code.util.IpAddressGenerator;

//Задачу подсчета уникальных ip адресов предлагается решить используя префиксное дерево Trie
//Дочерние элементы узов будут храниться, как наборы из пар «ключ-значение» (HashMap).
//Выбранная структура данных исключает хранения дубликатов. Таким образом проверка на уникальность
//и последующий подсчет ip адресов сводится к задачам: создать префиксное дерево и добавить в него ребуемые ip адреса
//При добавлении будем счетать колчество успешно добавленных строк (ip адресов)


public class Main {
    public static void main(String[] args) {
        //Для демонстрации работы приложения сгенерируем 1млн адресов и сохраняем в файл
        final int amountOfIpAddresses = 1000000;
        final String filePath = "./src/main/resources/ips";
        IpAddressGenerator.generateIpAddressesCollection(amountOfIpAddresses, filePath);


        TreeNode tree = TreeNode.create();
        //Считываем ip адреса построчно из сгенеренного файла
        FileReader.getLineFromFile(filePath)
                .forEach(line -> {

                    //Так как сложность добавления O(k), где k длина ключа, имеет смысл сократить "путь"
                    // Приведем вид ip адреса к десятичному типу long
                    //Сократим строку вида 255.255.255.255 (15 символов) до 4294967295 (10 символов)
                    long number = IpAddressConvertor.ipToLong(line);
                    String key = String.valueOf(number);

                    //Построчно заполняем дерево. Передаем полученное число в виде строки
                    tree.insert(key);
                });

        long uniqueIpsNumber = tree.getIpAddressesCounter();
        System.out.println("Amount of unique IP addresses is: " + uniqueIpsNumber);
    }
}
