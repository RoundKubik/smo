package data.disciplines

import domain.disciplines.RejectDiscipline
import domain.model.Request
import kotlin.math.max

/**
 * Описание дисциплины отказа
 * К примеру 3 источника
 * 1. Наименьший приоритет заявки, прибор которой имеет самый старший номер(3), если она одна заменяем на поступившую, пишем отказ
 * 2. Если несколько заявок с номером источника 3, то выкидываем ту, у которой самый маленький номер генерации
 *
 *
 *
 */
// TODO: 23.11.2021 add documentation 
class RejectDisciplineBySourcePriority : RejectDiscipline<Request> {

    companion object {
        private const val MIN_SIZE_FOR_SINGLE_REJECT = 1
    }

    /**
     * Not effective algorithm also provide reject result
     */
    override fun provideReject(request: Request, elements: Array<Request>): Array<Request> {
        if (elements.isEmpty()) {
            return elements
        }
        elements.forEach {
            if (it == Request()) {
                return elements
            }
        }
        if (elements.size == MIN_SIZE_FOR_SINGLE_REJECT) {
            if ((elements[0].sourceNum > request.sourceNum) or
                ((elements[0].sourceNum == request.sourceNum) and (request.requestNum > elements[0].requestNum))
            ) {
                elements[0] = request
            }
            return elements
        }

        var minPriority = elements[0].sourceNum
        elements.forEach {
            minPriority = max(minPriority, it.sourceNum)
        }

        if (request.sourceNum > minPriority) {
            return elements
        }
        val rejectIndex = elements.indexOf(
            elements.filter {
                it.sourceNum == minPriority
            }.sortedWith(
                compareBy {
                    it.requestNum
                }
            )[0]
        )
        elements[rejectIndex] = request

        return elements
    }
}