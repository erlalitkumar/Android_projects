package com.lkb.baseandroidproject;

import dagger.Component;

@Component
interface BattleComponent {
    War getWar();

    Starks getStarks();

    Boltons getBoltaons();
}
