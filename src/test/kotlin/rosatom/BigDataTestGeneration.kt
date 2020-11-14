package rosatom

import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.stringify
import org.junit.Test
import rosatom.web.dto.AnalyseRequestDto
import rosatom.web.dto.DependencyDto
import rosatom.web.dto.SimplifiedJobDto
import java.io.File
import java.lang.Integer.max

class BigDataTestGeneration {

    @ImplicitReflectionSerializer
    @Test
    fun generate() {
        File("src/main/resources/analyse-test/4.json").writeText(Json(JsonConfiguration.Stable)
            .stringify(AnalyseRequestDto(generateJobs(),generateDependencies())))
    }

    private fun generateJobs(): List<SimplifiedJobDto> {
        val jobs = mutableListOf<SimplifiedJobDto>()
        for (i in 1..9900 step 10) {
            jobs.add(SimplifiedJobDto(i+0, "${i+0}", 1, 2))
            jobs.add(SimplifiedJobDto(i+1, "${i+1}", 4, 4))
            jobs.add(SimplifiedJobDto(i+2, "${i+2}", 3, 1))
            jobs.add(SimplifiedJobDto(i+3, "${i+3}", 4, 2))
            jobs.add(SimplifiedJobDto(i+4, "${i+4}", 3, 4))
            jobs.add(SimplifiedJobDto(i+5, "${i+5}", 7, 1))
            jobs.add(SimplifiedJobDto(i+6, "${i+6}", 9, 2))
            jobs.add(SimplifiedJobDto(i+7, "${i+7}", 10, 2))
            jobs.add(SimplifiedJobDto(i+8, "${i+8}", 5, 3))
            jobs.add(SimplifiedJobDto(i+9, "${i+9}", 12, 1))
        }
        for (i in 9901..10000 step 10) {
            jobs.add(SimplifiedJobDto(i+0, "${i + 0}", 1, 2))
            jobs.add(SimplifiedJobDto(i+1, "${i + 1}", 2, 11))
            jobs.add(SimplifiedJobDto(i+2, "${i + 2}", 2, 1))
            jobs.add(SimplifiedJobDto(i+3, "${i + 3}", 4, 4))
            jobs.add(SimplifiedJobDto(i+4, "${i + 4}", 3, 7))
            jobs.add(SimplifiedJobDto(i+5, "${i + 5}", 7, 2))
            jobs.add(SimplifiedJobDto(i+6, "${i + 6}", 9, 3))
            jobs.add(SimplifiedJobDto(i+7, "${i + 7}", 10, 2))
            jobs.add(SimplifiedJobDto(i+8, "${i + 8}", 5, 3))
            jobs.add(SimplifiedJobDto(i+9, "${i + 9}", 11, 1))
        }
        for (i in 1000 until 10001 step 2000) {
            jobs.add(SimplifiedJobDto(10000 + i/1000, "${10000 + i/1000}", 15, 2))
        }
        for (i in 2000 until 10001 step 2000) {
            jobs.add(SimplifiedJobDto(10000 + i/1000, "${10000 + i/1000}", 15, 1))
        }
        jobs.add(SimplifiedJobDto(10011, "10011", 15, 2))
        return jobs
    }

    private fun generateDependencies() : List<DependencyDto> {
        val dependencies = mutableListOf<DependencyDto>()
        for (i in 0..9999 step 10) {
            dependencies.add(DependencyDto(i+1, i+2))
            dependencies.add(DependencyDto(i+1, i+3))
            dependencies.add(DependencyDto(i+1, i+5))
            dependencies.add(DependencyDto(i+2, i+8))
            dependencies.add(DependencyDto(i+3, i+2))
            dependencies.add(DependencyDto(i+3, i+4))
            dependencies.add(DependencyDto(i+3, i+6))
            dependencies.add(DependencyDto(i+3, i+9))
            dependencies.add(DependencyDto(i+4, i+6))
            dependencies.add(DependencyDto(i+5, i+7))
            dependencies.add(DependencyDto(i+6, i+7))
            dependencies.add(DependencyDto(i+6, i+8))
            dependencies.add(DependencyDto(i+7, i+10))
            dependencies.add(DependencyDto(i+9, i+7))
            dependencies.add(DependencyDto(i+8, i+10))
        }
        for (i in 100 until 10000 step 100) {
            dependencies.add(DependencyDto(i, 10000 + i/1000 + 1))
        }
        for (i  in 1..10) {
            dependencies.add(DependencyDto(10000+i, 10011))
        }
        for (i in 9910..9999 step 10) {
            dependencies.add(DependencyDto(i, i + 10))
        }
        return dependencies
    }
}