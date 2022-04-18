package com.example.home_data.remote.mapper

import com.example.home_data.remote.dto.CharacterDto
import com.example.home_domain.model.Character
import com.example.util.Mapper

interface CharacterDtoToCharacterMapper : Mapper<CharacterDto, Character>