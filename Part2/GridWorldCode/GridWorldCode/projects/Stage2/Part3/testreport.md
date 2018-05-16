#Including five parts test  

##Test1   
```
testJump()
```
Put the jumper in a given location(5,5) and setdirection to NORTH  
Jump the jumper  
Test whether the new location of the jumper the same as the predict location(3,5)  
If not, test fail  


##Test2  
```
testTurn()
```
Put the jumper in the grid with a default direction NORTH  
Turn the jumper  
Test whether the new direction of the jumper the same as the predict direction NORTHEAST  
If not, test fail  


##Test3
```
testCanJumpOutGrid()
```
###testCondition1  
Put the jumper on the side of the grid, location(5,0)  
Set the direction of the jumper to the grid side, direction EAST  
Test whether the jumper canJump  
If can, test fail  

###testCondition2  
Put the jumper on the side of the grid, location(5,1)  
Set the direction of the jumper to the grid side, direction EAST  
Test whether the jumper canJump  
If can, test fail  


##Test4  
```
testCanJumpTo()
```
###testCondition1  
Put the jumper on the location(5,1), direction EAST  
Put the rock on the location(5,3)  
Test whether the jumper canJump  
If can, test fail  

###testCondition2  
Put the jumper on the location(5,1), direction EAST  
Put the flower on the location(5,3)  
Test whether the jumper canJump  
If can't, test fail  

###testCondition3  
Put the jumper on the location(5,1), direction EAST  
Put the bug on the location(5,3)  
Test whether the jumper canJump  
If can, test fail  

###testCondition4  
Put the jumper on the location(5,1), direction EAST  
Put the critter on the location(5,3)  
Test whether the jumper canJump  
If can, test fail  

###testCondition5  
Put the jumper on the location(5,1), direction EAST  
Put the jumper on the location(5,3)  
Test whether the jumper canJump  
If can, test fail  


##Test5  
```
testCanJumpAcross()
```
###testCondition1  
Put the jumper on the location(5,1), direction EAST  
Put the rock on the location(5,2)  
Test whether the jumper canJump  
If can't, test fail  

###testCondition2  
Put the jumper on the location(5,1), direction EAST  
Put the flower on the location(5,2)  
Test whether the jumper canJump  
If can't, test fail  

###testCondition3  
Put the jumper on the location(5,1), direction EAST  
Put the bug on the location(5,2)  
Test whether the jumper canJump  
If can, test fail  

###testCondition4  
Put the jumper on the location(5,1), direction EAST  
Put the critter on the location(5,2)  
Test whether the jumper canJump  
If can, test fail  

###testCondition5  
Put the jumper on the location(5,1), direction EAST  
Put the jumper on the location(5,2)  
Test whether the jumper canJump  
If can, test fail