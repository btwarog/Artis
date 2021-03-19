package pl.btwarog.core.domain.mappers

interface RemoteMapper<in M, out E> {

	fun mapFromRemote(remote: M): E
}