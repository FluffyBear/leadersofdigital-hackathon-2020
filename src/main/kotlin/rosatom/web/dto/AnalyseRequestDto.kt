package rosatom.web.dto

import rosatom.Job

data class AnalyseRequestDto(
    val jobs: List<Job>,
    val adjustments: List<Job>?
)