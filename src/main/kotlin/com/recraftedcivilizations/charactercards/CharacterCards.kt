package com.recraftedcivilizations.charactercards

import com.github.darkvanityoflight.recraftedcore.ARecraftedPlugin
import com.recraftedcivilizations.charactercards.parser.ConfigParser
import com.recraftedcivilizations.charactercards.parser.DataParser
import com.recraftedcivilizations.charactercards.parser.datasources.IParseData
import com.recraftedcivilizations.charactercards.parser.datasources.SQLDataSource
import com.recraftedcivilizations.charactercards.parser.datasources.YMLDataSource

class CharacterCards : ARecraftedPlugin() {
    val configParser : ConfigParser = ConfigParser(config)
    lateinit var dataParser : IParseData

    override fun onEnable() {
        super.onEnable()
        saveDefaultConfig()
        instance = this

        configParser.read()

        dataParser = when (configParser.dataSource) {
            "YML" -> {
                YMLDataSource(this.dataFolder.absolutePath + "data.yml")
            }
            "SQL" -> {
                severe("Sql datasources are not supported yet, switching to YML")
                YMLDataSource(this.dataFolder.absolutePath + "data.yml")
            }
            else -> {
                severe("An internal error occurred while choosing the datasource, please contact your dev")
                YMLDataSource(this.dataFolder.absolutePath + "data.yml")
            }
        }
    }

    companion object{
        var instance : CharacterCards? = null
    }
}