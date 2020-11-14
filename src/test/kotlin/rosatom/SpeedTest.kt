package rosatom

import org.junit.Test
import rosatom.web.dto.AnalyseRequestDto
import rosatom.web.dto.DependencyDto
import rosatom.web.dto.SimplifiedJobDto
import kotlin.system.measureTimeMillis

class SpeedTest {

    @Test
    fun speed100K() {
        val test = generate(nodeNumbers = 100000)
        println(measureTimeMillis{Project(test).rating().criticalJobs})
    }

    @Test
    fun speed1M() {
        val test = generate(nodeNumbers = 1000000)
        println(measureTimeMillis{Project(test).rating().criticalJobs})
    }

    @Test
    fun speed10M() {
        val test = generate(nodeNumbers = 10000000)
        println(measureTimeMillis{Project(test).rating().criticalJobs})
    }

    private fun generate(nodeNumbers: Int) : AnalyseRequestDto {
        return AnalyseRequestDto(generateJobs(nodeNumbers), generateDependencies(nodeNumbers))
    }

    private fun generateJobs(nodeNumbers: Int): List<SimplifiedJobDto> {
        val jobs = mutableListOf<SimplifiedJobDto>()
        for (i in 1..nodeNumbers - 100 step 10) {
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
        for (i in nodeNumbers-99..nodeNumbers step 10) {
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
        for (i in nodeNumbers/10 until nodeNumbers+1 step (2 * nodeNumbers/10)) {
            jobs.add(SimplifiedJobDto(nodeNumbers + i/(nodeNumbers/10), "${nodeNumbers + i/(nodeNumbers/10)}", 15, 2))
        }
        for (i in 2*(nodeNumbers/10) until nodeNumbers+1 step 2*(nodeNumbers/10)) {
            jobs.add(SimplifiedJobDto(nodeNumbers + i/(nodeNumbers/10), "${nodeNumbers + i/(nodeNumbers/10)}", 15, 1))
        }
        jobs.add(SimplifiedJobDto(nodeNumbers+11, "${nodeNumbers+11}", 15, 2))
        println("JOBS READY")
        return jobs
    }

    private fun generateDependencies(nodeNumbers: Int) : List<DependencyDto> {
        val dependencies = mutableListOf<DependencyDto>()
        for (i in 0 until nodeNumbers step 10) {
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
        for (i in nodeNumbers/100 until nodeNumbers step nodeNumbers/100) {
            dependencies.add(DependencyDto(i, nodeNumbers + i/(nodeNumbers/10) + 1))
        }
        for (i  in 1..10) {
            dependencies.add(DependencyDto(nodeNumbers+i, nodeNumbers+11))
        }
        for (i in nodeNumbers-90 until nodeNumbers step 10) {
            dependencies.add(DependencyDto(i, i + 10))
        }
        println("DEPENDENCIES READY")
        return dependencies
    }
}