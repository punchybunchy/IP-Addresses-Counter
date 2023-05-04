package punchybunchy.code;

import java.util.HashMap;
import java.util.Map;

import static punchybunchy.code.util.IpAddressConvertor.INVALID_IP_ADDRESS;

public final class TreeNode {
    private boolean isLeaf;
    private final Map<Character, TreeNode> child;
    private long leafCounter;


    public static TreeNode create() {
        return new TreeNode();
    }


    public void insert(String key) {
        if (key.length() == 0 || key.equals(String.valueOf(INVALID_IP_ADDRESS))) {
            return;
        }

        TreeNode current = this;

        for (char k : key.toCharArray()) {
            //Проверяем есть ли данный ключ в данной узле, если нет, то добавляем ключи и создаем дочерний узел
            current.getChild().putIfAbsent(k, new TreeNode());
            //Вызываем следующий (дочерний) узел
            current = current.getChild().get(k);
        }

        // По завершению помечаем текущий узел как лист, ip адрес добавлен
        if (!current.isLeaf()) {
            current.setLeaf(true);

            //считаем количество успешно добавленных листьев
            // каждый лист это успешно добавленный ip адрес
            leafCounter++;
        }
    }

    //На случай если потребуется найти отдельно взятый ip адрес
    //Метод возвращает true или false если не удалось найти адрес
    public boolean search(String key) {
        TreeNode current = this;
        for (char k : key.toCharArray()) {
            current = current.getChild().get(k);

            // если у текущего узла null значение, то достигли конца пути, но не нашли строку (ip адрес) целиком
            if (current == null) {
                return false;
            }
        }
        // если успешно достигли конца пути, текущий узел является листом
        // возвращаем true, строка (ip адрес найден)
        return current.isLeaf();
    }


    //Constructor, getters and setters

    private TreeNode() {
        isLeaf = false;
        child = new HashMap<>();
    }
    public Map<Character, TreeNode> getChild() {
        return child;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public long getIpAddressesCounter() {
        return leafCounter;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

}
