package rosatom

import java.time.ZonedDateTime

class Job (
    val id: Int,
    val name: String,
    val function: FunctionType,
    val startDate: Int,
    val costDateShiftEarly: Int,
    val costDateShiftLater: Int,
    val normalDuration: Int,
    val minimumDuration: Int,
    val costReduceDurationFromNormalToMinimum: Int,
    val dependentJobs: List<Int>
) {
    constructor(id: Int, name: String, startDate: Int, normalDuration: Int, dependentJobs: List<Int>) : this(
        id = id,
        name = name,
        function = FunctionType(),
        startDate = startDate,
        costDateShiftEarly = 0,
        costDateShiftLater = 0,
        normalDuration = normalDuration,
        minimumDuration = 0,
        costReduceDurationFromNormalToMinimum = 0,
        dependentJobs = dependentJobs
    )
}