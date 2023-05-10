package punchybunchy.code;

import java.util.HashMap;
import java.util.Map;


public final class TreeNode {
    private boolean isLeaf;
    private final Map<Short, TreeNode> child;
    private long leafCounter;


    public static TreeNode create() {
        return new TreeNode();
    }


    public void insert(String ip) {

        TreeNode current = this;

        for (String k : ip.split("\\.")) {
            //Переводим в short для экономии памяти
            short key = Short.parseShort(k);

            //Проверяем есть ли данный ключ в данной узле, если нет, то добавляем ключи и создаем дочерний узел
            current.getChild().putIfAbsent(key, new TreeNode());
            //Вызываем следующий (дочерний) узел
            current = current.getChild().get(key);
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
    public boolean search(String ip) {
        TreeNode current = this;
        for (String k : ip.split("\\.")) {
            short key = Short.parseShort(k);
            current = current.getChild().get(key);

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
    public Map<Short, TreeNode> getChild() {
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
