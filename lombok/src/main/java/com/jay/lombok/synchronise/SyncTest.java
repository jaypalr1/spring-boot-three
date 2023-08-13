package com.jay.lombok.synchronise;

import lombok.Synchronized;

/*
When Java’s synchronized keyword is applied on the method level,
it synchronizes a particular method using the 'this' reference. Using synchronized this way may be convenient,
but nothing prevents users of your class from acquiring the same lock and shooting themselves in the
foot by messing your carefully designed locking strategy up.

The common pattern to prevent that from happening is to create a private field specifically for locks and
synchronize on the lock object instead:

````
public class Foo {

    private final Object lock = new Object();

    public void foo() {
        synchronized(lock) {
            // ...
        }
    }

}
````
But this way, you cannot use the synchronized keyword on the method level,
and this does not make your code clearer.

For this case, Lombok provides the @Synchronized annotation. It can be used similarly to the synchronized keyword,
but instead of using the 'this' reference, it creates a private field and synchronizes on it:
````
public class Foo {

    // synchronized on a generated private field
    @Synchronized
    public void foo() {
        // ...
    }

}
````
If you need to synchronize different methods on different locks, you can provide a name of a lock object to the
@Synchronized annotation but in this case you need to define locks yourself:
````
public class Foo {

    // lock to synchronize on
    private Object barLock = new Object();

    @Synchronized("barLock")
    public void bar() {
        // ...
    }

}
````

In this case, Lombok will synchronize 'bar' method on the 'barLock' object.

*/

public class SyncTest {

  private final Object lock = new Object();

  @Synchronized
  public void foo() {

  }
}
