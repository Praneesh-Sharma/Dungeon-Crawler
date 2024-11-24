public interface Engine {
    public void update();
}


//No, an interface cannot be instantiated directly. Interfaces are abstract types and cannot have a concrete implementation by themselves.
//A class can implement the Engine interface if it provides a concrete implementation of the update() method defined in the interface.
//Yes, in Java, a class can implement multiple interfaces. This is a way to achieve multiple inheritance-like behavior in Java.