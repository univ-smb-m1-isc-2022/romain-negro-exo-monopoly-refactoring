package net.negro.romain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GobletTest {

    private static Stream<Arguments> enumerateDesPossibles() {
        int[] val = {1,2,3,4,5,6};
        List<Arguments> args = new ArrayList<>();
        for (int v: val) {
            for (int v2 : val) {
                args.add(Arguments.of(v, v2));
            }
        }
        return args.stream();
    }
    @ParameterizedTest
    @MethodSource("enumerateDesPossibles")
    void estUnDouble(int x, int y) {
        Goblet g = new Goblet(new De[]{new DeMock(x), new DeMock(y)});
        g.lancer();
        assertTrue(g.estUnDouble() == (x == y));
    }

    @ParameterizedTest
    @MethodSource("enumerateDesPossibles")
    void valeurDes(int x, int y) {
        Goblet g = new Goblet(new De[]{new DeMock(x), new DeMock(y)});
        g.lancer();
        assertTrue(g.valeurDes() == x+y);
        // test
    }
}