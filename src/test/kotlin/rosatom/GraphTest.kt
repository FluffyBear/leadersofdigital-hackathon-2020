package rosatom

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

class GraphTest {

    @Test
    fun simpleGraph() {
        assertTrue(Graphs.simpleGraph.rating().rating < 1.0)
    }

    @Test
    fun validGraph() {
        assertEquals(1.0, Graphs.validGraph.rating().rating)
    }

    @Test
    fun badGraph() {
        assertTrue(Graphs.badGraph.rating().rating < 0.0)
    }
}