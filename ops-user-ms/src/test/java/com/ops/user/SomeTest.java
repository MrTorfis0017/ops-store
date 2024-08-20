package com.ops.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SomeTest {

    @Test
    public void sayHelloWorld() {
        String str = "Hello world !";
        assertEquals("Hello world !", str);
    }
}
