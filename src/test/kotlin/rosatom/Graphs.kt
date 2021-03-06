package rosatom

object Graphs {
    val simpleGraph = Project(
        mutableMapOf(
            Pair(1,Job(1, "1", 1, 2)),
            Pair(2,Job(2, "2", 1, 2)),
            Pair(3,Job(3, "3", 1, 1)),
            Pair(4,Job(4, "4", 1, 2)),
            Pair(5,Job(5, "5", 3, 2)),
            Pair(6,Job(6, "6", 3, 3)),
            Pair(7,Job(7, "7", 6, 1))
        ),
        listOf(
            1 to 2,
            3 to 4,
            5 to 6
        )
    )

    val validGraph = Project(
        mutableMapOf(
            Pair(1,Job(1, "1", 1, 2)),
            Pair(2,Job(2, "2", 4, 2)),
            Pair(3,Job(3, "3", 4, 2)),
            Pair(4,Job(4, "4", 7, 2)),
            Pair(5,Job(5, "5", 10, 2))
        ),
        listOf(
            1 to 2,
            1 to 3,
            2 to 4,
            3 to 4,
            4 to 5
        )
    )

    val badGraph = Project(
        mutableMapOf(
            Pair(1,Job(1, "1", 1, 2)),
            Pair(2,Job(2, "2", 3, 2)),
            Pair(3,Job(3, "3", 3, 2)),
            Pair(4,Job(4, "4", 1, 2))
        ),
        listOf(
            1 to 2,
            1 to 3,
            2 to 4,
            3 to 4
        )
    )
}