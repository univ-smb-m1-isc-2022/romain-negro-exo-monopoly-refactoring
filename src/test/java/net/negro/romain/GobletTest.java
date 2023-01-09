package net.negro.romain;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

class GobletTest {
    @Test
    void douzeEstUnDouble() {
        while (Goblet.lancer() != 12) {}
        if (Goblet.lancer() == 12)
            assertTrue(Goblet.estUnDouble());
    }
}