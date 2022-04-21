package com.br.leandro.marvel_hero_app.core.di.key

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class ViewModelKey(val value: KClass<out ViewModel>)
