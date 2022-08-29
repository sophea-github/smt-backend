package Stock.smt.model.Custom

class ResponseObjectMap  {
    fun ResponseObj(obj: Any):MutableMap<String,Any>{
        var res : MutableMap<String,Any> = HashMap()
        res["response"] = Response(200,"Success")
        res["result"] = obj
        return res
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
        var res: MutableMap<String,Any> = HashMap()
        res["Message"] =Response(0,msg)
        return res
    }
}