package com.cajusoftware.marvelcharacters.ui.components

import android.content.res.Resources.NotFoundException

class CarouselItemNotFound(override val message: String = "Item it not found!") :
    NotFoundException(message)