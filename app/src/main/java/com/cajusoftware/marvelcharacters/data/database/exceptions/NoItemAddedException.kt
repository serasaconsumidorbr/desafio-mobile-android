package com.cajusoftware.marvelcharacters.data.database.exceptions

import android.content.res.Resources.NotFoundException

class NoItemAddedException(
    override val message: String = "No item found!"
) : NotFoundException(message)