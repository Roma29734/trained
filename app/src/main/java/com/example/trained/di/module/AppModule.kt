package com.example.trained.di.module

import dagger.Module

@Module(
    includes = [
        ViewModelModule::class,
        DomainBaseModule::class,
        LocalBaseModule::class,
    ]
)
class AppModule