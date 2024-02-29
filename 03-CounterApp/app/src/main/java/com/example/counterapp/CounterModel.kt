package com.example.counterapp

data class CounterModel(var count: Int)

// The data is managed in the repository
class CounterRepository {
    private var _counter = CounterModel(0)

    fun getCounter() = _counter

    fun incrementCounter(){
        _counter.count++
    }

    fun decrementCounter(){
        _counter.count--
    }
}