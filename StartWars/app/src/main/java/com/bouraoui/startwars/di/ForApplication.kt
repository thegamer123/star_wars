package com.bouraoui.startwars.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ForApplication {
    /**
     * Annotation to separate the component from each other
     */
}