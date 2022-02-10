package domain.disciplines

interface RejectDiscipline<T> {
    fun provideReject(request: T, elements: Array<T>) : Array<T>
}