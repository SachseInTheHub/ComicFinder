package com.sachse.comicfinder.io;

import com.sachse.comicfinder.model.Character;

import io.realm.Realm;
import rx.Observable;
import rx.subjects.BehaviorSubject;

public class FileStorage {

    private BehaviorSubject<Character> dataSubject = BehaviorSubject.create();

    public FileStorage() {
    }

    public void storeCharacter(final Character character) {
        Realm defaultInstance = Realm.getDefaultInstance();
        defaultInstance.executeTransaction(realmTransaction -> {
            Character characterRealm = realmTransaction.createObject(Character.class);
            characterRealm.setName(character.getName().toLowerCase());
            characterRealm.setThumbnailResourcePath(character.getThumbnail().getResourcePath());
            characterRealm.setDescription(character.getDescription());

            dataSubject.onNext(characterRealm);
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

    public Observable<Character> onDataChanged() {
        return dataSubject.filter(character -> character != null);
    }
}
