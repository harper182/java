public class BusinessProcess {
    /* station distribute map */
    private static int [][]distMap = {
            {-1, 5, -1, 5, 7},
            {-1, -1, 4, -1, -1},
            {-1, -1, -1, 8, 2},
            {-1, -1, 8, -1, 6},
            {-1, 3, -1, -1, -1},
    };
    private BaseUtils buObj = new BaseUtils();

    /*
    * return shortest distance according to stationList
    */
    String ptsListDist(String stationList){
        int dist = 0;
        for(int i=0;i<stationList.length()-1;i++){
            if(buObj.initDFS(stationList.charAt(i),stationList.charAt(i+1),getDistMap()).equals("ERROR"))
                return "NO SUCH ROUTE";
            else{
                String curtDist = buObj.initDFS(stationList.charAt(i),stationList.charAt(i+1),getDistMap());
                dist = dist + Integer.parseInt(curtDist);
            }
        }
        return String.valueOf(dist);
    }

    /*
    * return shortest distance according to stationSeries
    */
    public String ptsSeriDist(String stationList){
        int dist = 0;
        for(int i=0;i<stationList.length()-1;i++){
            int indFir = stationList.charAt(i)-'A';
            int indSec = stationList.charAt(i+1)-'A';
            if(distMap[indFir][indSec]>0)
                dist = dist + distMap[indFir][indSec];
            else
                return "NO SUCH ROUTE";
        }
        return String.valueOf(dist);
    }
    /*
    * return shortest distance between 2 station points
    */
    public String pts2PtsDist(char start,char end){
        if(buObj.initDFS(start,end,getDistMap()).equals("ERROR"))
            return "NO SUCH ROUTE";
        else
            return buObj.initDFS(start,end,getDistMap());
    }

    /*
    * return station 2 station Schedule under the maxStop
    */
    public String countPts2PtsSchdeByMaxStop(char start,char end,int maxStop){
        int count = buObj.initMaxConsDFS(maxStop,getDistMap(),start,end);
        if(count == 0){
            return "NO SUCH ROUTE";
        }else
            return String.valueOf(count);

    }

    /*
    * return station 2 station Schedule equals to onStop
    */
    public String countPts2PtsSchdeByOnStop(char start,char end,int onStop){
        int count = buObj.initExectConsDFS(onStop,getDistMap(),start,end);
        if(count == 0){
            return "NO SUCH ROUTE";
        }else
            return String.valueOf(count);
    }
    /*
    * return station 2 station Schedule under the maxDist
    */
    public String countPts2PtsSchdeByMaxDist(char start,char end,int maxDist){
        int count = buObj.initDistConsDFS(maxDist,getDistMap(),start,end);
        if(count == 0){
            return "NO SUCH ROUTE";
        }else
            return String.valueOf(count);
    }


    public static int [][] getDistMap() {
        return distMap;
    }
    public static void setDistMap(int [][] distMap) {
        BusinessProcess.distMap = distMap;
    }
}
