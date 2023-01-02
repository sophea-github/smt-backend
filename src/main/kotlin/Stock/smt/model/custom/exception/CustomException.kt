package Stock.smt.model.custom.exception

class CustomException(statusCode: Int, message: String?) : RuntimeException(message) {
    var statusCode: Int = 0

    init {
        this.statusCode = statusCode
    }
}