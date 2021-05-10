package com.omaks.sample.kotlin150

interface State
interface Storable
interface PersistableState<T> : State, Storable
