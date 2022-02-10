package domain.model

// TODO: 23.11.2021
sealed class BufferingResult<T> {
    data class Success<T>(val data: Array<T>) : BufferingResult<T>() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Success<*>

            if (!data.contentEquals(other.data)) return false

            return true
        }

        override fun hashCode(): Int {
            return data.contentHashCode()
        }


    }

    data class OverFlow<T>(val data: T) : BufferingResult<T>()
}

