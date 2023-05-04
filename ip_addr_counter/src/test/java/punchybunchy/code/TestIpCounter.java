package punchybunchy.code;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import punchybunchy.code.util.FileReader;
import punchybunchy.code.util.IpAddressConverter;
import punchybunchy.code.util.IpAddressGenerator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static punchybunchy.code.util.IpAddressConverter.INVALID_IP_ADDRESS;

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
                    long number = IpAddressConverter.ipToLong(line);
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
                    long number = IpAddressConverter.ipToLong(line);
                    String key = String.valueOf(number);
                    treeNode.insert(key);
                });
        long actual = treeNode.getIpAddressesCounter();

        assertThat(actual).isEqualTo(numberOfUniqueIps);
    }

    @Test
    void testIpAddressGenerator() {
        final int amountOfIpAddresses = 100;
        final String filePath = "./src/test/resources/ips";
        IpAddressGenerator.generateIpAddressesCollection(amountOfIpAddresses, filePath);
        Path path = Paths.get(filePath).toAbsolutePath().normalize();

        long actualNumberIpsInFile = FileReader.getLineFromFile(filePath).toList().size();


        assertThat(Files.exists(path)).isTrue();
        assertThat(amountOfIpAddresses).isEqualTo(actualNumberIpsInFile);
    }

    @Test
    void testIpAddressConverter() {
        final String validIp = "192.100.50.20";
        final long expectedValidIp = 3227791892L;
        long actualValidIp = IpAddressConverter.ipToLong(validIp);

        final String invalidIp1 = "256.100.288.17";
        final String invalidIp2 = "192.100.50.20.1";
        final String invalidIp3 = "100.50.20";

        assertThat(actualValidIp).isEqualTo(expectedValidIp);
        assertThat(IpAddressConverter.ipToLong(invalidIp1)).isEqualTo(INVALID_IP_ADDRESS);
        assertThat(IpAddressConverter.ipToLong(invalidIp2)).isEqualTo(INVALID_IP_ADDRESS);
        assertThat(IpAddressConverter.ipToLong(invalidIp3)).isEqualTo(INVALID_IP_ADDRESS);
    }

    @Test
    void testInsertToTree() {
        final String validIp = "192.100.50.20";
        final String validIpNotAdded = "192.100.50.21";
        treeNode.insert(validIp);

        assertThat(treeNode.search(validIp)).isTrue();
        assertThat(treeNode.search(validIpNotAdded)).isFalse();

    }

}
