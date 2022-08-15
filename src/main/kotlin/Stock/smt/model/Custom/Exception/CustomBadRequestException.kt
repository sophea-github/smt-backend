package Stock.smt.model.Custom.Exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class CustomBadRequestException(msg: String): RuntimeException(msg)