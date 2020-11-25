package com.recraftedcivilizations.charactercards.parser

import com.recraftedcivilizations.charactercards.cards.Card
import com.recraftedcivilizations.charactercards.cards.CharacterCard
import com.recraftedcivilizations.charactercards.utils.SupportedTypes
import org.bukkit.entity.Player
import java.io.ObjectOutputStream

class DataParser {


    fun getCard(player: Player): CharacterCard? {
        return CharacterCard(mapOf(Pair("Foo", SupportedTypes.STRING)), mapOf(Pair("Foo", "Bar")), player)
    }

    fun setCard(player: Player, card: Card) {
    }
}