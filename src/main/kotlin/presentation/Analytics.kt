package presentation

object Analytics {
    var generatedTasksCount = 0
    var rejectedTasksCount = 0
    var tasksTotalTime: Long = 0
    var taskTotalSquaredTime: Long = 0
    var bufferedTime: Long = 0
    var bufferedSquaredTime: Long = 0


    var deviceRequestCount  = Array(Config.SOURCES_COUNT + 1) {
        0
    }
}