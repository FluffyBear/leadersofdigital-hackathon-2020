package rosatom.web.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import rosatom.Job

@Serializable
data class CompareRequestDto(
    val originalProject: AnalyseRequestDto,
    val adjustedJobs: List<SimplifiedJobDto>
)

@Serializable
data class AnalyseRequestDto(
    val jobs: List<SimplifiedJobDto>,
    val dependencies: List<DependencyDto>
) {
    companion object {
        private val json = Json(JsonConfiguration.Stable)

        fun parse(string: String) = json.parse(serializer(), string)
    }
}

@Serializable
data class SimplifiedJobDto(
    val id: Int,
    val name: String,
    val startDate: Int,
    val normalDuration: Int
) {
    fun toJob() = Job(id, name, startDate, normalDuration)
}

@Serializable
data class DependencyDto(
    val from: Int,
    val to: Int
)