package org.carlosgub.yt.rick.and.morty.domain.model

data class CharacterPaging(
    val characters: List<Character>,
    val canLoadMore: Boolean,
)
