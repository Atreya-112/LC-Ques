class Solution {
    static class Pair{
        int x;
        int y;
        public Pair(int x,int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString(){
            return "("+x+","+y+")";
        }
    }
    
    public int numBusesToDestination(int[][] routes, int source, int target) {
        
        if(source==target) return 0;
        
        HashMap<Integer,List<Pair>> map = new HashMap<Integer,List<Pair>>();
        
        HashMap<Integer,Integer> cache = new HashMap<Integer,Integer>();
        
        PriorityQueue<Pair> q = new PriorityQueue<Pair>((a,b)->{
            return cache.get(a.x)-cache.get(b.x);
        });
        
        for(int i = 0;i < routes.length;i++){
            
            int arr[] = routes[i];
            
            for(int j = 0;j < arr.length;j++){
                
                if(!map.containsKey(arr[j])) map.put(arr[j],new ArrayList<Pair>());
                
                if(j+1==arr.length) map.get(arr[j]).add(new Pair(arr[0],i));
                
                else map.get(arr[j]).add(new Pair(arr[j+1],i));
                
                if(arr[j]==source) {
                    cache.put(source,1);
                    q.offer(new Pair(source,i));
                }
            }
        }

        // System.out.println(q);
        
        int min = 501;

        while(!q.isEmpty()){

            Pair pr = q.poll();
            int stop = pr.x;
            int currBus = pr.y;
            int buses = cache.get(pr.x);

            // System.out.println(pr + " " + cache.get(stop));

            for(Pair p : map.get(stop)){
                
                int var = 0;
                
                if(p.y!=currBus) var++;
                
                if(!cache.containsKey(p.x) || cache.get(p.x) > buses + var){
                    
                    cache.put(p.x,buses+var);
                    
                    q.offer(p);

                    if(p.x==target) min = Math.min(buses+var,min);    
                
                }
                
            }
        }

        return (min==501)?-1:min;
    }
    
}
