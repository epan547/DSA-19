import java.util.HashMap;
import java.util.HashSet;

public class MCCR {
        public static int MCCR(EdgeWeightedGraph G) {
            // TODO

            // Initialize an Index PQ
            IndexPQ PQ = new IndexPQ(G.numberOfV());

            // hashmap to store vertices
            HashMap<Integer, Integer> visited = new HashMap<>();
            HashMap<Integer, Integer> weights = new HashMap<>();

            // Put vertices into PQ
            for(int i=0; i<G.numberOfV(); i++){
                PQ.insert(i, 1000);
                weights.put(i, 1000);
            }

            int cost = 0;
            PQ.decreaseKey(0, 0);
            weights.put(0,0);
            while(!PQ.isEmpty()){
                int temp = PQ.delMin();
                visited.put(temp, temp);
                cost += weights.get(temp);

                for(Edge e:G.edges(temp)){
                    if(e.weight() < weights.get(e.other(temp)) && !visited.containsKey(e.other(temp))){
                        PQ.decreaseKey(e.other(temp), e.weight());
                        weights.put(e.other(temp), e.weight());
                    }
                }

            }


            return cost;
        }

    }

