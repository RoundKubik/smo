package data.disciplines

import domain.disciplines.ChooseRequestDiscipline
import domain.model.ChooseRequestResult
import domain.model.Request
import kotlin.math.min

// FIXME: 25.11.2021 can be error with 2147483647 devices
class ChooseRequestDisciplineImpl : ChooseRequestDiscipline<Request> {

    companion object {
        private const val NO_PRIORITY = Int.MAX_VALUE
    }

    private var lastRequestPriority = NO_PRIORITY
    private var lastPackage = arrayListOf<Request>()

    override fun choose(requests: Array<Request>): ChooseRequestResult<Request> {
        if (lastPackage.isNotEmpty()) {
            return ChooseRequestResult.Success(lastPackage.removeFirst())
        }
        requests.forEach {
            if (it.sourceNum != -1) {
                lastRequestPriority = min(lastRequestPriority, it.sourceNum)

            }
        }
        requests.forEach {
                if ((it.sourceNum != -1) and (it.sourceNum == lastRequestPriority)) {
                    lastPackage.add(it)
                }
        }
        lastRequestPriority = NO_PRIORITY
        return if (lastPackage.isEmpty()) {
            ChooseRequestResult.Success(Request())
        } else {
            ChooseRequestResult.Success(lastPackage.removeFirst())
        }
    }
}