package com.example.demo.repo;

import java.util.List;

public interface RepoInterface<T> {

    //Skal hente alle fra en tabel
    List<T> fetchAll();

    //Skal hente 1 objekt med valgte id
    T fetchById(String id);

    //Skal tilføje 1 objekt til databasen af valgte
    void create(T t);

    //Skal fjerne 1 objekt fra databasen med valgte id.
    Boolean deleteById(String id);

    //Skal opdatere databasen med det valgte objekt.
    void update(T t);

}
