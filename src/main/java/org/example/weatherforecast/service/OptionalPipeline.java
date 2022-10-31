package org.example.weatherforecast.service;

public interface OptionalPipeline<T, K> {
    T find(K obj);
}
