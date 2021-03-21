package pl.btwarog.core.domain.mappers

interface RemoteMapper<in M, out E> {

	fun mapFromRemote(remote: M): E
}

interface CacheMapper<M, E> {

	fun mapFromCache(cache: M): E

	fun mapToCache(domain: E): M
}