the problem statement is as follows:  Let X be a minecraft world. This world contains n in N lapis blocks and m in N gold blocks. Lapis blocks indicate where a water source is allowed to be placed. Is there an assignment/placement of water sources, such that once the water has maximally spread and no further changes occur that all gold blocks are touched by water? (excluding redstonr and stuff that can create loops)

X, the input, is a 3D world map with block data for every coordinate within some finite bounding box
therefore, X has size x_range*y_range*z_range*k, I.e. the volume of the bounding box times some constant encoding size per block

In a "static" world, water can never move upwards and can only flow up to 7(?) blocks horizontally. Therefore, the worst case scenario for computing the outcome given some assignment is when water is placed at the top of the bounding box and has to flow to the bottom. This requires up to 7*y_range ticks to compute. (going down in steps)

When there are water-breakable blocks in the world, the flow of water can affect the "map geometry" in a limited way. Specifically, only blocks with the same x and z coordinates can be affected. (in the same column. I believe this is true)

For a world with a single breakable block, the computation of the water flow for a given assignment can be split into before-break and after-break segments. The before-break segment uses the initial world state to calculate the water flow up to the point where a breakable block is destroyed. Then the effect of the breakage is computed. Finally, the after-break segment starts, taking the updated world state as input.

Looking at the breakable blocks in this way, we can see that for a in N breakable blocks that are actually hit, we can calculate the ticks needed to calculate the final output of an assignment as: water-flow + sum_{i=1}^a (break-change + water-flow)

the worst-case for a break-change would be a stack of sand blocks, filling an entire y column, being triggered and falling into the void. this would be 2*y_range (?) ticks.

By replacing the terms in the sum with the individual worst-case runtimes (in ticks/steps) we get: 7*y_range*(b+1) + 2*y_range*b
where b in N is the number of breakable blocks in X.

The block changes in minecraft are local(?) so every block considers only some surrounding blocks when computing it's state in the next tick. Therefore, a tick needs at most x_range*y_range*z_range*poly(neighbours) (?) calculations.

As we can see, the number of computations needed to calculate the output for a given assignment scales only polynomially in the size of X. Therefore, simply loading up a world X with a given assignment y in Minecraft and letting the water physics play out is a valid verification procedure. As such, the stated problem above is in NP.

(explain world generator here)
for the construction of a world for a given CNF formula we have three general building blocks:
input+not
stream splitter
clause or

each building block has a constant size, and they are repeated and chained together to build the final world.
specifically, for a CNF formula with n variables and m clauses we have n input-not blocks, n*m stream splitters and n*m clause ors. The bounding box of the resulting world is x_range=5*n+2, z_range=2*m+6, y_range=m+6 (?). The input world X therefore scales polynomially with the size of the CNF.

To convert the general world construction to actually represent the given CNF, we simply do a pass over all stream splitters and remove a block if the corresponding variable or it's negation occur in the corresponding clause. Overall, the construction of the world is polynomial in the size of the CNF.

With our world generator we show that SAT < Water. due to transitivity of < and L < SAT we know that Water is NP-hard.
Therefore, Water is NP-complete