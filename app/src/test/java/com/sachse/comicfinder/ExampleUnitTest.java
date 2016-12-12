package com.sachse.comicfinder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.sachse.comicfinder.BuildConfig.MARVEL_PRIVATE_KEY;
import static junit.framework.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {

    @Before public void setUp() throws Exception {
    }

    @Test public void marvelKeysPresented_fromBuildConfig_shouldBeDifferent() {
        assertTrue(!MARVEL_PRIVATE_KEY.equals(BuildConfig.MARVEL_PUBLIC_KEY));
    }
}