Defines how many steps a BoxBug can move on each side of the box.  
// @file: projects/boxBug/BoxBug.java  
// @line: 45  
```
if (steps < sideLength && canMove())
```

Track how many steps a BoxBug has moved on each side.  
// @file: projects/boxBug/BoxBug.java  
// @line: 47~48  
```
move();
steps++;
```

Because the turn method turning 45 degrees one time, when its steps becomes equal to sideLength, it needs to turn 90 degrees to the next side of the box.  
// @file: projects/boxBug/BoxBug.java  
// @line: 52~54  
```
turn();
turn();
steps = 0;
```
// @file: framework/info/gridworld/actor/Bug.java  
// @line: 59~65  
```
/**
  * Turns the bug 45 degrees to the right without changing its location.
  */
public void turn()
{
    setDirection(getDirection() + Location.HALF_RIGHT);
}
```

Because the BoxBug class extends the Bug class, that means the BoxBug class is a subclass of the Bug class. And the Bug class have the public move method, so the BoxBug class can call the move method.  
// @file: projects/boxBug/BoxBug.java  
// @line: 25  
```
public class BoxBug extends Bug
```
  
// @file: framework/info/gridworld/actor/Bug.java  
// @line: 71  
```
public void move()
```

Yes, once a BoxBug is constructed, its sideLength is determined and cannot be changed.  
// @file: projects/boxBug/BoxBug.java  
// @line: 34~38  
```
public BoxBug(int length)
{
    steps = 0;
    sideLength = length;
}
```

Yes, if another Actor is in front of the BoxBug when it tries to move, the BoxBug will turn and then the path changes.  
// @file: projects/boxBug/BoxBug.java  
// @line: 45, 50~55  
```
if (steps < sideLength && canMove())...
else
{
    turn();
    turn();
    steps = 0;
}
```

When the BoxBug is constructed and when its steps equals to its sideLength that means it turn into a new side of its box.  
// @file: projects/boxBug/BoxBug.java  
// @line: 34~38, 45,50~55  
```
public BoxBug(int length)
{
    steps = 0;
    sideLength = length;
}
if (steps < sideLength && canMove())...
else
{
    turn();
    turn();
    steps = 0;
}
```