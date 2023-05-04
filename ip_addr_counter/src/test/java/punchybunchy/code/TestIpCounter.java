package punchybunchy.code;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import punchybunchy.code.util.IpAddressConvertor;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestIpCounter {
    private TreeNode treeNode;


    @BeforeEach
    final void init() {
        treeNode = TreeNode.create();
    }

    @Test
    void testInsert() {
        List<String> exampleIps = List.of(
                "0.0.0.1",
                "0.0.1.0",
                "0.1.0.0",
                "1.0.0.0",
                "1.1.1.1",
                "0.1.0.0",
                "0.0.1.0"
        );

        final int numberOfUniqueIps = 5;

        exampleIps
                .forEach(line -> {
                    long number = IpAddressConvertor.ipToLong(line);
                    String key = String.valueOf(number);
                    treeNode.insert(key);
                });
        long actual = treeNode.getIpAddressesCounter();

        assertThat(actual).isEqualTo(numberOfUniqueIps);
    }

    @Test
    void testInsertInvalidIps() {
        List<String> exampleIps = List.of(
                "0.0.0.1",
                "0.0.1.0",
                "0.1.0.0",
                "1.0.0.0",
                "1.1.1",
                "\n",
                "256.300.1.1",
                "0.0.1.0.1"
        );

        final int numberOfUniqueIps = 4;

        exampleIps
                .forEach(line -> {
                    long number = IpAddressConvertor.ipToLong(line);
                    String key = String.valueOf(number);
                    treeNode.insert(key);
                });
        long actual = treeNode.getIpAddressesCounter();

        assertThat(actual).isEqualTo(numberOfUniqueIps);
    }

}
