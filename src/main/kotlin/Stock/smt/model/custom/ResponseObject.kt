package Stock.smt.model.custom

import org.springframework.stereotype.Component
import java.io.Serializable

@Component
class ResponseObject(var code: Int? = 0, var message: String? = null) : Serializable {

    fun success(): ResponseObject {
        val code = 200
        val message = "Success"
        return ResponseObject(code, message)
    }

    fun error(): ResponseObject {
        val code = 404
        val message = "Error"
        return ResponseObject(code, message)
    }
}