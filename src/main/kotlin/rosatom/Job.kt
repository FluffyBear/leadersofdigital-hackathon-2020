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
    val dependentJobs: List<Job>
)