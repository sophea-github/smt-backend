package Stock.smt.model.custom

class ResponseObjectMap  {

    val responseObject = ResponseObject()

    fun responseObj(obj: Any):MutableMap<String,Any>{
        val res : MutableMap<String,Any> = HashMap()
        res["response"] = Response(200,"Success")
        res["result"] = obj
        return res
    }
    fun responseObject(obj: Any?): MutableMap<String, Any> {
        val response: MutableMap<String, Any> = HashMap()
        if (obj != null) {
            response["data"] = obj
            response["code"] = responseObject.success().code!!
            response["message"] = responseObject.success().message!!
        } else {
            response["code"] = responseObject.error().code!!
            response["message"] = responseObject.error().message!!
        }
        return response
    }
    fun responseOBJ(total:Long,obj: Any):MutableMap<String,Any>
    {
        val response:MutableMap<String,Any> = HashMap()
//        response["response"]= Response(200, "Success")
        response["total"]=total
        response["result"]=obj
        return response
    }
    fun responeMessage(msg: String): MutableMap<String,Any>{
        val res: MutableMap<String,Any> = HashMap()
        res["Message"] =Response(0,msg)
        return res
    }
}