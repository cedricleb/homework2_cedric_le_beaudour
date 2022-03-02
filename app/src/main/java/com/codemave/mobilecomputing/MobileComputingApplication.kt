package com.codemave.mobilecomputing

import android.app.Application

class MobileComputingApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}