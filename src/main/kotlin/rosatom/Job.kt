package rosatom

import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.math.sign

data class Job(
    val id: Int,
    val name: String,
    val function: String,
    val startDate: Int,
    val costDateShiftEarly: Int?,
    val costDateShiftLater: Int?,
    val normalDuration: Int,
    val minimumDuration: Int?,
    val costReduceDurationFromNormalToMinimum: Int?
) {
    val dependentJobs = mutableListOf<Job>()
    val dependsOnJobs = mutableListOf<Job>()

    constructor(id: Int, name: String, startDate: Int, normalDuration: Int) : this(
        id = id,
        name = name,
        function = "",
        startDate = startDate,
        costDateShiftEarly = 0,
        costDateShiftLater = 0,
        normalDuration = normalDuration,
        minimumDuration = 0,
        costReduceDurationFromNormalToMinimum = 0
    )

    val endDate: Int = startDate + normalDuration

    val gapBefore: Int by lazy { dependsOnJobs.map { it.endDate }.max()?.let { startDate - it } ?: normalDuration }

    val gapAfter: Int by lazy { dependentJobs.map { it.startDate }.min()?.let { it - endDate } ?: normalDuration }

    val rating: Double by lazy {
        println("$name > $gapBefore > $gapAfter")
        val gap = gapBefore.sign * max(gapBefore.absoluteValue, normalDuration) / 2.0 +
                gapAfter.sign * max(gapAfter.absoluteValue, normalDuration) / 2.0
        gap / normalDuration
    }
}