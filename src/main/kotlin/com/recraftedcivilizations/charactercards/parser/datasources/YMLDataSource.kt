package com.recraftedcivilizations.charactercards.parser.datasources

import com.recraftedcivilizations.charactercards.cards.Card
import com.recraftedcivilizations.charactercards.cards.CharacterCard
import org.bukkit.OfflinePlayer
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File


class YMLDataSource(override val dataURI: String) : IParseData {
   private val dataFile: YamlConfiguration = YamlConfiguration.loadConfiguration(File(dataURI))

    override fun getCard(player: OfflinePlayer, fields: List<String>): CharacterCard {
        val valueMap = mapOf<String, String?>().toMutableMap()
        val cardSection = dataFile.getConfigurationSection("cards.${player.uniqueId}") ?: return CharacterCard(fields, player)

        for(key in cardSection.getKeys(false)){
            valueMap[key] = cardSection.getString(key)
        }

        return CharacterCard(fields, valueMap, player)
    }

    override fun setCard(card: Card) {
        val cardSection = dataFile.createSection("cards.${card.owner}", card.valueMap)
        dataFile.save(File(dataURI))

    }
}