package de.kiemle.notes.model;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public record User (String name){

    private static final UUID uuid = UUID.randomUUID();

    public UUID uuid() {
        return uuid;
    }
}
