package com.bouraoui.startwars.di

import dagger.Module


@Module(subcomponents = [MovieComponent::class])
class AppSubComponents {
    /**
     * application main subComponent that refer to all the component
     */
}