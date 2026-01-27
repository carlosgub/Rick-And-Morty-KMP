package org.carlosgub.yt.rick.and.morty.domain.model

data class CharacterPaging(
    val results: List<Character>,
    val canLoadMore: Boolean
)
