# Wildlife of the Plain
 Wildlife simulator

1. Problem Description  
This project simulates interactions among different forms of life in a plain. The plain is represented by an 𝑁×𝑁 grid that changes over a number of cycles. Within a cycle, each square is occupied by one of the following five life forms:  
Badger (B), Fox (F), Rabbit (R), Grass (G), and Empty (E)  
An Empty square means that it is not occupied by any life form. Below is a plain example as a 6 × 6 grid.  
F5 E E F0 E E  
B3 F1 B0 R0 G R0  
R0 E R2 B0 B2 G  
B0 E E R1 F0 E  
B1 E E G E R0  
G G E B0 R2 E  
Both row and column indices start from 0. In the example, the (1, 1)th square is occupied by a 1-year-old Fox. It has a 3 × 3 neighborhood centered at the square:  
F5 E E  
B3 F1 B0  
R0 E R2  
The (0, 0)th square F5 (a 5-year-old Fox) has only a 2 × 2 neighborhood:  
F5 E  
B3 F1  
Meanwhile, the (2, 0)th square R0 (a newborn Rabbit) has a 3 × 2 neighborhood:  
B3 F1  
R0 E  
B0 E  
Generally, the neighborhood of a square is a "3 × 3" grid which includes only those squares lying within the intersection of the plain with a 3 × 3 window centered at the square. When a square is on the border, the dimension of its neighborhood reduces to 2 × 3, 3 × 2, or 2 × 2.  
  
2. Survival Rules
The plain evolves from one cycle to the next one. In the next cycle, the life form to reside on a square is decided from those life forms in the current cycle who live in the 3 × 3 neighborhood centered at the square, under a set of survival rules. These rules are specified according to the life form residing on the same square in the current cycle. Badgers, foxes, and rabbits start at age 0, and grow one year older when the next cycle starts.  
  
2.1 Badger  
A badger dies of old age or hunger, or from a group attack by foxes when it is alone. The life form on a Badger square in the next cycle will be  
a) Empty if the Badger is currently at age 4;  
b) otherwise, Fox, if there is only one Badger but there are more than one Fox in the neighborhood;  
c) otherwise, Empty, if Badgers and Foxes together outnumber Rabbits in the neighborhood;  
d) otherwise, Badger (the badger will live on).  
The new life form taking over the square, if a Fox, will have age 0 when the next cycle starts.  
For example, in the following neighborhood of a Badger at age 2:  
R0 G R0  
B0 B2 G  
R1 F0 E  
there are two Badgers (including self), one Fox, three Rabbits. Going down the rule list, neither a), b), c) applies. According to rule d), the central element (square) will still be B (Badger) --- just one year older --- in the next cycle. In other words, B2 will be replaced with B3.  
  
2.2 Fox
A fox dies of old age, hunger, or an attack by more numerous badgers. The life form on a Fox square in the next cycle will be  
a) Empty if the Fox is currently at age 6;  
b) otherwise, Badger, if there are more Badgers than Foxes in the neighborhood;  
c) otherwise, Empty, if Badgers and Foxes together outnumber Rabbits in the neighborhood;  
d) otherwise, Fox (the fox will live on).  
The new life form, if a Badger, will have age 0 when the next cycle begins.
For example, in the following neighborhood of a Fox at age 1:  
F5 E E  
B3 F1 B0  
R0 E R2  
there are two Foxes, two Badgers, and two Rabbits. Rule c) applies. The central square will become E in the next cycle.  
  
2.3 Rabbit  
A rabbit dies of old age or hunger. It may also be eaten by a badger or a fox. More specifically, the life form on a Rabbit square in the next cycle will be  
a) Empty if the Rabbit's current age is 3;  
b) otherwise, Empty if there is no Grass in the neighborhood (the rabbit needs food);  
c) otherwise, Fox if in the neighborhood there are at least as many Foxes and Badgers  
combined as Rabbits, and furthermore, if there are more Foxes than Badgers;
d) otherwise, Badger if there are more Badgers than Rabbits in the neighborhood;  
e) otherwise, Rabbit (the rabbit will live on).  
If the new life form is a Badger or Fox, it will have age 0 when the next cycle starts.  
In the following neighborhood of a rabbit at age 2:  
F1 B0 R0  
E R2 B0  
E E R1  
lives two Badgers, one Fox, and three Rabbits. Rule a) does not apply because the Rabbit is only 2-years old. Rule b) does since there is no Grass in the neighborhood. The central element (square) will be E in the next cycle according to this rule.  
2.4 Grass  

Grass may be eaten out by overcrowded rabbits. Rabbits may also multiply fast enough to take over the Grass square. In the next cycle, the life form on a Grass square will be  
a) Empty if at least three times as many Rabbits as Grasses in the neighborhood;  
b) otherwise, Rabbit if there are at least three Rabbits in the neighborhood;  
c) otherwise, Grass.  
If the new life form is a Rabbit, it will be aged 0 when the next cycle starts.
For example, if the neighborhood of a Grass is  
F0 E E
R0 G R0
B0 B2 G
the central element will be G in the next cycle under rule c).  
  
2.5 Empty  

Empty squares are competed by other life forms. More specifically, the life form on an Empty square in the next cycle will be  
a) Rabbit, if more than one neighboring Rabbit;  
b) otherwise, Fox, if more than one neighboring Fox;  
c) otherwise, Badger, if more than one neighboring Badger;  
d) otherwise, Grass, if at least one neighboring Grass;  
e) otherwise, Empty.  
If the new life form is a Badger, Fox, or Rabbit, it will have age 0 when the next cycle begins. For example, an Empty square in the top row has the following neighborhood:  
F0 E E  
R0 G R0  
which includes two Rabbits. Thus, rule a) applies to change the central element to R0 in the next cycle.  