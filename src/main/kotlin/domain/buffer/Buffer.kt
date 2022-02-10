package domain.buffer

// TODO: 23.11.2021
interface Buffer<T> {
    fun get(): T
    fun update(requests: Array<T>)
    fun getElements() : Array<T>
}