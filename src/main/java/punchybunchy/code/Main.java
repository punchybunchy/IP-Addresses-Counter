package punchybunchy.code;

import punchybunchy.code.util.DataReader;
import punchybunchy.code.util.IpAddressValidator;

import static punchybunchy.code.util.IpAddressValidator.MAX_IN_DECIMAL_FORM;
import static punchybunchy.code.util.IpAddressValidator.MIN_IN_DECIMAL_FORM;


//Задачу подсчета уникальных ip адресов предлагается решить используя префиксное дерево Trie
//Дочерние элементы узов будут храниться, как наборы из пар «ключ-значение» (HashMap).
//Выбранная структура данных исключает хранения дубликатов. Таким образом проверка на уникальность
//и последующий подсчет ip адресов сводится к задачам: создать префиксное дерево и добавить в него ребуемые ip адреса
//При добавлении будем счетать колчество успешно добавленных строк (ip адресов)


public class Main {
    public static void main(String[] args) {
        //В filePath узказываем путь к файлу
        final String filePath = "./src/main/resources/ips";


        TreeNode treeNode = TreeNode.create();

        //Вариант для проверки на "слабом" ПК.
        //Проеряемый файл занимает более 120Гб (тестирование велось на слабой машине с 8Гб оперативной памяти).
        //Чтобы избежать OutOfMemory добавил фильтр, который позволяет ограничить объем обрабатываемых данных.
        //В данном случае мы фильтруем поток по определенному значению первого октета ip адреса
        //Для более мощных машин с большим объемом оперативной памяти приложение можно использовать без этой надстройки
        for (int index = MIN_IN_DECIMAL_FORM; index <= MAX_IN_DECIMAL_FORM; index++) {
            int currentIndex = index;
            DataReader.getLineFromFile(filePath)
                    .filter(IpAddressValidator::checkIp)
                    .filter(i -> {
                        String[] str = i.split("\\.");
                        return str[0].equals(String.valueOf(currentIndex));
                    }).forEach(treeNode::insert);
        }

        //Вариант для проверки на мощных машинах.
//        DataReader.getLineFromFile(filePath)
//                .filter(IpAddressValidator::checkIp)
//                .forEach(treeNode::insert);

        long uniqueIpsNumber = treeNode.getIpAddressesCounter();
        System.out.println("Amount of unique IP addresses is: " + uniqueIpsNumber);
    }
}
