package Stock.smt.model.Custom.Exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class CustomNotFoundException(msg: String): RuntimeException(msg)