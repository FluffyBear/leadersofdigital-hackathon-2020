package rosatom

class Graph(
    val jobs: List<Job>,
    val dependentJobs: Map<Int, List<Int>>,
    val dependsOnJobs: Map<Int, List<Int>>
) {
    constructor(jobs: List<Job>, dependencies: Map<Int, List<Int>>)
}