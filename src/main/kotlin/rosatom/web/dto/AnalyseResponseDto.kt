package rosatom.web.dto

import rosatom.Job

data class AnalyseResponseDto(
    val averageScore: Double,
    val notes: Map<Job, ScoreDto>
)

data class ScoreDto(
    val score: Double
)