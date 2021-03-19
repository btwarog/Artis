package pl.btwarog.brainz.domain.model

data class PaginatedList<T>(val results: List<T>?, val hasNextPage: Boolean, val nextPageCursor: String?)