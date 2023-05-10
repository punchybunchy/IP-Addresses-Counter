package punchybunchy.code;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import punchybunchy.code.util.DataReader;
import punchybunchy.code.util.IpAddressValidator;
import punchybunchy.code.util.IpAddressGenerator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

        exampleIps.stream()
                .filter(IpAddressValidator::checkIp)
                .forEach(treeNode::insert);
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

        exampleIps.stream()
                .filter(IpAddressValidator::checkIp)
                .forEach(treeNode::insert);

        long actual = treeNode.getIpAddressesCounter();

        assertThat(actual).isEqualTo(numberOfUniqueIps);
    }

    @Test
    void testIpAddressGenerator() {
        final int amountOfIpAddresses = 100;
        final String filePath = "./src/test/resources/ips";
        IpAddressGenerator.generateIpAddressesCollection(amountOfIpAddresses, filePath);
        Path path = Paths.get(filePath).toAbsolutePath().normalize();

        long actualNumberIpsInFile = DataReader.getLineFromFile(filePath).toList().size();


        assertThat(Files.exists(path)).isTrue();
        assertThat(amountOfIpAddresses).isEqualTo(actualNumberIpsInFile);
    }

    @Test
    void testIpAddressValidator() {
        final String validIp = "192.100.50.20";
        final String invalidIp1 = "256.100.288.17";
        final String invalidIp2 = "192.100.50.20.1";
        final String invalidIp3 = "100.50.20";

        assertThat(IpAddressValidator.checkIp(validIp)).isTrue();
        assertThat(IpAddressValidator.checkIp(invalidIp1)).isFalse();
        assertThat(IpAddressValidator.checkIp(invalidIp2)).isFalse();
        assertThat(IpAddressValidator.checkIp(invalidIp3)).isFalse();
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
