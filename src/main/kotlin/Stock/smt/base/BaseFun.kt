package Stock.smt.base

import org.springframework.data.domain.Page

interface BaseFun<T,D> {
    fun pagination(q: String?, page: Int, size: Int): Page<T>
    fun findAll(): List<T>?
    fun saveAll(t: T): T?
    fun updateObj(id: Int, t: T): T?
    fun delete(id: Int)
}