package com.androidtutz.jegan.didemo;

import dagger.Binds;
import dagger.Module;

@Module
abstract class NCBatteryModule {

    @Binds
    abstract Battery bindNCBattery(NickelCadmiumBattery nickelCadmiumBattery);
}
