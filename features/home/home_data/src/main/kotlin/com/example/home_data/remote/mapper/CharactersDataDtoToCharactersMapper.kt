package com.example.home_data.remote.mapper

import com.example.home_data.remote.dto.CharactersDataDto
import com.example.home_domain.model.Character
import com.example.util.Mapper

interface CharactersDataDtoToCharactersMapper: Mapper<CharactersDataDto, List<Character>>