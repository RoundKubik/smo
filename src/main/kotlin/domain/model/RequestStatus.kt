package domain.model

// TODO: 19.11.2021 add documenation
sealed class RequestStatus {

    object Canceled : RequestStatus()
    object InQueue : RequestStatus()
    object Processed : RequestStatus()
    object Generated: RequestStatus()
    object Unknown: RequestStatus()

    override fun toString()  =when(this) {
            is Canceled -> "Отказ"
            is InQueue -> "В очереди"
            is Processed -> "Обрабатывается"
            is Generated -> "Сгенерирован"
            is Unknown -> "Без статуса"
        }
}
