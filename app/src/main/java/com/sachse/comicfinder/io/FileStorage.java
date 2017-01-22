package com.sachse.comicfinder.io;

import com.sachse.comicfinder.api.models.Image;
import com.sachse.comicfinder.api.models.Powers;
import com.sachse.comicfinder.model.Character;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import rx.Observable;
import rx.subjects.BehaviorSubject;

public class FileStorage {

    private BehaviorSubject<Character> dataSubject = BehaviorSubject.create();

    public FileStorage() {
    }

    public void storeCharacter(final Character character) {
        Realm defaultInstance = Realm.getDefaultInstance();
        defaultInstance.executeTransaction(realmTransaction -> {
            Character realmCharacter = realmTransaction.createObject(Character.class);
            realmCharacter.setName(character.getName());
            realmCharacter.setBirth(character.getBirth());
            realmCharacter.setAliases(character.getAliases());

            Image image = realmTransaction.copyToRealm(character.getImage());
            realmCharacter.setImage(image);
            realmCharacter.setImageMediumUrl(image.getMediumUrl());

            RealmList<Powers> realmPowers = new RealmList<>();
            for (Powers power : character.getPowers()) {
                realmPowers.add(realmTransaction.copyToRealm(power));
            }
            realmCharacter.setPowers(realmPowers);

            dataSubject.onNext(realmCharacter);
        });
    }

    public Character getCharacter(final String characterName) {
        Character character = Realm.getDefaultInstance()
                .where(Character.class)
                .equalTo("name", characterName)
                .findFirst();

        if (character != null) {
            dataSubject.onNext(character);
        }

        return character;
    }

    public List<Character> getAllCharacters() {
        RealmResults<Character> characterRealmResults = Realm.getDefaultInstance().where(Character.class).findAll();
        List<Character> characters = new ArrayList<>();
        characters.addAll(characterRealmResults);
        return characters;
    }

    public Observable<Character> onDataChanged() {
        return dataSubject.filter(character -> character != null);
    }
}
