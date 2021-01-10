package com.kostakuu.moviestar.contract.service;

import java.util.List;

public interface BaseServiceInterface<T, G> {
    List<G> getAll();
    G findById(int id);
    G save(T t);
    G deleteById(int id);
}
