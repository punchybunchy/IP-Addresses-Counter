package punchybunchy.code;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import punchybunchy.code.util.IpAddressConvertor;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestIpCounter {
    TreeNode treeNode;


    @BeforeEach
    void init() {
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

        exampleIps
                .forEach(line -> {
                    long number = IpAddressConvertor.ipToLong(line);
                    String key = String.valueOf(number);
                    treeNode.insert(key);
                });
        long actual = treeNode.getIpAddressesCounter();
        long expected = 5;

        assertThat(actual).isEqualTo(expected);
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

        exampleIps
                .forEach(line -> {
                    long number = IpAddressConvertor.ipToLong(line);
                    String key = String.valueOf(number);
                    treeNode.insert(key);
                });
        long actual = treeNode.getIpAddressesCounter();
        long expected = 4;

        assertThat(actual).isEqualTo(expected);
    }

}
