package com.example.home_data.remote.mapper

import com.example.home_data.local.CharacterHomeEntity
import com.example.home_domain.model.CharacterHomeUiModel
import com.example.util.Mapper

interface CharactersHomeDatabaseToCharactersHomeUiMapper :
    Mapper<List<CharacterHomeEntity>, List<CharacterHomeUiModel>>