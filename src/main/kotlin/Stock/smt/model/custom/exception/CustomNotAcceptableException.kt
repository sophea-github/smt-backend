package Stock.smt.model.custom.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
class CustomNotAcceptableException(message: String):RuntimeException(message)